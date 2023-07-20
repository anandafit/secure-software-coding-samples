package org.example;
import java.security.*;

public class SignatureCreationDemo {
  public static void main(String[] args) {
    try {
      // Generate Public/Private key pair using DSA
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
      keyGen.initialize(1024, new SecureRandom());
      KeyPair keyPair = keyGen.generateKeyPair();

      // Create the Signature object
      Signature signature = Signature.getInstance("SHA1withDSA");
      signature.initSign(keyPair.getPrivate(), new SecureRandom());

      // Message to be signed
      String msg = "Hello, this is a sample message.";

      // Update the Signature object with the message
      signature.update(msg.getBytes());

      // Create the digital signature
      byte[] sigBytes = signature.sign();

      // Display the digital signature
      System.out.println("Original Message: " + msg);
      System.out.println("Digital Signature (in hexadecimal): " + bytesToHex(sigBytes));

      // Verify the signature using the public key
      signature.initVerify(keyPair.getPublic());
      signature.update(msg.getBytes());
      boolean verified = signature.verify(sigBytes);
      System.out.println("Signature Verified: " + verified);
    } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
      e.printStackTrace();
    }
  }

  // Helper method to convert bytes to hexadecimal representation
  private static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}


/**
 * ***** result ***
 *
 * /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=52804:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/ananda/org/ananda/learn/mis/secure-software-3/target/classes org.example.SignatureCreationDemo
 * Original Message: Hello, this is a sample message.
 * Digital Signature (in hexadecimal): 302d02140ee4532af41acfa42d672fdb8b0def1183958ef70215008df2a755b1b95220d24d9a423715b287f87f757d
 * Signature Verified: true
 *
 * Process finished with exit code 0
 *
 */
