package com.greenwich.comp1640.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtil {

    private FileUtil() {

    }

    public static void saveFile(InputStream inputStream, String filePath) throws IOException {
        OutputStream outputStream = new FileOutputStream(new File(filePath));

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        outputStream.flush();
        outputStream.close();
        return;
    }

}
