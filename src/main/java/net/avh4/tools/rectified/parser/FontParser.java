package net.avh4.tools.rectified.parser;

import net.avh4.framework.uilayer.Font;

public class FontParser {
    public Font parse(int pt) {
        return Font.OPEN_SANS.size(pt);
    }
}
