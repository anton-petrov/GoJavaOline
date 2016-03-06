package edu.petrov.gojavaonline.module03.filesystem;

import java.nio.charset.Charset;

/**
 * Created by anton on 06/03/16.
 */
public class ImageFile extends File {

    public ImageFile(String name) {
        super(name);
    }

    public void setImage(String textImage) {
        data = textImage.getBytes(Charset.forName("UTF-8"));
    }

    public void drawImage() {
        System.out.println(new String(getData()));
    }
}
