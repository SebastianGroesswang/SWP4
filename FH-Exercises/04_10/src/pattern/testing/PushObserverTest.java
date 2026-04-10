package pattern.testing;

import pattern.observer.push.Observer;
import pattern.observer.push.impl.TimerObserver;
import pattern.observer.push.impl.TimerObservable;

public class PushObserverTest {
    public static void main(String[] args) throws InterruptedException {
        TimerObservable timer = new TimerObservable(10, 100);
        TimerObservable timer2 = new TimerObservable(20, 100);

        Observer observer = new TimerObserver();
        Observer observer2 = new TimerObserver();

        timer.addObserver(observer);
        timer.addObserver(observer2);

        timer2.addObserver(observer);

        timer.runTimer();
        timer2.runTimer();

        System.out.println("Timer finished");
    }
}
