import com.itextpdf.layout.font.FontProvider;

public class FontHelper {
    public static FontProvider getFontProvider() {
        FontProvider fontProvider = new FontProvider();
        fontProvider.addSystemFonts(); // Carga fuentes del sistema
        fontProvider.addDirectory("fonts"); // Carga la carpeta local /fonts con ARIAL.ttf
        return fontProvider;
    }
}
