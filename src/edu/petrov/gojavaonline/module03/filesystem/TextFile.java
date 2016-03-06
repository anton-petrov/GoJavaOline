package edu.petrov.gojavaonline.module03.filesystem;

import java.nio.charset.Charset;

/**
 * Created by anton on 06/03/16.
 */
public class TextFile extends File {

    TextFile(String path, String name) {
        super(path, name);
    }

    public TextFile(String name) {
        super(name);
    }

    public String getText() {
        return new String(getData());
    }

    public void printText() {
        System.out.println(getText());
    }

    public void setText(String text) {
        data = text.getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public long size() {
        return getText().length();
    }
}
