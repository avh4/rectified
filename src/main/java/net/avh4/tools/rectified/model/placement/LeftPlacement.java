package net.avh4.tools.rectified.model.placement;

import net.avh4.math.geometry.Rect;

public class LeftPlacement implements Placement {
    private final int size;

    public LeftPlacement(int size) {
        this.size = size;
    }

    @Override public Rect place(Rect bounds) {
        return bounds.left(size);
    }

    @Override public Rect remainder(Rect bounds) {
        return bounds.right(bounds.width() - size);
    }

    @Override public String toString() {
        return "LeftPlacement{" +
                "size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeftPlacement that = (LeftPlacement) o;

        if (size != that.size) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return size;
    }
}
