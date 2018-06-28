package pl.kpir.kpir.kpir.model;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class SalesInvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private String date;
    private String netValue;
    private String vatValue;
    private String vatAmount;
    private String invoiceAmount;
    private Long contractorId;

}
