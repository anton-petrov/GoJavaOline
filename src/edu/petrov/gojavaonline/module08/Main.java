package edu.petrov.gojavaonline.module08;

import edu.petrov.gojavaonline.module03.filesystem.Directory;
import edu.petrov.gojavaonline.module03.filesystem.FileSystem;
import edu.petrov.gojavaonline.module03.filesystem.ImageFile;
import edu.petrov.gojavaonline.module03.filesystem.TextFile;

/**
 * Module 8
 * Выбрать иерархию классов из ДЗ по теме OOP in Java и создать несколько коллекций объектов из данной иерархии.
 * Хотя бы одна коллекция должна содержать в себе объекты разных классов.
 * Создать класс, который печатает созданные коллекции на экран в виде таблицы. Колонки таблицы соответствуют полям
 * объектов. Каждая строка в таблице соответствует объекту из коллекции.
 * Создать упорядоченный список объектов из ДЗ по теме OOP in Java не прибегая к  использованию Collections.sort().
 */
public class Main {
    public static void main(String[] args) {
        Directory base = FileSystem.createDirectory("C:/petrov", "base");
        base.createFile("file1");
        base.createFile("file2");
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

        // здесь выполняется своя сортировка и печать на экран списка файлов
        base.print();
    }
}
