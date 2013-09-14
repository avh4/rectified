package net.avh4.util.test;

import net.avh4.util.Observable;
import net.avh4.util.Observer;

public class FakeObservable<T> extends Observable<T> {
    public Observer<T> observer;

    @Override protected T getObservedValue() {
        throw new RuntimeException("FakeObservable internal error: All methods that call getObservedValue() should have been overridden");
    }

    @Override public void watch(Observer<T> observer) {
        this.observer = observer;
    }
}
