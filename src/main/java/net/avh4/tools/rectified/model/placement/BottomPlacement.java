package net.avh4.tools.rectified.model.placement;

import net.avh4.math.geometry.Rect;

public class BottomPlacement implements Placement {
    private final int size;

    public BottomPlacement(int size) {
        this.size = size;
    }

    @Override public Rect place(Rect bounds) {
        return bounds.bottom(size);
    }

    @Override public Rect remainder(Rect bounds) {
        return bounds.top(bounds.height() - size);
    }

    @Override public String navString() {
        return "Bottom (" + size + "dp)";
    }

    @Override public String toString() {
        return "BottomPlacement{" +
                "size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BottomPlacement that = (BottomPlacement) o;

        if (size != that.size) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return size;
    }
}
