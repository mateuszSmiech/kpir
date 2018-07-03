package pl.kpir.kpir.kpir.controller;


import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kpir.kpir.kpir.handler.PageOrientationEventHandler;
import pl.kpir.kpir.kpir.handler.PageRotationEventHandler;

import java.io.FileNotFoundException;


@Controller
@RequestMapping(path="/reports")
public class ReportController {

    @GetMapping(path="/list")
    public String loadReportPage() {
        return "reportPage";
    }

    @GetMapping(path="/kpirReport")
    public String openReportPage() throws FileNotFoundException {
        // Creating a PdfWriter
        String dest = "C:/Users/Mateusz/ms_sda/sample.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        PageOrientationEventHandler eventHandler = new PageOrientationEventHandler();
        PageRotationEventHandler rotationEventHandler = new PageRotationEventHandler();
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, eventHandler);
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, rotationEventHandler);
        eventHandler.setOrientation(new PdfNumber(90));
        rotationEventHandler.setRotation(new PdfNumber(90));
        Document document = new Document(pdfDoc);
//        Paragraph paragraph = new Paragraph("asdasdadasd");
//        document.add(paragraph);

        Table table = new Table(5, true);

        for (int i = 0; i < 5; i++) {
            table.addHeaderCell(new Cell().setKeepTogether(true).add(new Paragraph("Header " + i)));
        }

        document.add(table);
        for (int i = 0; i < 500; i++) {
            if (i % 5 == 0) {
                table.flush();
            }
            table.addCell(new Cell().setKeepTogether(true).add(new Paragraph("Test " + i).setMargins(0, 0, 0, 0)));
        }

        table.complete();


        document.close();

        return "redirect:list";
    }
}


