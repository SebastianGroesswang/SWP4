package pattern.observer.pull.impl;

import pattern.observer.pull.Observable;
import pattern.observer.pull.Observer;

public class ConcreteObserver implements Observer {

    //pull method
    Observable observable;

    public ConcreteObserver(Observable observer) {
        this.observable = observer;
    }

    @Override
    public void update() {
        if(observable instanceof DefaultObservable o)
            System.out.println("State changed to " + o.getState());
    }
}
