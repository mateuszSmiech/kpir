package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CostInvoiceEntityRepository extends JpaRepository<CostInvoiceEntity, Long> {

    //TODO: uwzględnic companyId w query (teraz szuka dla szystkich użytkowników)

    @Query("SELECT cost FROM CostInvoiceEntity cost INNER JOIN cost.companyId comp " +
            "WHERE comp.id = :companyId " +
            "AND MONTH(cost.date) = :month  " +
            "AND YEAR(cost.date) = :year")
    List<CostInvoiceEntity> findByCompanyId(@Param("companyId") Long id, @Param("month") int mont, @Param("year") int year);

    @Query("SELECT cost FROM CostInvoiceEntity cost INNER JOIN cost.companyId comp  " +
            "WHERE comp.id = :companyId " +
            "AND MONTH(cost.date) = :month " +
            "AND YEAR(cost.date) = :year")
    List<CostInvoiceEntity> findInvoiceByDate(@Param("companyId") Long id, @Param("month") int month, @Param("year") int year);


    @Query("SELECT COALESCE(SUM(cost.netValue), 0) FROM CostInvoiceEntity cost INNER JOIN cost.companyId comp " +
            "WHERE comp.id = :companyId " +
            "AND MONTH(cost.date) = :month " +
            "AND YEAR(cost.date) = :year")
    BigDecimal sumNetValueForCurrentMonth(@Param("companyId") Long id, @Param("month") int month, @Param("year") int year);


    @Query("SELECT coalesce(SUM(cost.netValue), 0) FROM CostInvoiceEntity cost INNER JOIN cost.contractorEntity contr WHERE cost.date >= CONCAT(:year, '-01-01') " +
            "AND cost.date < concat(:year, '-', :month, '-01')")
    BigDecimal sumNetValueFromYearStart(@Param("month") String month, @Param("year") String year);


}
