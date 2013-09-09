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

    @Given("^a new project$")
    public void a_new_project() throws Throwable {
        MutablePicoContainer pico = new DefaultPicoContainer();
        ApplicationModule.configure(pico);
        app = pico.getComponent(RectifiedApp.class);
    }

    @When("^I set the background color of the main region$")
    public void I_set_the_background_color_of_the_main_region() throws Throwable {
        app.codePanel().actions().replaceCode("" +
                "{\n" +
                "    \"design\": [\n" +
                "        {\n" +
                "            \"remainder\": true,\n" +
                "            \"background\": \"#DC143C\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
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
}
