package net.avh4.tools.rectified.swing;

import net.avh4.tools.rectified.EditPanel;
import net.avh4.tools.rectified.Observables;
import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.framework.uilayer.mvc.Observer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class EditPanelView extends JPanel implements Observer {
    private final Channel<SelectionQuery> selection;
    private boolean changing;
    private final JColorChooser chooser;

    public EditPanelView(Observables observables, final EditPanel panel) {
        chooser = new JColorChooser(Color.BLUE);
        add(chooser);

        chooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override public void stateChanged(ChangeEvent e) {
                if (changing) return;
                final int argb = chooser.getColor().getRGB();
                panel.actions().setColor(argb);
            }
        });

        selection = observables.selection();
        observables.selection().watch(this);
    }

    @Override public void update() {
        final Component component = selection.get().selectedComponent();
        if (component instanceof ColorComponent) {
            changing = true;
            chooser.setColor(((ColorComponent) component).color());
            changing = false;
            chooser.setVisible(true);
        } else {
            chooser.setVisible(false);
        }
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }
}
