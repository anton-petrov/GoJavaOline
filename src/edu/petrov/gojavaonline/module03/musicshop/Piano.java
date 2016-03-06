package edu.petrov.gojavaonline.module03.musicshop;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by anton on 06/03/16.
 */
public class Piano extends Product implements MusicalInstrument {
    public Piano(int price) {
        super(price);
    }

    @Override
    public void playSound() {
        throw new NotImplementedException();
    }
}

