package net.avh4.tools.rectified.test.support;

import net.avh4.util.imagecomparison.hamcrest.ImageComparisonMatchers;
import net.avh4.util.reflection.StackUtils;
import org.assertj.core.api.AbstractAssert;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import javax.swing.*;
import java.io.IOException;

public class JComponentAssert extends AbstractAssert<JComponentAssert, JComponent> {
    protected JComponentAssert(JComponent actual) {
        super(actual, JComponentAssert.class);
    }

    public JComponentAssert looksLike(String resourceName) {
        try {
            final Class<?> callingClass = StackUtils.getCallingClass();
            final Matcher<Object> matcher = ImageComparisonMatchers.looksLike(resourceName, callingClass);
            if (!matcher.matches(actual)) {
                Description d = new StringDescription();
                matcher.describeMismatch(actual, d);
                failWithMessage("Expected <%s> to look like <%s>, but %s", actual, resourceName, d);
            }
        } catch (IOException e) {
            failWithMessage("Expected <%s> to look like <%s>, but got IOException\n%s", actual, resourceName, e);
        }
        return this;
    }

    public void isApproved() {
        try {
            final Matcher<Object> matcher = ImageComparisonMatchers.isApproved();
            if (!matcher.matches(actual)) {
                Description d = new StringDescription();
                matcher.describeMismatch(actual, d);
                failWithMessage("Expected <%s> to be approved, but %s", actual, d);
            }
        } catch (IOException e) {
            failWithMessage("Expected <%s> to be approved, but got IOException\n%s", actual, e);
        }
    }
}
