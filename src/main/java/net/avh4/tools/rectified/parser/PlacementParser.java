package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;
import net.avh4.tools.rectified.model.placement.*;
import org.json.JSONException;
import org.json.JSONObject;

public class PlacementParser {
    public Placement parse(String json) throws InvalidCodeException {
        try {
            final JSONObject o = new JSONObject(json);
            if (o.has("top")) {
                return new TopPlacement(o.getInt("top"));
            } else if (o.has("bottom")) {
                return new BottomPlacement(o.getInt("bottom"));
            } else if (o.has("right")) {
                return new RightPlacement(o.getInt("right"));
            } else if (o.has("left")) {
                return new LeftPlacement(o.getInt("left"));
            } else {
                throw new InvalidCodeException("Invalid placement: " + json);
            }
        } catch (JSONException e) {
            throw new InvalidCodeException(e);
        }
    }
}
