package pl.kpir.kpir.kpir.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CreateInvoiceForm {

    private String ivoiceNumber;
    private String date;
    private BigDecimal netValue;
    private String vatValue;
    private String invoiceType;
}
