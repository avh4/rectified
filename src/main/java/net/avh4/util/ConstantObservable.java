package net.avh4.util;

public class ConstantObservable<T> extends Observable<T> {
    private final T constantValue;

    public ConstantObservable(T constantValue) {
        super();
        this.constantValue = constantValue;
    }

    @Override protected T getObservedValue() {
        return constantValue;
    }
}
