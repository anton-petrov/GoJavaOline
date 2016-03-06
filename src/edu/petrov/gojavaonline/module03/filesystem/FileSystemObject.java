package edu.petrov.gojavaonline.module03.filesystem;

/**
 * Created by anton on 06/03/16.
 */
public interface FileSystemObject {
    String name();

    String getPath();

    long size();

    boolean isDirectory();
}
