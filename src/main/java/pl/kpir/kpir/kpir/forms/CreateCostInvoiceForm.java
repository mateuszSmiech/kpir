package pl.kpir.kpir.kpir.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CreateCostInvoiceForm {

    private String invoiceNumber;
    private String date;
    private String netValue;
    private String vatValue;
    private String invoiceType;
}
