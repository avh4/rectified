package net.avh4.tools.rectified.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.avh4.framework.uilayer.swing.SwingSceneRenderer;
import net.avh4.tools.rectified.ApplicationModule;
import net.avh4.tools.rectified.RectifiedApp;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import javax.swing.*;
import java.awt.*;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;


@SuppressWarnings("UnusedDeclaration")
public class Steps {
    private Agent agent = new Agent();
    private RectifiedApp app;

    @Given("^a new design")
    public void a_new_project() throws Throwable {
        MutablePicoContainer pico = new DefaultPicoContainer();
        ApplicationModule.configure(pico);
        app = pico.getComponent(RectifiedApp.class);
    }

    @When("^I set the background color of the main region$")
    public void I_set_the_background_color_of_the_main_region() throws Throwable {
        app.codePanel().actions().replaceCode("" +
                "(design ((color (rgb \"#DC143C\"))))");
    }

    @When("^I add a constant-size region to the top$")
    public void I_add_a_constant_size_region_to_the_top() throws Throwable {
        app.codePanel().actions().replaceCode("" +
                "(design ((color (rgb \"#DC143C\")) (placement (top 48) ((color (rgb \"#7171C6\"))) ()))");
    }

    @Then("^I see the new background color in the preview$")
    public void I_see_the_new_background_color_in_the_preview() throws Throwable {
        JComponent p = new SwingSceneRenderer(app.designPanel()) {
            @Override public Dimension getPreferredSize() {
                return new Dimension(320, 508);
            }
        };
        assertThat(p).looksLike("crimson_mdpi.png");
    }

    @Then("^I see the new region$")
    public void I_see_the_new_region() throws Throwable {
        JComponent p = new SwingSceneRenderer(app.designPanel()) {
            @Override public Dimension getPreferredSize() {
                return new Dimension(320, 508);
            }
        };
        assertThat(p).looksLike("top_48_mdpi.png");
    }
}
