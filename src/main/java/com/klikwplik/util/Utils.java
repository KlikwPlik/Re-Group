package com.klikwplik.util;

import java.util.Random;

public class Utils {

    public static double randomLatitude() {
        return Constants.Map.SOUTH_BOUNDARY + new Random().nextDouble() * (Constants.Map.NORTH_BOUNDARY - Constants.Map.SOUTH_BOUNDARY);
    }

    public static double randomLongitude() {
        return Constants.Map.WEST_BOUNDARY + new Random().nextDouble() * (Constants.Map.WEST_BOUNDARY - Constants.Map.EAST_BOUNDARY);
    }
}
