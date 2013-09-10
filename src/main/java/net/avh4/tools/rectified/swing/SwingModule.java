package net.avh4.tools.rectified.swing;

import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;

public class SwingModule {
    public static void configure(MutablePicoContainer pico) {
        pico.as(Characteristics.CACHE).addComponent(RectifiedAppView.class);
        pico.as(Characteristics.CACHE).addComponent(CodePanelView.class);
        pico.as(Characteristics.CACHE).addComponent(DesignPanelView.class);
    }
}
