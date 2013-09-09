package net.avh4.tools.rectified;

import net.avh4.tools.rectified.model.ColorComponent;
import net.avh4.tools.rectified.model.Design;
import org.json.JSONArray;
import org.json.JSONObject;

public class DesignParser {
    public Design parse(String code) throws InvalidCodeException {
        try {
            final JSONArray design = new JSONObject(code).getJSONArray("design");
            final JSONObject component = design.getJSONObject(0);
            return new Design(new ColorComponent(component.getString("background")));
        } catch (Throwable e) {
            e.printStackTrace();
            throw new InvalidCodeException(e);
        }
    }
}
