package net.avh4.tools.rectified.swing;

import net.avh4.tools.rectified.CodePanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class CodePanelView extends JTextArea {
    private final CodePanel codePanel;
    private final JComponent designPanel;
    private ErrorPanelView errorPanel;

    public CodePanelView(final CodePanel codePanel, final DesignPanelView designPanel, ErrorPanelView errorPanel) {
        this.codePanel = codePanel;
        this.designPanel = designPanel;
        this.errorPanel = errorPanel;

        this.setMargin(new Insets(20, 20, 20, 20));
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) {
                refreshDesign();
            }

            @Override public void removeUpdate(DocumentEvent e) {
                refreshDesign();
            }

            @Override public void changedUpdate(DocumentEvent e) {
                refreshDesign();
            }
        });
    }

    private void refreshDesign() {
        codePanel.actions().replaceCode(this.getText());
        designPanel.repaint();
        errorPanel.reloadData();
        errorPanel.repaint();
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}
