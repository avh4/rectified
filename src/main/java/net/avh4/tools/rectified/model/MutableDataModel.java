package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.Color;
import net.avh4.tools.rectified.model.cqrs.DataQuery;
import net.avh4.util.Observable;
import org.pcollections.TreePVector;

public class MutableDataModel extends Observable<Design> implements DataQuery {
    private Design design;

    public MutableDataModel() {
        Component mainComponent = new ColorComponent(Color.fromHSL(60, 0.5, 0.5));
        this.design = new Design(TreePVector.singleton(mainComponent));
    }

    public void swapDesign(Design design, Design newDesign) {
        this.design = newDesign;
        notifyObservers();
    }

    @Override protected Design getObservedValue() {
        return design;
    }

    @Deprecated // use watch instead
    @Override public Design design() {
        return design;
    }
}
