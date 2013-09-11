package net.avh4.tools.rectified;

import net.avh4.tools.rectified.parser.*;
import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;

public class ApplicationModule {
    public static void configure(MutablePicoContainer pico) {
        pico.as(Characteristics.CACHE).addComponent(RectifiedApp.class);
        pico.as(Characteristics.CACHE).addComponent(CodePanel.class);
        pico.as(Characteristics.CACHE).addComponent(DesignPanel.class);
        pico.as(Characteristics.CACHE).addComponent(MainController.class);
        pico.as(Characteristics.CACHE).addComponent(AppConfig.class);

        pico.as(Characteristics.CACHE).addComponent(ComponentParser.class);
        pico.as(Characteristics.CACHE).addComponent(ColorParser.class);
        pico.as(Characteristics.CACHE).addComponent(PlacementParser.class);
        pico.as(Characteristics.CACHE).addComponent(DesignParser.class);
        pico.as(Characteristics.CACHE).addComponent(FontParser.class);
    }
}
