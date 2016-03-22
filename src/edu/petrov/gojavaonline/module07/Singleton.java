package edu.petrov.gojavaonline.module07;

/**
 * Created by Anton on 22.03.2016.
 */

/*
  Thread-safe singleton pattern.
*/
public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
