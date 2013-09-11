package net.avh4.tools.rectified.parser;

import net.avh4.tools.rectified.InvalidCodeException;

public class ColorParser {

    public int parse(String color) throws InvalidCodeException {
        if (color == null) {
            return 0xff333333;
        } else if (color.charAt(0) == '#' && color.length() == 7) {
            return 0xff000000 | Integer.parseInt(color.substring(1), 16);
        } else if (color.charAt(0) == '#' && color.length() == 4) {
            int base = Integer.parseInt(color.substring(1, 2), 16) << (4 * 4)
                    | Integer.parseInt(color.substring(2, 3), 16) << (4 * 2)
                    | Integer.parseInt(color.substring(3, 4), 16);
            return 0xff000000 | base << 4 | base;
        } else {
            throw new InvalidCodeException("Invalid color: " + color + "\nExpected something like #123456 or #123");
        }
    }
}
