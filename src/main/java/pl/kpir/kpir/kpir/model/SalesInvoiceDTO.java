package pl.kpir.kpir.kpir.model;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class SalesInvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private LocalDate date;
    private String desc;
    private BigDecimal netValue;
    private BigDecimal vatValue;
    private BigDecimal vatAmount;
    private BigDecimal invoiceAmount;
    private ContractorDTO contractorId;

}
