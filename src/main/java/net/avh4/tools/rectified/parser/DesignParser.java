package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.Design;
import net.avh4.util.lisp.LispParser;

public class DesignParser {
    private final LispParser parser;

    public DesignParser(RectifiedLispParser parser) {
        this.parser = parser;
    }

    public Design parse(String code) throws InvalidCodeException {
        final Object o;
        try {
            o = parser.parse(code);
        } catch (RuntimeException e) {
            throw new InvalidCodeException("Parse error", e);
        }
        if (o instanceof Design) {
            return (Design) o;
        } else {
            throw new InvalidCodeException("Didn't recognize " + code + " as a Design\n  got: " + o);
        }
    }
}
