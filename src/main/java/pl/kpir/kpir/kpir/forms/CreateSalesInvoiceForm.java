package pl.kpir.kpir.kpir.forms;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class CreateSalesInvoiceForm {


    private String invoiceNumber;
    private String date;
    private BigDecimal netValue;
    private BigDecimal vatValue;
    private BigDecimal vatAmount;
    private BigDecimal invoiceAmount;
    private Long contractorId;

}
