package net.avh4.tools.rectified.swing;

import net.avh4.tools.rectified.ErrorPanel;

import javax.swing.*;

public class ErrorPanelView extends JTextArea {
    private final ErrorPanel errorPanel;

    public ErrorPanelView(ErrorPanel errorPanel) {
        this.errorPanel = errorPanel;
    }

    public void reloadData() {
        setText(errorPanel.getMessage());
    }
}
