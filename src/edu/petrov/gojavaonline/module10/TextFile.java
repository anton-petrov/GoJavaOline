package edu.petrov.gojavaonline.module10;

import java.io.*;

/**
 * Created by anton on 29/03/16.
 */

class TextFile {

    public static void Write(String fileName, String text, boolean append) throws IOException {
        if(fileName == null || text == null)
            return;
        File file = new File(fileName);
        try(final FileWriter writer = new FileWriter(file, append)) {
            writer.write(text);
            writer.flush();
        }
    }

    public static String Read(String fileName) throws IOException {
        if(fileName == null)
            return null;
        File file = new File(fileName);
        char[] buffer = new char[0];
        try(final FileReader reader = new FileReader(file))
        {
            buffer = new char[(int)file.length()];
            reader.read(buffer);
        }
        return new String(buffer);
    }

}
