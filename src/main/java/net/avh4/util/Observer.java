package net.avh4.util;

public interface Observer<T> {
    void update(T newValue);
}
