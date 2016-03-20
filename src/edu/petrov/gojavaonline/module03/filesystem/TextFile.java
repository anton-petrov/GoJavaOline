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
        if (data != null)
            return new String(getData());
        else
            return "";
    }

    public void setText(String text) {
        data = text.getBytes(Charset.forName("UTF-8"));
    }

    public void printText() {
        System.out.println(getText());
    }

    @Override
    public long size() {
        String text = getText();
        return text != null ? text.length() : 0;
    }
}
