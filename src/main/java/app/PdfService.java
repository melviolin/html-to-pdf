
package app;

import static spark.Spark.*;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.layout.font.FontProvider;

import java.io.InputStream;

public class PdfService {
    public static void main(String[] args) {
        port(4567);

        post("/pdf", (req, res) -> {
            String html = req.body();

            FontProvider fontProvider = new FontProvider();
            InputStream fontStream = PdfService.class.getClassLoader()
                    .getResourceAsStream("fonts/ARIAL.ttf");
            if (fontStream != null) {
                fontProvider.addFont(fontStream);
            }

            ConverterProperties props = new ConverterProperties();
            props.setFontProvider(fontProvider);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(html, baos, props);

            res.type("application/pdf");
            return baos.toByteArray();
        });
    }
}
