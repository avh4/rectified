package net.avh4.tools.rectified.model.placement;

import net.avh4.math.geometry.Rect;

public class TopPlacement implements Placement {
    private final int size;

    public TopPlacement(int size) {
        this.size = size;
    }

    @Override public String toString() {
        return "TopPlacement{" +
                "size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopPlacement that = (TopPlacement) o;

        if (size != that.size) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return size;
    }

    @Override public Rect place(Rect bounds) {
        return bounds.top(size);
    }
}
