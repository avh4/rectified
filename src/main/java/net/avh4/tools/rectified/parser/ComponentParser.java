package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.placement.Placement;
import net.avh4.tools.rectified.model.placement.PlacementComponent;
import org.json.JSONException;
import org.json.JSONObject;

public class ComponentParser {

    private ColorParser colorParser;
    private final PlacementParser placementParser;

    public ComponentParser(ColorParser colorParser, PlacementParser placementParser) {
        this.colorParser = colorParser;
        this.placementParser = placementParser;
    }

    public Component parse(String json) throws InvalidCodeException {
        try {
            final JSONObject o = new JSONObject(json);
            if (o.has("color")) {
                return new ColorComponent(colorParser.parse(o.getString("color")));
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
}
