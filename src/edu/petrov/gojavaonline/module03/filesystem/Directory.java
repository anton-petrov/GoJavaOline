package edu.petrov.gojavaonline.module03.filesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anton on 06/03/16.
 */
public class Directory implements FileSystemObject, Comparable {
    private final HashMap<String, FileSystemObject> files = new HashMap<>();
    private String path;
    private String name;

    Directory() {
    }

    Directory(String path, String name) {
        this.path = path;
        this.name = name;
    }

    private static String shiftStringRight(String string, int shift, String symbol) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shift; i++) result.append(symbol);
        return result.append(string).toString();
    }

    private static void printRecursively(Directory dir, int level) {
        List<FileSystemObject> currentFilesList = dir.getFilesList();
        for (FileSystemObject o : currentFilesList) {
            System.out.println(shiftStringRight("[" + o.name() + "]", level, "  "));
            if (o.isDirectory()) {
                printRecursively((Directory) o, level + 1);
            }
        }
    }

    public File createFile(String name) {
        if (name == null) return null;
        File file = new File(path, name);
        addFileSystemObject(file);
        return file;
    }

    public File createFile(File file) {
        if (file == null) return null;
        file.setPath(path);
        addFileSystemObject(file);
        return file;
    }

    public Directory createDirectory(String name) {
        if (name == null) return null;
        Directory dir = FileSystem.createDirectory(path, name);
        dir.createDefaultDirectories();
        addFileSystemObject(dir);
        return dir;
    }

    private FileSystemObject addFileSystemObject(FileSystemObject fileSystemObject) {
        if (fileSystemObject == null) return null;
        return files.put(fileSystemObject.name(), fileSystemObject);
    }

    void createDefaultDirectories() {
        addFileSystemObject(new Directory(path, "."));
        addFileSystemObject(new Directory(path, ".."));
    }

    public List<FileSystemObject> getFilesList() {
        List fileList = new ArrayList(files.values());
        fileList.sort((o1, o2) -> ((FileSystemObject) o1).name().compareToIgnoreCase(((FileSystemObject) o2).name()));
        return fileList;
    }

    public void print(boolean recursively) {
        if (recursively) {
            printRecursively(this, 0);
        } else {
            for (Map.Entry<String, FileSystemObject> entry : files.entrySet()) {
                FileSystemObject file = entry.getValue();
                if (file.isDirectory())
                    System.out.println("[" + file.name() + "]");
                else
                    System.out.println(file.name());
            }
        }
    }

    public void print() {
        Object[] fileArray = files.values().toArray();
        //ArrayUtils.insertionSort(fileArray);
    }

    public FileSystemObject deleteFile(String name) {
        return files.remove(name);
    }

    public FileSystemObject getFile(String name) {
        return files.get(name);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public long size() {
        return files.size();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Directory) o).name);
    }
}
