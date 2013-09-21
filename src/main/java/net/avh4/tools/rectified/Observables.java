package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Design;
import net.avh4.tools.rectified.model.MutableDataModel;
import net.avh4.tools.rectified.uimodel.MutableSelectionModel;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.framework.uilayer.mvc.Channel;

public class Observables {
    private final Channel<Design> design;
    private final Channel<SelectionQuery> selection;

    public Observables(MutableDataModel design, MutableSelectionModel selection) {
        this.design = design;
        this.selection = selection;
    }

    public Channel<Design> design() {
        return design;
    }

    public Channel<SelectionQuery> selection() {
        return selection;
    }
}
