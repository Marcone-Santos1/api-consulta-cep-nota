package com.fatec.web.observers;

import java.util.ArrayList;
import java.util.List;

public class MediaSubject {
    private double media;
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void calculateMedia(double p1, double p2) {
        media = (p1 + p2) / 2;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(media);
        }
    }
}