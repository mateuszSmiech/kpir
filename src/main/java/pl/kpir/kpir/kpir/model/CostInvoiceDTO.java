package pl.kpir.kpir.kpir.model;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CostInvoiceDTO {
//TODO: Zmienić date na LocalDate.
    private Long id;
    private String invoiceNumber;
    private String desc;
    private LocalDate date;
    private BigDecimal netValue;
    private BigDecimal vatValue;
    private BigDecimal vatAmount;
    private BigDecimal invoiceAmount;
    private String invoiceType;
    private ContractorDTO contractorId;


}
