package main.java.org.vision.utils;

public class ByteArrayUtil {
    public static void reverseArray(byte[] bytes) {
        byte t;
        for (int i = 0, j = bytes.length - 1; i < bytes.length / 2; i++, j--) {
            t = bytes[j];
            bytes[j] = bytes[i];
            bytes[i] = t;
        }
    }

    public static byte[] combineArray(byte[]... source) {
        int combinedLength = 0;
        for (byte[] bytes : source) {
            combinedLength += bytes.length;
        }
        byte[] combined = new byte[combinedLength];

        int descPosition = 0;
        for (byte[] bytes : source) {
            System.arraycopy(bytes, 0, combined, descPosition, bytes.length);
            descPosition += bytes.length;
        }
        return combined;
    }

    public static void printArray(byte[] array) {
        for (byte b: array) {
            System.out.print(String.format("%d ", b));
        }
        System.out.println();
    }

}
