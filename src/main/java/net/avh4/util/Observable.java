package net.avh4.util;

import org.pcollections.PVector;
import org.pcollections.TreePVector;

public abstract class Observable<T> {
    private PVector<Observer<T>> observers = TreePVector.empty();

    protected synchronized void notifyObservers() {
        for (Observer<T> observer : observers) {
            observer.update(getObservedValue());
        }
    }

    protected abstract T getObservedValue();

    public synchronized void watch(Observer<T> observer) {
        final T currentValue = getObservedValue();
        observers = observers.plus(observer);
        if (currentValue != null) {
            observer.update(currentValue);
        }
    }
}
