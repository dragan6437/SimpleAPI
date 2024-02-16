import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class SimpleListener implements HttpHandler {

	PrivateKey privateKey;

	SimpleListener(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public void handle(HttpExchange t) throws IOException {
		// Send response message
		String response = "Success!";
		t.sendResponseHeaders(200, response.getBytes().length);

		// Get request body
		InputStream requestBody = t.getRequestBody();
		OutputStream os = t.getResponseBody();
		byte[] requestBodyBytes = requestBody.readAllBytes();

		// Try to decrypt the request body
		RSAEncryptor encryptor = new RSAEncryptor();
		try {
			byte[] decryptedBytes = encryptor.decrypt(requestBodyBytes, privateKey);
			String decryptedMessage = new String(decryptedBytes);
			System.out.println("Decrypted message: " + decryptedMessage);
		} catch (BadPaddingException e) {
			System.out.println("BadPaddingException :" + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("General exeption :" + e);
			e.printStackTrace();
		}

		os.write(response.getBytes());
		os.close();
	}

}