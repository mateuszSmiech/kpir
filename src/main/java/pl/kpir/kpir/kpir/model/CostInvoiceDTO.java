package pl.kpir.kpir.kpir.model;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CostInvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private String date;
    private String netValue;
    private String vatValue;


}
