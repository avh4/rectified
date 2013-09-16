package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.MutableDataModel;
import net.avh4.tools.rectified.parser.ColorParser;
import net.avh4.tools.rectified.parser.FontParser;
import net.avh4.tools.rectified.uimodel.MutableSelectionModel;
import net.avh4.tools.rectified.uimodel.cqrs.AppCommands;
import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;

public class ApplicationModule {
    public static void configure(MutablePicoContainer pico) {
        pico.as(Characteristics.CACHE).addComponent(DesignPanel.class);
        pico.as(Characteristics.CACHE).addComponent(NavPanel.class);
        pico.as(Characteristics.CACHE).addComponent(EditPanel.class);
        pico.as(Characteristics.CACHE).addComponent(OverlayPanel.class);
        pico.as(Characteristics.CACHE).addComponent(InteractPanel.class);
        pico.as(Characteristics.CACHE).addComponent(AppConfig.class);
        pico.as(Characteristics.CACHE).addComponent(MutableSelectionModel.class);
        pico.as(Characteristics.CACHE).addComponent(MutableDataModel.class);
        pico.as(Characteristics.CACHE).addComponent(AppCommands.class);
        pico.as(Characteristics.CACHE).addComponent(Observables.class);

        pico.as(Characteristics.CACHE).addComponent(ColorParser.class);
        pico.as(Characteristics.CACHE).addComponent(FontParser.class);
    }
}
