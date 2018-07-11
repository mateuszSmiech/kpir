package pl.kpir.kpir.kpir.services;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class ReportService{

    public byte[] generateKpirReport() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc, PageSize.A4.rotate());

        Paragraph paragraph = new Paragraph("asdasdadasd");
        document.add(paragraph);
        AreaBreak aB = new AreaBreak();

        document.add(aB);
        document.add(paragraph);

        IntStream.range(1, pdfDoc.getNumberOfPages() + 1)
                .mapToObj(pdfDoc::getPage)
                .forEach(page -> page.setRotation(0));
        document.close();

        return outputStream.toByteArray();
    }
}
