package cs5800.sofwareEngineering.hw4.driver;


import cs5800.sofwareEngineering.hw4.model.character.CharacterText;
import cs5800.sofwareEngineering.hw4.model.document.Document;
import cs5800.sofwareEngineering.hw4.model.font.FontColor;
import cs5800.sofwareEngineering.hw4.model.font.FontFamily;
import cs5800.sofwareEngineering.hw4.processor.DocumentProcessor;

import java.util.List;

public class Question1Driver {
    public static void main(String[] args) {
            String text = "SO IT WAS COOL AND HOT BUT ANY WAY WE WERE AWESOME";
            String serializedText = DocumentProcessor.serializeText(text, FontFamily.ARIAL, FontColor.BLUE, 2);
            System.out.println("Serialized Text " + serializedText);
            List<CharacterText> deserializedCharacterList = DocumentProcessor.deserializeTextAndBuildCharacterTextList(serializedText);
            System.out.println("Raw Text " + DocumentProcessor.buildRawText(deserializedCharacterList));
    }
}
