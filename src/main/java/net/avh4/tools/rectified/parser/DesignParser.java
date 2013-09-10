package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import org.json.JSONArray;
import org.json.JSONObject;

public class DesignParser {
    private final ComponentParser componentParser;

    public DesignParser(ComponentParser componentParser) {
        this.componentParser = componentParser;
    }

    public Design parse(String code) throws InvalidCodeException {
        try {
            final JSONArray design = new JSONObject(code).getJSONArray("design");
            Component[] components = new Component[design.length()];
            for (int i = 0; i < components.length; i++) {
                components[i] = componentParser.parse(design.getString(i));
            }
            return new Design(components);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new InvalidCodeException(e);
        }
    }
}
