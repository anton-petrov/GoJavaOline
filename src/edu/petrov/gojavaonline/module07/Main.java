package edu.petrov.gojavaonline.module07;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 23.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        MyData data = new MyData();
        data.changeData(1, 2, 3);
        ConsolePrinter consolePrinter = new ConsolePrinter(data);
        data.changeData(7, 6, 5);
    }
}

class MyData implements Observable {
    private Object[] data;
    private List<Observer> observers;

    public MyData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }

    public void changeData(Object... data) {
        this.data = data;
        notifyObservers();
    }
}

class ConsolePrinter implements Observer {
    private Object[] data;
    private MyData myData;

    public ConsolePrinter(MyData data) {
        this.myData = data;
        myData.registerObserver(this);
    }

    @Override
    public void update(Object... data) {
        this.data = data;
        display();
    }

    public void display() {
        for (Object o : data) {
            System.out.print(o);
            System.out.print(" ");
        }
        System.out.println();
    }
}