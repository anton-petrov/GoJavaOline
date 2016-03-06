package edu.petrov.gojavaonline.module03.musicshop;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by anton on 06/03/16.
 */
public class Guitar extends Product implements MusicalInstrument {
    public Guitar(int price) {
        super(price);
    }

    @Override
    public void playSound() {
        throw new NotImplementedException();
    }
}
