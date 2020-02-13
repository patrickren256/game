package com.example.game.Model.Interface;

public interface Observable {
    void register(Observer o);

    void unregister(Observer o);

    void notifyObserver();
}
