package pattern.testing;

import pattern.observer.pull.Observer;
import pattern.observer.pull.impl.ConcreteObserver;
import pattern.observer.pull.impl.TimerObservable;

public class PullObserverTest {
    public static void main(String[] args) throws InterruptedException {
        TimerObservable timer = new TimerObservable(10, 100);
        TimerObservable timer2 = new TimerObservable(20, 100);
        Observer observer = new ConcreteObserver(timer);

        timer.addObserver(observer);
        timer2.addObserver(observer);
        timer.runTimer();
        timer2.runTimer();

        System.out.println("Timer finished");
    }
}
