package edu.petrov.gojavaonline.module07;

/**
 * Created by Anton on 23.03.2016.
 */
public interface Observable {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
