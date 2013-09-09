package net.avh4.tools.rectified.test.support;

import net.avh4.framework.uilayer.Element;

import javax.swing.*;

public class Assertions extends org.assertj.core.api.Assertions {
    public static ElementAssert assertThat(Element actual) {
        return new ElementAssert(actual);
    }

    public static JComponentAssert assertThat(JComponent actual) {
        return new JComponentAssert(actual);
    }
}
