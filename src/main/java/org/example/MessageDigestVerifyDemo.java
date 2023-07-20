package org.example;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestVerifyDemo {
  public static void main(String[] args) {
    try {
      // Step 1: Get MessageDigest instance for SHA algorithm
      MessageDigest md = MessageDigest.getInstance("SHA");

      // Step 2: Input data into the digest class (you can feed multiple times)
      String message = "Hello, this is a sample message.";
      md.update(message.getBytes(StandardCharsets.UTF_8));

      // Step 3: Calculate the digest
      byte[] digest = md.digest();

      // Display the results in hexadecimal representation
      System.out.println("Message: " + message);
      System.out.println("SHA Digest (in hexadecimal): " + bytesToHex(digest));

      // Example of comparing with an original digest (use this to simulate verifying against a stored digest)
      byte[] originalDigest = md.digest(message.getBytes(StandardCharsets.UTF_8));

      // Compare the original digest with the newly calculated digest
      boolean isEqual = MessageDigest.isEqual(digest, originalDigest);

      // Display the result of the comparison
      System.out.println("Digest comparison result: " + isEqual);
    } catch (NoSuchAlgorithmException e) {
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


  /**
   * **********Result *************
   *
   * /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=52725:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/ananda/org/ananda/learn/mis/secure-software-3/target/classes org.example.MessageDigestVerifyDemo
   * Message: Hello, this is a sample message.
   * SHA Digest (in hexadecimal): ad36d8a070074f427f38a463e724aa774b96b412
   * Digest comparison result: true
   *
   * Process finished with exit code 0
   */

}
