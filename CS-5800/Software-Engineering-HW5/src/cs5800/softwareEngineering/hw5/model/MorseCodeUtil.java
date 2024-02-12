package cs5800.softwareEngineering.hw5.model;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeUtil {
    private static Map<String, String> MORSE_CODE = new HashMap<>();

    public static Map<String, String> getMorseCodeCharacters() {
        // use the map to build the morse code mapping by alphabet
        return MORSE_CODE;
    }
}
