import java.security.*;
import javax.crypto.Cipher;

public class RSAEncryptor {
	public byte[] encrypt (String message, PublicKey publicKey) throws Exception {
		// Perform encryption operation
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(message.getBytes());
	}
	
	public byte[] decrypt (byte[] encryptedBytes, PrivateKey privateKey) throws Exception {
		// Perform encryption operation
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(encryptedBytes);
	}
}