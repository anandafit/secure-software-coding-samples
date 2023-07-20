package org.example;
import java.io.*;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestStreamsDemo {
  public static void main(String[] args) {
    try {
      // Step 1: Bind the Digest algorithm and input/output stream
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      FileOutputStream fos = new FileOutputStream("data.txt");
      DigestOutputStream dos = new DigestOutputStream(fos, md);
      ObjectOutputStream oos = new ObjectOutputStream(dos);

      // Step 3: Write data using ObjectOutputSteam
      String message = "Hello, this is a sample message.";
      oos.writeObject(message);
      oos.flush();

      // Step 4: Turn off the digest calculation
      dos.on(false);

      // Step 2: Create ObjectInputStream associated with the DigestInputStream
      FileInputStream fis = new FileInputStream("data.txt");
      DigestInputStream dis = new DigestInputStream(fis, md);
      ObjectInputStream ois = new ObjectInputStream(dis);

      // Step 3: Read data using ObjectInputStream
      String readMessage = (String) ois.readObject();

      // Step 4: Turn on the digest calculation
      dis.on(true);

      // Step 5: Obtain the digest
      byte[] digest = md.digest();

      // Display the results
      System.out.println("Original Message: " + message);
      System.out.println("Read Message: " + readMessage);
      System.out.println("Calculated Digest: " + bytesToHex(digest));

    } catch (NoSuchAlgorithmException | IOException | ClassNotFoundException e) {
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
 * ***** Result *******
 *
 * /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=52766:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/ananda/org/ananda/learn/mis/secure-software-3/target/classes org.example.MessageDigestStreamsDemo
 * Original Message: Hello, this is a sample message.
 * Read Message: Hello, this is a sample message.
 * Calculated Digest: 5c883bc8889ed7c5a9f877ed0857245e28e8970c95acb76b068841a5b3f12bca
 *
 */
