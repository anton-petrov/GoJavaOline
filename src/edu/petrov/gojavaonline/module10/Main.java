package edu.petrov.gojavaonline.module10;

import edu.petrov.gojavaonline.module09.CaesarCipher;

import java.io.*;

/**
 * Module 10
 * Реализовать сохранение/загрузку текстового сообщения в файл с предварительным шифрованием/расшифрованием.
 * Предусмотреть обработку различных ошибок ввода/вывода.
 */

/**
 *  Example
 *  -------
 *  Encrypt and write to file : java Main --key 3 --message "Some text" --file "text.txt"
 *  Read from file and decrypt : java Main --file "text.txt" --key 3
 */
public class Main {
    public static void main(String[] args) {
        String file = "";
        String message = null;
        int key = 0;
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-m":
                case "-message":
                case "--message":
                    if(i + 1 < args.length) {
                        message = args[i + 1];
                    }
                    break;
                case "-k":
                case "-key":
                case "--key":
                    if(i + 1 < args.length) {
                        key = Integer.valueOf(args[i + 1]);
                    }
                    break;
                case "-f":
                case "-file":
                case "--file":
                    if(i + 1 < args.length) {
                        file = args[i + 1];
                    }
                    break;
            }
        }

        if(message != null) {
            // encrypt and write
            try {
                TextFile.Write(file, CaesarCipher.crypt(message, key), false);
            } catch (FileNotFoundException e) {
                System.out.printf("File '" + file + "' not found!");
            } catch (IOException e) {
                System.out.println("Write error!");
            }
        }
        else {
            // read and decrypt
            try {
                String text = CaesarCipher.decrypt(TextFile.Read(file), key);
                System.out.println(text);
            } catch (FileNotFoundException e) {
                System.out.printf("File '" + file + "' not found!");
            } catch (IOException e) {
                System.out.println("Read error!");
            }
        }
    }
}

// end of file