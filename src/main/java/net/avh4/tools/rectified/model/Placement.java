package net.avh4.tools.rectified.model;

import net.avh4.math.geometry.Rect;

public interface Placement {
    Rect place(Rect bounds);
}
