package cs5800.sofwareEngineering.hw4.driver;

import cs5800.sofwareEngineering.hw4.model.q1.character.CharacterText;
import cs5800.sofwareEngineering.hw4.model.q1.character.CharacterTextFactory;
import cs5800.sofwareEngineering.hw4.model.q1.font.FontColor;
import cs5800.sofwareEngineering.hw4.model.q1.font.FontFamily;

public class Question1DriverPart2 {

    /**
     *
     * This driver demonstrates the usage of Flyweight pattern by demonstrating using multiple duplicate fonts
     */
    public static void main(String[] args) {

        // same properties
        CharacterText characterK = CharacterTextFactory.buildCharacterText('K', FontFamily.ARIAL, FontColor.RED, 12);
        CharacterText characterK2 = CharacterTextFactory.buildCharacterText('K', FontFamily.ARIAL, FontColor.RED, 12);
        System.out.println("Both of the Instance is Same since they have same properties : " + (characterK == characterK2));

        // character k with different property
        CharacterText characterK3 = CharacterTextFactory.buildCharacterText('K', FontFamily.TIMES_ROMAN, FontColor.RED, 12);
        System.out.println("Both of the Instance are not Same since they have different properties : " + (characterK != characterK3));

        // character k with same property as k3
        CharacterText characterK4 = CharacterTextFactory.buildCharacterText('K', FontFamily.TIMES_ROMAN, FontColor.RED, 12);
        System.out.println("Both of the Instance is Same since they have same properties : " + (characterK == characterK4));

        // character M
        CharacterText characterM = CharacterTextFactory.buildCharacterText('M', FontFamily.VERDANA, FontColor.BLUE, 8);
        CharacterText characterM2 = CharacterTextFactory.buildCharacterText('M', FontFamily.VERDANA, FontColor.BLUE, 8);

        System.out.println("Both of the Instance is Same since they have same properties : " + (characterM == characterM2));


        // character M with diffrent Font
        CharacterText characterM3 = CharacterTextFactory.buildCharacterText('M', FontFamily.TIMES_ROMAN, FontColor.BLUE, 8);
        System.out.println("Both of the Instance are not Same since they have different properties : " + (characterM3 != characterM2));


    }
}
