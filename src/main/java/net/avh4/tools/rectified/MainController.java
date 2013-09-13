package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.Design;
import net.avh4.tools.rectified.parser.DesignParser;

public class MainController implements CodePanel.Listener {
    private final DesignPanel designPanel;
    private final DesignParser designParser;
    private final ErrorPanel errorPanel;

    public MainController(DesignPanel designPanel, DesignParser designParser, ErrorPanel errorPanel) {
        this.designPanel = designPanel;
        this.designParser = designParser;
        this.errorPanel = errorPanel;
    }

    @Override public void codeDidChange(String newCode) {
        final Design design;
        try {
            design = designParser.parse(newCode);
            designPanel.setDesign(design);
            errorPanel.clearError();
        } catch (InvalidCodeException e) {
            errorPanel.setError(e);
        }
    }
}
