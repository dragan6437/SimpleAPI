import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.sun.net.httpserver.HttpServer;

public class Main {

	public static void main(String[] args) throws IOException, Exception {
		// Generate keys
		RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("Public key :" + publicKey);
		
		// Write to file
		byte[] key = publicKey.getEncoded();
		FileOutputStream keyfos = new FileOutputStream("rsaPublicKey");
		keyfos.write(key);
		keyfos.close();
		
		// Create server
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/create", new SimpleListener(privateKey));
        server.setExecutor(null); // creates a default executor
        System.out.println("Starting server...");
        server.start();
	}

}
