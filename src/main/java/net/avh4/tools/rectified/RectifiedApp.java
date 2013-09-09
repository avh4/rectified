package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.Element;

public class RectifiedApp {

    private final CodePanel codePanel;
    private final DesignPanel designPanel;

    public RectifiedApp(CodePanel codePanel, DesignPanel designPanel) {
        this.codePanel = codePanel;
        this.designPanel = designPanel;
    }

    public CodePanel codePanel() {
        return codePanel;
    }

    public Element designPanel() {
        return designPanel;
    }
}
