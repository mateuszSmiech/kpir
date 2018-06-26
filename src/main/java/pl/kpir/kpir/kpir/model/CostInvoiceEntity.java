package pl.kpir.kpir.kpir.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kpir.kpir.kpir.forms.CreateContractorForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


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
    @Column (nullable = false)
    private String date;
    @Column (name = "netto_value")
    private String netValue;
    @Column (name = "vat_value")
    private String vatValue;
    @Column (name = "invoice_cost")
    private String invoiceType;
}