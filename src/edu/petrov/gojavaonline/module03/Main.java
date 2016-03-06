package edu.petrov.gojavaonline.module03;

import edu.petrov.gojavaonline.module03.filesystem.Directory;
import edu.petrov.gojavaonline.module03.filesystem.FileSystem;
import edu.petrov.gojavaonline.module03.filesystem.ImageFile;
import edu.petrov.gojavaonline.module03.filesystem.TextFile;

/**
 * Created by anton on 06/03/16.
 */
public class Main {
    public static void main(String[] args) {
        Directory base = FileSystem.createDirectory("C:/petrov", "base");
        base.createFile("file1");
        base.createFile("file2");
        base.createFile(new TextFile("mytext.txt"));

        ImageFile imgFile = new ImageFile("image1.jpg");
        imgFile.setImage(
                "  /\\_/\\\n" +
                        "=( °w° )=\n" +
                        "  )   (  //\n" +
                        " (__ __)//"
        );
        imgFile.drawImage();

        base.createFile(imgFile);
        base.createDirectory("dir1").createDirectory("dir2").createFile("file3");

        base.print(true);
    }
}
