package net.avh4.tools.rectified;

import org.picocontainer.MutablePicoContainer;

public class ApplicationModule {
    public static void configure(MutablePicoContainer pico) {
        pico.addComponent(RectifiedApp.class);
        pico.addComponent(CodePanel.class);
        pico.addComponent(DesignPanel.class);
    }
}
