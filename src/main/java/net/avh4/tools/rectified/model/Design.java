package net.avh4.tools.rectified.model;

public class Design {
    private final ColorComponent mainComponent;

    public Design(ColorComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    public Component getMainComponent() {
        return mainComponent;
    }
}
