package net.avh4.tools.rectified.parser;

import net.avh4.framework.uilayer.Font;
import net.avh4.test.junit.Nested;
import net.avh4.tools.rectified.model.*;
import net.avh4.tools.rectified.model.placement.*;
import net.avh4.util.lisp.ObjectFactory;
import net.avh4.util.lisp.Symbol;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;
import static org.mockito.Mockito.stub;

@RunWith(Nested.class)
public class RectifiedLispContextTest {
    private RectifiedLispContext subject;
    @Mock private ColorParser colorParser;
    @Mock private FontParser fontParser;
    @Mock private Iterable<Component> components;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new RectifiedLispContext(colorParser, fontParser);
    }

    @Test
    public void shouldParseDesign() throws Exception {
        checkParse(new Design(components), "design", components);
    }

    public class Components {
        private static final int color = 0x123123;
        @Mock private Font font;
        @Mock private Placement placement;
        @Mock private Iterable<Component> components;

        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void shouldParseColorComponent() throws Exception {
            checkParse(new ColorComponent(color), "color", color);
        }

        @Test
        public void shouldParseTextComponent() throws Exception {
            checkParse(new TextComponent("Text", font, color, 5), "text", "Text", font, color, 5);
        }

        @Test
        public void shouldParsePlacementComponent() throws Exception {
            checkParse(new PlacementComponent(placement, components), "placement", placement, components);
        }

        @Test
        public void shouldParseImageComponent() throws Exception {
            checkParse(new ImageComponent("examples/star_mdpi.png"), "image", "examples/star_mdpi.png");
        }
    }

    public class Placements {
        @Test
        public void shouldParseTopPlacement() throws Exception {
            checkParse(new TopPlacement(48), "top", 48);
        }

        @Test
        public void shouldParseBottomPlacement() throws Exception {
            checkParse(new BottomPlacement(48), "bottom", 48);
        }

        @Test
        public void shouldParseLeftPlacement() throws Exception {
            checkParse(new LeftPlacement(48), "left", 48);
        }

        @Test
        public void shouldParseRightPlacement() throws Exception {
            checkParse(new RightPlacement(48), "right", 48);
        }
    }

    public class Colors {
        private int c1 = 0x567567;

        @Test
        public void shouldParseRgb() throws Exception {
            stub(colorParser.parse("#567567")).toReturn(c1);
            checkParse(c1, "rgb", "#567567");
        }
    }

    public class Fonts {
        @Mock private Font font;

        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void shouldParseDefaultFont() throws Exception {
            stub(fontParser.parse(null)).toReturn(font);
            checkParse(font, "font");
        }
    }

    private <T> void checkParse(T expected, String name, Object... args) {
        final Symbol symbol = Symbol.s(name);
        Object[] params = new Object[args.length + 1];
        params[0] = symbol;
        System.arraycopy(args, 0, params, 1, args.length);
        final ObjectFactory factory = subject.get(symbol);
        assertThat(factory).as("No factory mapped for '" + name + "'").isNotNull();
        final Object o = factory.create(params);
        assertThat(o).isEqualTo(expected);
    }
}
