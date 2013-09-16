package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.Color;
import net.avh4.tools.rectified.model.cqrs.DataCommands;
import net.avh4.tools.rectified.model.cqrs.DataQuery;
import net.avh4.tools.rectified.model.placement.TopPlacement;
import net.avh4.util.Observable;
import org.pcollections.ConsPStack;
import org.pcollections.PStack;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

public class MutableDataModel extends Observable<Design> implements DataQuery, DataCommands {
    private Design design;

    public MutableDataModel() {
        Component background = new ColorComponent(Color.fromHSL(180, 0.5, 0.5));
        Component header = new PlacementComponent(new TopPlacement(48), TreePVector.<Component>singleton(new ColorComponent(Color.fromHSL(15, 0.5, 0.5))), TreePVector.<Component>empty());
        Group mainComponent = new FullGroup(TreePVector.singleton(background).plus(header));
        this.design = new Design(mainComponent);
    }

    public MutableDataModel(Design design) {
        this.design = design;
    }

    @Override protected Design getObservedValue() {
        return design;
    }

    @Deprecated // use watch instead
    @Override public Design design() {
        return design;
    }

    @Override public PStack<Component> replace(PStack<Component> path, Component newComponent) {
        final Design design = this.design;

        Group parent = null;
        Component oldComponent = path.get(0);

        PVector<Component> newPath = TreePVector.singleton(newComponent);

        for (int i = 1; i < path.size(); i++) {
            if (!(path.get(i) instanceof Group))
                throw new IllegalArgumentException("Path contains non-group parents: " + path);
            parent = (Group) path.get(i);
            newComponent = parent.swap(oldComponent, newComponent);
            oldComponent = parent;
            newPath = newPath.plus(newComponent);
        }

        if (parent == null) parent = (Group) path.get(0);

        this.design = design.swap(parent, (Group) newComponent);
        notifyObservers();
        return ConsPStack.from(newPath);
    }

    @Override public PStack<Component> add(PStack<Component> path, Component component) {
        path = findNearestGroup(path);

        Group parent = (Group) path.get(0);
        final Group newParent = parent.add(component);
        return replace(path, newParent);
    }

    private PStack<Component> findNearestGroup(PStack<Component> path) {
        while (!(path.get(0) instanceof Group)) {
            if (path.isEmpty()) throw new IllegalArgumentException("Path contains no groups: " + path);
            path = path.subList(1);
        }
        return path;
    }
}
