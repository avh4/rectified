package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.Color;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.model.cqrs.DataQuery;
import net.avh4.util.Observable;
import org.pcollections.PStack;
import org.pcollections.TreePVector;

public class MutableDataModel extends Observable<Design> implements DataQuery, DataCommands {
    private Design design;

    public MutableDataModel() {
        Component background = new ColorComponent(Color.fromHSL(60, 0.5, 0.5));
        Group mainComponent = new FullGroup(TreePVector.singleton(background));
        this.design = new Design(mainComponent);
    }

    @Override protected Design getObservedValue() {
        return design;
    }

    @Deprecated // use watch instead
    @Override public Design design() {
        return design;
    }

    @Override public void replace(PStack<Component> path, Component newComponent) {
        if (path.size() < 2) throw new IllegalArgumentException("Parent path cannot be empty");
        final Design design = this.design;

        Group parent = null;
        Component oldComponent = path.get(0);

        for (int i = 1; i < path.size(); i++) {
            if (!(path.get(i) instanceof Group))
                throw new IllegalArgumentException("Path contains non-group parents: " + path);
            parent = (Group) path.get(i);
            newComponent = parent.swap(oldComponent, newComponent);
            oldComponent = parent;
        }

        this.design = design.swap(parent, (Group) newComponent);
        notifyObservers();
    }

    @Override public void add(PStack<Component> path, Component component) {
        final Design design = this.design;

        while (!(path.get(0) instanceof Group)) {
            if (path.isEmpty()) throw new IllegalArgumentException("Not a valid path for adding: " + path);
            path = path.subList(1);
        }

        Group parent = (Group) path.get(0);
        final Group newParent = parent.add(component);
        this.design = design.swap(parent, newParent);
        notifyObservers();
    }
}
