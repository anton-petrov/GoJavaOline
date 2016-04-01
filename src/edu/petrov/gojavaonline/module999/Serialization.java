package edu.petrov.gojavaonline.module999;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by anton on 30/03/16.
 */
public class Serialization {

    public static class RandomClass implements Serializable {
        // Генерация рандомного значения
        private static int r() {
            return (int) (Math.random() * 10);
        }

        private int data[];
        private transient List<Integer> data2 = new ArrayList<>(20);

        // Конструктор
        public RandomClass() {
            data = new int[r()];
            for (int i = 0; i < data.length; i++)
                data[i] = r();
        }

        public void printout() {
            System.out.println("This RandomClass has " + data.length + " random integers");
            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i] + ":");
                System.out.println();
            }
        }

        @Override
        public String toString() {
            return "RandomClass{" +
                    "data=" + Arrays.toString(data) +
                    '}';
        }
    }

    public static void Run() throws Exception {
        RandomClass rc1 = new RandomClass();
        RandomClass rc2 = new RandomClass();
        Date now = new Date(System.currentTimeMillis());

        System.out.println(rc1);
        System.out.println(rc2);
        System.out.println(now);


        // serialize
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream( byteOutputStream
                /* new FileOutputStream("objects.dat") */ );

        outputStream.writeObject(rc1);
        outputStream.writeObject(rc2);
        outputStream.writeObject(now);
        outputStream.close();

        // deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteOutputStream.toByteArray()));
        RandomClass _rc1 = (RandomClass) inputStream.readObject();
        RandomClass _rc2 = (RandomClass) inputStream.readObject();
        Date _now = (Date) inputStream.readObject();
        inputStream.close();

        System.out.println(_rc1);
        System.out.println(_rc2);
        System.out.println(_now);
    }
}
