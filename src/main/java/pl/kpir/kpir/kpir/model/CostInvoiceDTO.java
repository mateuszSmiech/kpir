package pl.kpir.kpir.kpir.model;


import lombok.*;

import java.math.BigDecimal;

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
    private String date;
    private BigDecimal netValue;
    private BigDecimal vatValue;
    private BigDecimal vatAmount;
    private BigDecimal invoiceAmount;
    private String invoiceType;
    private ContractorDTO contractorId;


}
