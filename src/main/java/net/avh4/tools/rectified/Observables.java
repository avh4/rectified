package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Design;
import net.avh4.tools.rectified.model.MutableDataModel;
import net.avh4.tools.rectified.uimodel.MutableSelectionModel;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.util.Observable;

public class Observables {
    private final Observable<Design> design;
    private final Observable<SelectionQuery> selection;

    public Observables(MutableDataModel design, MutableSelectionModel selection) {
        this.design = design;
        this.selection = selection;
    }

    public Observable<Design> design() {
        return design;
    }

    public Observable<SelectionQuery> selection() {
        return selection;
    }
}
