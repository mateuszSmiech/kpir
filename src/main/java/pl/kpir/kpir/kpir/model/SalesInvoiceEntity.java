package pl.kpir.kpir.kpir.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "salesInvoices")
public class SalesInvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private ContractorEntity contractorEntity;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity companyId;
    @Column (name = "number")
    private String invoiceNumber;
    @Column (name = "description")
    private String desc;
    @Column (nullable = false)
    private LocalDate date;
    @Column (name = "netto_amount")
    private BigDecimal netValue;
    @Column (name = "vat_value")
    private BigDecimal vatValue;
    @Column (name = "vat_amount")
    private BigDecimal vatAmount;
    @Column (name = "invoice_amount")
    private BigDecimal invoiceAmount;
}
