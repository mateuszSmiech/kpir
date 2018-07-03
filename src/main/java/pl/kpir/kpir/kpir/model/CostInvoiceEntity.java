package pl.kpir.kpir.kpir.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "costInvoices")
public class CostInvoiceEntity {
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
    private Date date;
    @Column (name = "netto_value")
    private BigDecimal netValue;
    @Column (name = "vat_value")
    private BigDecimal vatValue;
    @Column (name = "invoice_cost")
    private String invoiceType;
    @Column (name = "vat_amount")
    private BigDecimal vatAmount;
    @Column (name = "invoice_amount")
    private BigDecimal invoiceAmount;
}