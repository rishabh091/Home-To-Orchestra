package com.orchestra.orchestra.services.helpers;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageHelper {

    public static byte[] compress(byte[] arr) {
        System.out.println("Original Image byte size : " + arr.length);

        Deflater deflater = new Deflater();
        deflater.setInput(arr);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        try{
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Compressed byte array size : " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        try{
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
