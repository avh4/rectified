package net.avh4.tools.rectified.model.placement;

import net.avh4.math.geometry.Rect;

public class RightPlacement implements Placement {
    private final int size;

    public RightPlacement(int size) {
        this.size = size;
    }

    @Override public Rect place(Rect bounds) {
        return bounds.right(size);
    }

    @Override public Rect remainder(Rect bounds) {
        return bounds.left(bounds.width() - size);
    }

    @Override public String navString() {
        return "Right (" + size + "dp)";
    }

    @Override public String toString() {
        return "RightPlacement{" +
                "size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RightPlacement that = (RightPlacement) o;

        if (size != that.size) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return size;
    }
}
