package org.example;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CipherEncryption {
  public static void main(String[] args) {
    try {
      // Step 1: Obtain the cipher engine for DES/CBC/PKCS5Padding
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

      // Step 2: Initializing the cipher engine for encryption with a key
      byte[] keyBytes = "12345678".getBytes(); // Replace this with your own 8-byte key
      SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "DES");

      byte[] ivBytes = new byte[cipher.getBlockSize()];
      IvParameterSpec iv = new IvParameterSpec(ivBytes);

      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

      // Step 3: Put the data into the cipher class (you can call update multiple times)
      String data = "This is a sample message to be encrypted.";
      byte[] buf = data.getBytes();
      byte[] encryptedBytes = cipher.update(buf);

      // Step 4: Do the padding and finish the encryption
      byte[] finalEncryptedBytes = cipher.doFinal();

      // Step 5: Save the initialization vector (IV)
      byte[] ivUsed = cipher.getIV();

      // Display the results
      System.out.println("Original Message: " + data);
      System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedBytes) +
          Base64.getEncoder().encodeToString(finalEncryptedBytes));
      System.out.println("Initialization Vector (IV): " + Base64.getEncoder().encodeToString(ivUsed));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/**
 * ****** Result *********
 *
 *
 * /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=54588:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/ananda/org/ananda/learn/mis/secure-software-3/target/classes org.example.CipherEncryption
 * Original Message: This is a sample message to be encrypted.
 * Encrypted Message: BcnEyvuZN9kBBYiE4ggb25WtxLvyvU2hwivj3kuMuseuk824nV/eZA==MJBhEg6nlR4=
 * Initialization Vector (IV): AAAAAAAAAAA=
 *
 * Process finished with exit code 0
 */
