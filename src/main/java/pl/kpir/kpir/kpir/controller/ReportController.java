package pl.kpir.kpir.kpir.controller;


import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kpir.kpir.kpir.services.ReportService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
@RequestMapping(path="/reports")
public class ReportController {
    private static final String FILE_PATH = "/tmp/example.pdf";
    private static final String APPLICATION_PDF = "application/pdf";
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path="/list")
    public String loadReportPage() {
        return "reportPage";
    }

    @GetMapping(path="/kpirReport", produces = APPLICATION_PDF)
    public @ResponseBody void downloadA(HttpServletResponse response) throws IOException {
        byte[] reportBytes = reportService.generateKpirReport();

        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + "MojaSlodkaNazwa.pdf");
        response.setHeader("Content-Length", String.valueOf(reportBytes.length));
        FileCopyUtils.copy(reportBytes, response.getOutputStream());
    }
}


