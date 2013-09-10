package net.avh4.tools.rectified;

import net.avh4.tools.rectified.swing.RectifiedAppView;
import net.avh4.tools.rectified.swing.SwingModule;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class Rectified {

    public static void main(String[] args) {
        MutablePicoContainer pico = new DefaultPicoContainer();
        ApplicationModule.configure(pico);
        SwingModule.configure(pico);

        RectifiedAppView window = pico.getComponent(RectifiedAppView.class);
        window.display();
    }
}
