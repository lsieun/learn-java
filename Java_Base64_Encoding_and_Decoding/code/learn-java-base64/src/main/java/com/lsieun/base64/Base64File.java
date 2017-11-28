package com.lsieun.base64;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class Base64File {
    private static Base64 base64;

    static {
        base64 = new Base64();
    }

    public static void main(String[] args) {
        String encodedString = getFileStr("D:/tmp/img01.jpg");
        System.out.println("encodedString = \r\n" + encodedString);
        generateFileFromStr(encodedString,"D:/tmp/img/newImg01.jpg");
    }

    public static String getFileStr(String filePath){
        InputStream in = null;
        byte[] data = null; //读取文件字节数组
        try{
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            int count = in.read(data);
            System.out.println("read file bytes: " + count + "byte");
        }catch (IOException e){
            e.printStackTrace();
        }
        finally{
            IOUtils.closeQuietly(in);
        }

        if(data == null) return null;

        String encodedString = new String(base64.encode(data));
        return encodedString;
    }

    public static void generateFileFromStr(String fileContent, String filePath){
        if(fileContent == null) return;
        byte[] data = base64.decode(fileContent.getBytes());

        OutputStream out = null;

        try{
            out = new FileOutputStream(filePath);
            out.write(data);
            out.flush();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally{
            IOUtils.closeQuietly(out);
        }
    }
}
