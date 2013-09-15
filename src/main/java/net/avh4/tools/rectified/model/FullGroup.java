package net.avh4.tools.rectified.model;

import net.avh4.math.geometry.Rect;
import org.pcollections.PVector;

public class FullGroup extends Group {
    private final PVector<Component> components;

    public FullGroup(PVector<Component> components) {
        this.components = components;
    }

    @Override public Rect placedBoundsForChild(Rect rect, Component child) {
        return rect;
    }

    @Override public PVector<Component> children() {
        return components;
    }

    @Override public FullGroup swap(Component oldComponent, Component newComponent) {
        int index = components.indexOf(oldComponent);
        if (index == -1) {
            throw new RuntimeException("Swap is not valid\n    Current: " + this + "\n    old: " + oldComponent + "\n    new: " + newComponent);
        }
        final PVector<Component> newComponents = components.with(index, newComponent);
        return new FullGroup(newComponents);
    }

    @Override public FullGroup add(Component component) {
        final PVector<Component> newComponents = components.plus(component);
        return new FullGroup(newComponents);
    }

    @Override public String navString() {
        return "Group";
    }

    @Override public String toString() {
        return "FullGroup{" +
                "components=" + components +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullGroup fullGroup = (FullGroup) o;

        if (components != null ? !components.equals(fullGroup.components) : fullGroup.components != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }
}
