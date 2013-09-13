package net.avh4.tools.rectified;

public class ErrorPanel {
    private Exception error;

    public void setError(Exception e) {
        this.error = e;
    }

    public void clearError() {
        this.error = null;
    }

    public String getMessage() {
        if (error == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(error.getLocalizedMessage());
        if (error.getCause() != null) {
            sb.append("\n");
            sb.append(error.getCause().getLocalizedMessage());
        }
        return sb.toString();
    }
}
