package pattern.observer.push.impl;

import pattern.observer.push.Observer;

public class ConcreteObserver implements Observer<Integer> {
    @Override
    public void update(Integer changedData) {
        System.out.println("State changed to " + changedData);
    }
}
