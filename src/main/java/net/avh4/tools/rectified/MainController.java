package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Design;

public class MainController implements CodePanel.Listener {
    private final DesignPanel designPanel;
    private final DesignParser designParser;

    public MainController(DesignPanel designPanel, DesignParser designParser) {
        this.designPanel = designPanel;
        this.designParser = designParser;
    }

    @Override public void codeDidChange(String newCode) {
        final Design design;
        try {
            design = designParser.parse(newCode);
            designPanel.setDesign(design);
        } catch (InvalidCodeException e) {
            // do nothing
        }
    }
}
