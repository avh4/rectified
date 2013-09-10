package net.avh4.tools.rectified.model;

public class Design {
    private final Component[] components;

    public Design(Component[] components) {
        this.components = components;
    }

    public Component[] components() {
        return components;
    }
}
