package lsieun.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
    public static String backupFile(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) return "File Not Existed";

        String newFilePath = filepath + "." + System.currentTimeMillis() + ".bak";

        boolean flag = copy(filepath, newFilePath);
        if (flag) {
            return newFilePath;
        }

        return "Failed!";
    }

    public static Boolean copy(String src, String dest) {
        File srcFile = new File(src);
        File destFile = new File(dest);

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(srcFile);
            in = new BufferedInputStream(in);

            out = new FileOutputStream(destFile);
            out = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];

            int len = -1;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static void deleteDirecotry(String dirPath) {
        File directory = new File(dirPath);

        //make sure directory exists
        if(!directory.exists()){
            return;
        }

        try{
            delete(directory);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void delete(File file) throws IOException{
        if (file == null) return;

        if(file.isDirectory()){
            //list all the directory contents
            String files[] = file.list();
            if(files == null) return;

            //directory is empty, then delete it
            if(files.length==0){
                boolean flag = file.delete();
                if(flag) {
                    System.out.println("Directory is deleted : " + file.getAbsolutePath());
                }
            }else{

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                files = file.list();
                //check the directory again, if empty then delete it
                if(files == null || files.length==0){
                    boolean flag = file.delete();
                    if(flag) {
                        System.out.println("Directory is deleted : " + file.getAbsolutePath());
                    }
                }
            }

        }else{
            //if file, then delete it
            boolean flag = file.delete();
            if(flag) {
                System.out.println("File is deleted : " + file.getAbsolutePath());
            }
        }
    }
}
