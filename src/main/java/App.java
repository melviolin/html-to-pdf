import static spark.Spark.*;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import java.io.ByteArrayOutputStream;

public class App {
    public static void main(String[] args) {
        port(getHerokuAssignedPort()); // Render usa la variable PORT

        post("/generate", (req, res) -> {
            String html = req.body();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ConverterProperties properties = new ConverterProperties();
            properties.setFontProvider(FontHelper.getFontProvider()); // Fuente Arial

            HtmlConverter.convertToPdf(html, baos, properties);

            res.type("application/pdf");
            return baos.toByteArray();
        });
    }

    static int getHerokuAssignedPort() {
        String port = System.getenv("PORT");
        return port != null ? Integer.parseInt(port) : 4567;
    }
}
