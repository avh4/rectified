package net.avh4.tools.rectified.model.cqrs;

import net.avh4.tools.rectified.model.Design;
import net.avh4.util.Observer;

public interface DataQuery {
    @Deprecated // use watch instead
    Design design();

    @Deprecated // use Observables instead
    void watch(Observer<Design> observer);
}
