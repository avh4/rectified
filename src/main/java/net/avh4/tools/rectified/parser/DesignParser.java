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
            final Component component = componentParser.parse(design.getString(0));
            return new Design(component);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new InvalidCodeException(e);
        }
    }
}
