package net.avh4.tools.rectified.model;

import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;

import static net.avh4.tools.rectified.test.support.Assertions.assertThat;

public class BottomPlacementTest {

    private BottomPlacement subject;

    @Before
    public void setUp() throws Exception {
        subject = new BottomPlacement(44);
    }

    @Test
    public void shouldPlace() throws Exception {
        assertThat(subject.place(Rect.ofSize(800, 600))).isEqualTo(Rect.fromTopLeft(0, 600 - 44, 800, 44));
    }
}
