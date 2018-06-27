package pl.kpir.kpir.kpir.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @Column (nullable = false)
    private String date;
    @Column (name = "netto_amount")
    private String netValue;
    @Column (name = "vat_value")
    private String vatValue;
    @Column (name = "vat_amount")
    private String vatAmount;
    @Column (name = "invoice_amount")
    private String invoiceAmount;
}
