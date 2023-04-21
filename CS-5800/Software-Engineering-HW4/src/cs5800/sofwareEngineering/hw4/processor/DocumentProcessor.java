package cs5800.sofwareEngineering.hw4.processor;

import cs5800.sofwareEngineering.hw4.model.character.CharacterText;
import cs5800.sofwareEngineering.hw4.model.character.CharacterTextFactory;
import cs5800.sofwareEngineering.hw4.model.document.Document;
import cs5800.sofwareEngineering.hw4.model.font.FontColor;
import cs5800.sofwareEngineering.hw4.model.font.FontFamily;

import java.util.ArrayList;
import java.util.List;

import static cs5800.sofwareEngineering.hw4.model.character.CharacterText.*;

public class DocumentProcessor {

    private DocumentProcessor() {

    }

    public static String buildRawText(List<CharacterText> characterTexts) {
        StringBuilder rawText = new StringBuilder();
        characterTexts.forEach(characterText -> rawText.append(characterText.getCharacter() == SPACE ? ' ' : characterText.getCharacter()));
        return rawText.toString();
    }


    public static String serializeText(String text, FontFamily fontFamily, FontColor color, int size) {
        text = text.trim();
        StringBuilder serializedText =  new StringBuilder();
        int index = 0;

        while(index < text.length()) {
            Character currentCharacter = text.charAt(index);
            if (currentCharacter.equals(' ')) {
                serializedText.append(CharacterText.SPACE);
            }
            else {
                // serialize the character
                serializedText.append(serializeCharacter(currentCharacter, fontFamily, color, size));
            }
            index++;
        }
        return serializedText.toString();
    }

    public static List<CharacterText> deserializeTextAndBuildCharacterTextList(String serializedText) {
        serializedText = serializedText.trim();
        int index = 0;
        StringBuilder currentSerializedCharacter = new StringBuilder();
        List<CharacterText> deserializedCharacters = new ArrayList<>();

        while(index < serializedText.length()) {
            Character currentChar = serializedText.charAt(index);
            if (Character.compare(currentChar, OPEN_BRACKET) == 0) {
                    // flush out the old string
                    currentSerializedCharacter = new StringBuilder();
                    // character begins
                    currentSerializedCharacter.append(currentChar);
            } else if ((Character.compare(currentChar, CLOSED_BRACKET) == 0)) {
                    // character ends
                    currentSerializedCharacter.append(currentChar);
                    // create new character object from the character factory [Flyweight pattern is being used here!!!]
                    deserializedCharacters.add(CharacterTextFactory.parseCharacterAndBuildCharacterText(currentSerializedCharacter.toString()));
            } else if ((Character.compare(currentChar, SPACE) == 0)) {
                    // space [Flyweight pattern is being used here!!!]
                    deserializedCharacters.add(CharacterTextFactory.parseCharacterAndBuildCharacterText("[%::FONT_FAMILY::COLOR::1]"));
            } else {
                // just append the string
                currentSerializedCharacter.append(currentChar);
            }
            index++;
        }
        return deserializedCharacters;
    }

    private static String serializeCharacter(Character character, FontFamily fontFamily, FontColor color, int size) {
        return new StringBuilder().append(OPEN_BRACKET)
                .append(character)
                .append(DELIMITER)
                .append(fontFamily.toString())
                .append(DELIMITER)
                .append(color.toString())
                .append(DELIMITER)
                .append(size)
                .append(CLOSED_BRACKET).toString();
    }
}
