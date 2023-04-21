package cs5800.sofwareEngineering.hw4.driver;

import cs5800.sofwareEngineering.hw4.model.q1.document.Document;
import cs5800.sofwareEngineering.hw4.model.q1.font.FontColor;
import cs5800.sofwareEngineering.hw4.model.q1.font.FontFamily;
import cs5800.sofwareEngineering.hw4.q1.processor.DocumentProcessor;

public class Question1DriverPart1 {

    /**
     *
     * This driver illustrates the document creation and update process.
     * When the document is being stored in the file system
     * each character will serialized by storing meta-data about that character example for character 'M' with arial font, red color, size as 1 with enclosed brackets
     * it will be stored as [M::ARIAL::BLUE::2]
     */
    public static void main(String[] args) {
        String text1 = "I was there but I could not attend the lecture.";

        // first document with Arial text, Red color, 12 size
        DocumentProcessor.createDocument("Document1", text1, FontFamily.ARIAL, FontColor.RED, 12);
        // get the document
        System.out.println("Fetched Document : " + DocumentProcessor.getDocument("Document1").getDeserializedText());

        // update the document
        Document oldDocument = DocumentProcessor.getDocument("Document1");
        String newTextToBeAppended = " But I was able to come to the lecture in the afternoon.";
        // update the document
        DocumentProcessor.updateDocument("Document1", oldDocument.getDeserializedText() + newTextToBeAppended,
                                            FontFamily.TIMES_ROMAN, FontColor.BLUE, 2);
        // updated the document
        System.out.println("Fetched Document After update : " + DocumentProcessor.getDocument("Document1").getDeserializedText());

    }
}
