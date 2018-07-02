package pl.kpir.kpir.kpir.model;


import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class SalesInvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private String date;
    private String desc;
    private BigDecimal netValue;
    private BigDecimal vatValue;
    private BigDecimal vatAmount;
    private BigDecimal invoiceAmount;
    private Long contractorId;

}
