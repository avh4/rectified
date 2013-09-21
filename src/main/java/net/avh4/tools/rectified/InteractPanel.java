package net.avh4.tools.rectified;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.PlacementComponent;
import net.avh4.tools.rectified.model.placement.*;
import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.tools.rectified.uimodel.cqrs.AppCommands;
import net.avh4.tools.rectified.uimodel.cqrs.SelectionQuery;
import net.avh4.framework.uilayer.mvc.Observer;
import org.pcollections.PStack;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

public class InteractPanel implements UI, Observer {
    private final Channel<SelectionQuery> selection;
    private Rect selectionBounds;
    private Region hoverRegion;
    private AppCommands appCommands;
    private PStack<Component> selectionPath;

    private enum Region {
        LEFT, CENTER, RIGHT, TOP, BOTTOM;

        public Rect highlight(Rect selectionBounds) {
            if (this == LEFT) return selectionBounds.leftPercent(0.3);
            if (this == RIGHT) return selectionBounds.rightPercent(0.3);
            if (this == TOP) return selectionBounds.topPercent(0.3);
            if (this == BOTTOM) return selectionBounds.bottomPercent(0.3);
            return selectionBounds;
        }

        public static Region find(Rect bounds, Point p) {
            if (bounds.topPercent(0.3).contains(p)) return TOP;
            if (bounds.bottomPercent(0.3).contains(p)) return BOTTOM;
            if (bounds.leftPercent(0.3).contains(p)) return LEFT;
            if (bounds.rightPercent(0.3).contains(p)) return RIGHT;
            if (bounds.contains(p)) return CENTER;
            return null;
        }

        public Placement placement(int dp) {
            if (this == LEFT) return new LeftPlacement(dp);
            if (this == RIGHT) return new RightPlacement(dp);
            if (this == TOP) return new TopPlacement(dp);
            if (this == BOTTOM) return new BottomPlacement(dp);
            return null;
        }
    }

    public InteractPanel(Observables observables, AppCommands appCommands) {
        this.appCommands = appCommands;
        this.selection = observables.selection();
        observables.selection().watch(this);
    }

    @Override public void update() {
        SelectionQuery newValue = selection.get();
        selectionBounds = newValue.selectionBounds();
        hoverRegion = null;
        selectionPath = newValue.path();
    }

    @Override public void click(Rect bounds, Point p) {
        move(bounds, p);
        PVector<Component> remainderComponents = TreePVector.empty();
        PVector<Component> components = TreePVector.<Component>singleton(new ColorComponent(Color.RED));
        final PlacementComponent pc = new PlacementComponent(hoverRegion.placement(48), components, remainderComponents);
        appCommands.add(selectionPath, pc);
    }

    @Override public void move(Rect bounds, Point p) {
        if (selectionBounds != null) {
            hoverRegion = Region.find(selectionBounds, p);
        }
    }

    @Override public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        if (hoverRegion != null) {
            final Rect b;
            b = hoverRegion.highlight(selectionBounds).inset(3);
            g.drawRect(b, Color.fromWA(0.3, 0.3));
        }
    }

    @Override public void key(int keyCode, boolean shift) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override public UpdateAction time() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
