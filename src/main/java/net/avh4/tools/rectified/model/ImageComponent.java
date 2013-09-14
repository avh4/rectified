package net.avh4.tools.rectified.model;

import net.avh4.framework.uilayer.Image;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

import java.io.IOException;

public class ImageComponent implements Component {
    private final String filename;
    private final Image image;

    public ImageComponent(String filename) throws IOException {
        this.filename = filename;
        this.image = UILayer.loadImageFile(filename);
    }

    @Override public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawImage(image, bounds);
    }

    @Override public String navString() {
        return "Image (" + filename + ")";
    }

    @Override public String toString() {
        return "ImageComponent{" +
                "filename='" + filename + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageComponent that = (ImageComponent) o;

        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return filename != null ? filename.hashCode() : 0;
    }
}
