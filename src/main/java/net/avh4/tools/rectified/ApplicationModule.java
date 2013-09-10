package net.avh4.tools.rectified;

import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;

public class ApplicationModule {
    public static void configure(MutablePicoContainer pico) {
        pico.as(Characteristics.CACHE).addComponent(RectifiedApp.class);
        pico.as(Characteristics.CACHE).addComponent(CodePanel.class);
        pico.as(Characteristics.CACHE).addComponent(DesignPanel.class);
        pico.as(Characteristics.CACHE).addComponent(MainController.class);
        pico.as(Characteristics.CACHE).addComponent(AppConfig.class);
        pico.addComponent(DesignParser.class);
    }
}
