package net.avh4.tools.rectified.uimodel.cqrs;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.placement.PlacementComponent;

public interface SelectionQuery {
    Component selectedComponent();

    PlacementComponent parentOfSelected();
}
