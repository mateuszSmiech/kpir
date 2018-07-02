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
    private BigDecimal netValue;
    private BigDecimal vatValue;
    private BigDecimal vatAmount;
    private BigDecimal invoiceAmount;
    private String invoiceType;
    private Long contractorId;

//    private String shortCompanyName;
//    private String fullCompanyName;
//    private String nip;
//    private String street;
//    private String postCode;
//    private String city;
//    private String country;
//    private String telephoneNumber;
//    private String email;
}
