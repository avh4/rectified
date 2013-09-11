package net.avh4.tools.rectified.parser;

import net.avh4.framework.uilayer.Font;
import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.TextComponent;
import net.avh4.tools.rectified.model.placement.Placement;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import org.json.JSONException;
import org.json.JSONObject;

public class ComponentParser {

    private ColorParser colorParser;
    private final PlacementParser placementParser;
    private final FontParser fontParser;

    public ComponentParser(ColorParser colorParser, PlacementParser placementParser, FontParser fontParser) {
        this.colorParser = colorParser;
        this.placementParser = placementParser;
        this.fontParser = fontParser;
    }

    public Component parse(String json) throws InvalidCodeException {
        try {
            final JSONObject o = new JSONObject(json);
            if (o.has("text")) {
                String value = get(o, "text");
                int color = getColor(o, "color");
                Font font = fontParser.parse(get(o, "font"));
                int padding = o.has("padding") ? o.getInt("padding") : 0;
                return new TextComponent(value, font, color, padding);
            } else if (o.has("color")) {
                final int color = getColor(o, "color");
                return new ColorComponent(color);
            } else if (o.has("placement")) {
                final Placement placement = placementParser.parse(o.getString("placement"));
                Component[] components = new Component[o.getJSONArray("inside").length()];
                for (int i = 0; i < components.length; i++) {
                    components[i] = parse(o.getJSONArray("inside").getString(i));
                }
                return new PlacementComponent(placement, components);
            } else {
                throw new InvalidCodeException("Invalid component: " + json);
            }
        } catch (JSONException e) {
            throw new InvalidCodeException(e);
        }
    }

    private int getColor(JSONObject o, String attribute) throws InvalidCodeException, JSONException {
        return colorParser.parse(get(o, attribute));
    }

    private String get(JSONObject o, String attribute) throws JSONException {
        return o.has(attribute) ? o.getString(attribute) : null;
    }
}
