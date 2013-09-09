package net.avh4.tools.rectified;

public class CodePanel {
    private final Listener listener;

    public CodePanel(Listener listener) {
        this.listener = listener;
    }

    public interface Actions {
        void replaceCode(String newCode);
    }

    public interface Listener {
        void codeDidChange(String newCode);
    }

    public Actions actions() {
        return new Actions() {
            @Override public void replaceCode(String newCode) {
                listener.codeDidChange(newCode);
            }
        };
    }
}
