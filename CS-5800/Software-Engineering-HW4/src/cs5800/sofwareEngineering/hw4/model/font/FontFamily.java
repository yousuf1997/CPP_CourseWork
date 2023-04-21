package cs5800.sofwareEngineering.hw4.model.font;

public enum FontFamily {
    ARIAL,
    CALIBRI,
    VERDANA;

    public static FontFamily createFont(String font) {
        if (font == null) {
            return null;
        }
        font = font.toUpperCase();
        switch (font) {
            case "ARIAL" :
                return ARIAL;
            case "CALIBRI" :
                return ARIAL;
            case "VERDANA" :
                return VERDANA;
            default:
                return null;
        }
    }
}
