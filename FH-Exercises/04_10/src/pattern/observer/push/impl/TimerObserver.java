package pattern.observer.push.impl;

import pattern.observer.push.Observable;
import pattern.observer.push.Observer;

public class TimerObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update of [" + this + "] coming from [" + o + "] with change [" + arg + "]");
    }
}
