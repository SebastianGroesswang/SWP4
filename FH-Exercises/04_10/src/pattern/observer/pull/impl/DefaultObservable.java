package pattern.observer.pull.impl;

import pattern.observer.pull.Observable;
import pattern.observer.pull.Observer;

import java.util.HashSet;
import java.util.Set;

public abstract class DefaultObservable implements Observable {
    private final Set<Observer> registerObserver = new HashSet<>();

    @Override
    public void addObserver(Observer observer) {
        if(observer != null)
            registerObserver.add(observer);
    }


    @Override
    public void removeObserver(Observer observer) {
        if(observer != null)
            registerObserver.remove(observer);

    }

    @Override
    public void notifyObservers() {
        for (Observer observer : registerObserver) {
            observer.update();
        }
    }

    public abstract Object getState();
}
