package net.avh4.util.test;

import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.framework.uilayer.mvc.Observer;

public class FakeChannel<T> extends Channel<T> {
    public Observer observer;

    @Override public T get() {
        throw new RuntimeException("FakeObservable internal error: All methods that call getObservedValue() should have been overridden");
    }

    @Override public void watch(Observer observer) {
        this.observer = observer;
    }
}
