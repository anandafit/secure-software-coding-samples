package org.example;

import java.security.SecureRandom;

public class RandomNumberGeneratorDemo {
  public static void main(String[] args) {
    // Define the desired seed size (in bytes)
    int seedSizeBytes = 32; // 256 bits

    // Create a SecureRandom object
    SecureRandom secureRandom = new SecureRandom();

    // Generate a random seed of the specified size
    byte[] seed = secureRandom.generateSeed(seedSizeBytes);

    // Set the seed for the SecureRandom object
    secureRandom.setSeed(seed);

    // Generate a random byte array of size 32 (256 bits)
    byte[] randomBytes = new byte[seedSizeBytes];
    secureRandom.nextBytes(randomBytes);

    // Display the results
    System.out.println("Generated Seed: " + bytesToHex(seed));
    System.out.println("Random 256 bits: " + bytesToHex(randomBytes));
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
