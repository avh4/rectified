package net.avh4.tools.rectified.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.avh4.framework.uilayer.swing.SwingSceneRenderer;
import net.avh4.tools.rectified.*;
import net.avh4.tools.rectified.model.*;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.cqrs.DataQuery;
import org.pcollections.ConsPStack;
import org.pcollections.PStack;
import org.pcollections.TreePVector;
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
        pico.removeComponent(MutableDataModel.class);
        pico.addComponent(new MutableDataModel(createEmptyDesign()));
        navPanel = pico.getComponent(NavPanel.class);
        designPanel = pico.getComponent(DesignPanel.class);
        overlayPanel = pico.getComponent(OverlayPanel.class);
        editPanel = pico.getComponent(EditPanel.class);
        dataQuery = pico.getComponent(DataQuery.class);
    }

    private Design createEmptyDesign() {
        Component background = new ColorComponent(net.avh4.framework.uilayer.Color.fromHSL(180, 0.5, 0.5));
        Group mainComponent = new FullGroup(TreePVector.singleton(background));
        return new Design(mainComponent);
    }

    @When("^I set the background color of the main region$")
    public void I_set_the_background_color_of_the_main_region() throws Throwable {
        selectComponent(0);
        editPanel.actions().setColor(0xffDC143C);
    }

    @When("^I add a constant-size region to the top$")
    public void I_add_a_constant_size_region_to_the_top() throws Throwable {
        selectComponent(0);
        editPanel.actions().setColor(0xffDC143C);
        selectComponent(0);
        overlayPanel.actions().addPlacement(OverlayPanel.Edge.TOP, 48);
        selectComponent(1, 0);
        editPanel.actions().setColor(0xff7171C6);
    }

    private void selectComponent(int... indexPath) {
        Component last = dataQuery.design().mainComponent();
        PStack<Component> path = ConsPStack.singleton(last);
        for (int index : indexPath) {
            last = ((Group) last).children().get(index);
            path = path.plus(last);
        }
        navPanel.actions().select(path);
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
