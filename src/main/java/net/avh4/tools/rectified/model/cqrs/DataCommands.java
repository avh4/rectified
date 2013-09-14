package net.avh4.tools.rectified.model.cqrs;

import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import net.avh4.tools.rectified.model.Group;
import net.avh4.tools.rectified.model.MutableDataModel;

public class DataCommands {
    private final MutableDataModel dataModel;

    public DataCommands(MutableDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void replace(Group parent, Component oldComponent, Component newComponent) {
        if (parent == null) {
            final Design design = dataModel.design();
            final Design newDesign = design.swap(oldComponent, newComponent);
            dataModel.swapDesign(design, newDesign);
        } else {
            final Design design = dataModel.design();
            final Group newParent = parent.swap(oldComponent, newComponent);
            final Design newDesign = design.swap(parent, newParent);
            dataModel.swapDesign(design, newDesign);
        }
    }

    public void add(Component component) {
        final Design design = dataModel.design();
        final Design newDesign = design.add(component);
        dataModel.swapDesign(design, newDesign);
    }
}
