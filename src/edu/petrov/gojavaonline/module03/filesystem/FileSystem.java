package edu.petrov.gojavaonline.module03.filesystem;

/**
 * Created by anton on 06/03/16.
 */
public class FileSystem {

    public static Directory createDirectory(String path, String name) {
        Directory dir = new Directory(path, name);
        dir.createDefaultDirectories();
        return dir;
    }

}
