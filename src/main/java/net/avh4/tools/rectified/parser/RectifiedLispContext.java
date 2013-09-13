package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Design;
import net.avh4.tools.rectified.model.ImageComponent;
import net.avh4.tools.rectified.model.TextComponent;
import net.avh4.tools.rectified.model.placement.*;
import net.avh4.util.lisp.ConstructorObjectFactory;
import net.avh4.util.lisp.LispContext;
import net.avh4.util.lisp.ObjectFactory;
import net.avh4.util.lisp.Symbol;

import java.util.HashMap;

public class RectifiedLispContext implements LispContext {
    private final HashMap<Symbol, ObjectFactory> map = new HashMap<>();

    public RectifiedLispContext(final ColorParser colorParser, final FontParser fontParser) {
        add("design", Design.class);

        add("color", ColorComponent.class);
        add("text", TextComponent.class);
        add("placement", PlacementComponent.class);
        add("image", ImageComponent.class);

        add("top", TopPlacement.class);
        add("bottom", BottomPlacement.class);
        add("left", LeftPlacement.class);
        add("right", RightPlacement.class);

        map.put(Symbol.s("rgb"), new ObjectFactory() {
            @Override public Object create(Object[] args) {
                try {
                    return colorParser.parse((String) args[1]);
                } catch (InvalidCodeException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        map.put(Symbol.s("font"), new ObjectFactory() {
            @Override public Object create(Object[] args) {
                return fontParser.parse(null);
            }
        });
    }

    private void add(String name, Class<?> clazz) {
        map.put(Symbol.s(name), new ConstructorObjectFactory(clazz));
    }

    @Override public ObjectFactory get(Symbol symbol) {
        return map.get(symbol);
    }
}
