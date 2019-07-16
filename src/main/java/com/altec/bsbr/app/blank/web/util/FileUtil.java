package com.altec.bsbr.app.blank.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: Jul 28, 2009
 */
public class FileUtil {
    protected static final String prefix = "scd";
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1000];

        int bytesRead = 0;

        while ((bytesRead = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, bytesRead);
        }

        return outputStream.toByteArray();
    }

    public static byte[] readFileBytes(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return readInputStream(fileInputStream);
    }

    public static File createTempFile(String fileName, File baseDir) throws IOException {
        File file = new File(baseDir, fileName);
        if (!file.exists())
            file.createNewFile();
        return file;
    }

    public static long getFileSize(File file) throws IOException {
        return (file.exists() ? file.length() : 0);
    }

    public static File createTempDir(Long time, String tempDirPath) throws IOException {
        logger.info("Using {} as temp dir.", tempDirPath);
        File tempDir = new File(tempDirPath + prefix + "-" + time);
        tempDir.mkdir();
        return tempDir;
    }

    public static void writeBytes(File file, byte[] bytes) throws IOException {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        OutputStream outputStream = new FileOutputStream(file);

        try {
            byte[] bytesRead = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(bytesRead)) > 0) {
                outputStream.write(bytesRead, 0, length);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            outputStream.close();
        }


    }

    public static void write(File file, WriterCallback callback) throws IOException {

        if (!file.exists()) {
            file.createNewFile();
        }

        long startedTime = System.currentTimeMillis();

        FileWriter fileWriter = new FileWriter(file, true);

        try {

            String header = callback.getHeader();

            if (header != null)
                fileWriter.append(header);

            fileWriter.append(callback.getEndLine());

            long rows = 0;

            while (callback.hasNext()) {

                fileWriter.append(callback.next());

                fileWriter.append(callback.getEndLine());

                rows++;

            }

            logger.info("Stored rows: " + (rows != 0 ? ++rows : 0));

            String trail = callback.getTrail();

            if (trail != null)
                fileWriter.append(trail);

            fileWriter.append(callback.getEndLine());

            String endFile = callback.getEndFile();

            if (endFile != null)
                fileWriter.append(endFile);

            fileWriter.flush();

        } finally {
            fileWriter.close();
        }

        logger.info("Write on {} file finished. Elapsed time: {} ms", file.getAbsoluteFile(), (System.currentTimeMillis() - startedTime));
    }

    public static void addToZipFile(File saveToFile, File dirToCompress) throws IOException {
        if (!saveToFile.exists()) {
            logger.trace("");
            saveToFile.createNewFile();
        }

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(saveToFile));
        try {
            addToZipFile(zipOutputStream, dirToCompress, null, dirToCompress);
        } finally {
            zipOutputStream.close();
        }
    }

    protected static void addToZipFile(ZipOutputStream zipOutputStream, File rootDir, File parentDir, File dirToCompress) throws IOException {
        for (File fileToRead : dirToCompress.listFiles()) {
            if (!fileToRead.isDirectory()) {

                // Create a buffer for reading the files
                byte[] buf = new byte[1024];

                ZipEntry entry = new ZipEntry(
                        (parentDir != null && !parentDir.equals(rootDir) ? parentDir.getName() + File.separator : "") +
                                (dirToCompress != null && !dirToCompress.equals(rootDir) ? dirToCompress.getName() + File.separator : "") +
                                fileToRead.getName());
                zipOutputStream.putNextEntry(entry);

                FileInputStream in = new FileInputStream(fileToRead);

                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    zipOutputStream.write(buf, 0, len);
                }

                // Complete the entry
                zipOutputStream.closeEntry();
                in.close();

            } else {

                addToZipFile(zipOutputStream, rootDir, dirToCompress, fileToRead);

            }
        }
    }

    public static void deleteDir(File dir) {
        if (dir != null && dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
            dir.delete();
        }
    }
    
    public static void deleteFilesFromDir(File dir) {
        if (dir != null && dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
        }
    }

    public static abstract class WriterCallback {

        public abstract String getHeader();

        public abstract String getTrail();

        public abstract String next();

        public abstract boolean hasNext();

        public abstract String getEndFile();

        public abstract String getEndLine();

        public static String parseNumber(Number number, int size, String pad) {

            StringBuilder stringBuilder = new StringBuilder(number.toString());
            if (stringBuilder.length() > size) {
                return stringBuilder.substring(0, size);
            } else if (stringBuilder.length() < size) {
                for (int i = stringBuilder.length(); i < size; i++) {
                    stringBuilder.insert(0, pad);
                }
            }
            return stringBuilder.toString();

        }

    }
}
