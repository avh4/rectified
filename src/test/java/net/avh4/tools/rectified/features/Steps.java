package net.avh4.tools.rectified.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.avh4.framework.uilayer.swing.SwingSceneRenderer;
import net.avh4.tools.rectified.*;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.cqrs.DataQuery;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import javax.swing.*;
import java.awt.*;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;


@SuppressWarnings("UnusedDeclaration")
public class Steps {
    private Agent agent = new Agent();
    private OverlayPanel overlayPanel;
    private NavPanel navPanel;
    private EditPanel editPanel;
    private DesignPanel designPanel;
    private DataQuery dataQuery;

    @Given("^a new design")
    public void a_new_project() throws Throwable {
        MutablePicoContainer pico = new DefaultPicoContainer();
        ApplicationModule.configure(pico);
        navPanel = pico.getComponent(NavPanel.class);
        designPanel = pico.getComponent(DesignPanel.class);
        overlayPanel = pico.getComponent(OverlayPanel.class);
        editPanel = pico.getComponent(EditPanel.class);
        dataQuery = pico.getComponent(DataQuery.class);
    }

    @When("^I set the background color of the main region$")
    public void I_set_the_background_color_of_the_main_region() throws Throwable {
        final Component component = dataQuery.design().components().get(0);
        navPanel.actions().select(component);
        editPanel.actions().setColor(0xffDC143C);
    }

    @When("^I add a constant-size region to the top$")
    public void I_add_a_constant_size_region_to_the_top() throws Throwable {
        final Component component = dataQuery.design().components().get(0);
        navPanel.actions().select(component);
        editPanel.actions().setColor(0xffDC143C);
        overlayPanel.actions().addPlacement(OverlayPanel.Edge.TOP, 48);
        final PlacementComponent parent = (PlacementComponent) dataQuery.design().components().get(1);
        navPanel.actions().select(parent, parent.components().get(0));
        editPanel.actions().setColor(0xff7171C6);
    }

    @Then("^I see the new background color in the preview$")
    public void I_see_the_new_background_color_in_the_preview() throws Throwable {
        JComponent p = new SwingSceneRenderer(designPanel) {
            @Override public Dimension getPreferredSize() {
                return new Dimension(320, 508);
            }
        };
        assertThat(p).looksLike("crimson_mdpi.png");
    }

    @Then("^I see the new region$")
    public void I_see_the_new_region() throws Throwable {
        JComponent p = new SwingSceneRenderer(designPanel) {
            @Override public Dimension getPreferredSize() {
                return new Dimension(320, 508);
            }
        };
        assertThat(p).looksLike("top_48_mdpi.png");
    }
}
