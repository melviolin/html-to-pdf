package app;

import static spark.Spark.*;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.font.FontProvider;

import java.io.ByteArrayOutputStream;

public class PdfService {
    public static void main(String[] args) {
        port(4567);

        post("/generate", (req, res) -> {
            String html = req.body();

            res.type("application/pdf");

            ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();

            ConverterProperties props = new ConverterProperties();
            FontProvider fp = new FontProvider();
            fp.addStandardPdfFonts();
            fp.addDirectory("src/main/resources/fonts"); // Para local
            fp.addFont(PdfService.class.getClassLoader().getResource("fonts/ARIAL.ttf").getPath()); // Para empaquetado

            props.setFontProvider(fp);
            props.setCharset("UTF-8");

            HtmlConverter.convertToPdf(html, pdfOutput);

            return pdfOutput.toByteArray();
        });
    }
}
