package net.avh4.tools.rectified.model.placement;

import net.avh4.math.geometry.Rect;

public interface Placement {
    Rect place(Rect bounds);

    Rect remainder(Rect bounds);

    String navString();
}
