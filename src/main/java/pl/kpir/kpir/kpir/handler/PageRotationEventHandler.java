package pl.kpir.kpir.kpir.handler;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;

public class PageRotationEventHandler implements IEventHandler {
    protected PdfNumber rotation = new PdfNumber(90);;

    public void setRotation(PdfNumber orientation) {
        this.rotation = orientation;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        docEvent.getPage().put(PdfName.Rotate, rotation);
    }
}
