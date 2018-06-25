package pl.kpir.kpir.kpir.forms;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CreateInvoiceForm {

    private String ivoiceNumber;
    private Date date;
    private BigDecimal netValue;
    private int vatValue;
    private boolean isCostInvoice;
}
