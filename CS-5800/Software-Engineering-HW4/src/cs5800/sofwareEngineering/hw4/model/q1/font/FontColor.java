package cs5800.sofwareEngineering.hw4.model.q1.font;

public enum FontColor {
    RED,
    BLUE,
    GREY,
    GREEN;

    public static FontColor createFontColor(String color) {
        if (color == null) {
            return null;
        }
        color = color.toUpperCase();
        switch (color) {
            case "RED" :
                return RED;
            case "BLUE" :
                return BLUE;
            case "GREY" :
                return GREY;
            case "GREEN" :
                return GREEN;
            default:
                return null;
        }
    }
}
