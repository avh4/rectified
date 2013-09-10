package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import org.json.JSONException;
import org.json.JSONObject;

public class ComponentParser {

    private ColorParser colorParser;

    public ComponentParser(ColorParser colorParser) {
        this.colorParser = colorParser;
    }

    public Component parse(String json) throws InvalidCodeException {
        try {
            final JSONObject o = new JSONObject(json);
            return new ColorComponent(colorParser.parse(o.getString("color")));
        } catch (JSONException e) {
            throw new InvalidCodeException(e);
        }
    }
}
