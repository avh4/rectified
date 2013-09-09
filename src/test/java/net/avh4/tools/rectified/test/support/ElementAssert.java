package net.avh4.tools.rectified.test.support;

import net.avh4.framework.uilayer.Element;
import net.avh4.util.imagecomparison.hamcrest.ImageComparisonMatchers;
import net.avh4.util.reflection.StackUtils;
import org.assertj.core.api.AbstractAssert;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.io.IOException;

public class ElementAssert extends AbstractAssert<ElementAssert, Element> {
    protected ElementAssert(Element actual) {
        super(actual, ElementAssert.class);
    }

    public ElementAssert looksLike(String resourceName) {
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
}
