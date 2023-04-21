package cs5800.sofwareEngineering.hw4.model.character;

import cs5800.sofwareEngineering.hw4.model.font.FontColor;
import cs5800.sofwareEngineering.hw4.model.font.FontFamily;
import java.util.HashMap;
import java.util.Map;

import static cs5800.sofwareEngineering.hw4.model.character.CharacterText.*;

public class CharacterTextFactory {
    private static Map<String, CharacterText> CHARACTER_BUCKET = new HashMap<>();

    private CharacterTextFactory() {

    }

    // input is like: [char::FONT_FAMILY::COLOR::SIZE]
    public static CharacterText parseCharacterAndBuildCharacterText(String serializedCharacter) {
        if (serializedCharacter == null || serializedCharacter.isEmpty()) {
            return null;
        }

        if (CHARACTER_BUCKET.containsKey(serializedCharacter)) {
            return CHARACTER_BUCKET.get(serializedCharacter);
        }

        // characters serialized as [char::FONT_FAMILY::COLOR::SIZE]
        String removedOpenAndClosedBrackets = serializedCharacter.replace(OPEN_BRACKET + "","").replace(CLOSED_BRACKET + "", "");
        Object [] characterProperties = removedOpenAndClosedBrackets.split(DELIMITER);

        Character character = ((String) characterProperties[0]).charAt(0);
        FontFamily fontFamily = FontFamily.createFont((String) characterProperties[1]);
        FontColor fontColor = FontColor.createFontColor((String) characterProperties[2]);
        int fontSize = Integer.parseInt((String) characterProperties[3]);
        CharacterText newCharacter = new CharacterText(character, fontColor, fontFamily, fontSize);

        // put in the cache
        CHARACTER_BUCKET.put(serializedCharacter, newCharacter);

        return newCharacter;
    }

}
