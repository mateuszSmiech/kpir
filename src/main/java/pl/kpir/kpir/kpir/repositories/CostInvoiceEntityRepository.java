package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CostInvoiceEntityRepository extends JpaRepository<CostInvoiceEntity, Long> {

    //TODO: uwzględnic companyId w query (teraz szuka dla szystkich użytkowników)

    @Query("SELECT cost FROM CostInvoiceEntity cost INNER JOIN cost.companyId com INNER JOIN com.userEntity u WHERE u.id=:userId")
    List<CostInvoiceEntity> findByCompanyId(@Param("userId") Long id );

    @Query("SELECT cost FROM CostInvoiceEntity cost INNER JOIN cost.contractorEntity contr WHERE MONTH(cost.date) = :month " +
            "AND YEAR(cost.date) = :year")
    List<CostInvoiceEntity> findInvoiceByDate(@Param("month") int month, @Param("year") int year);


    @Query("SELECT coalesce(SUM(cost.netValue), 0) FROM CostInvoiceEntity cost INNER JOIN cost.contractorEntity contr WHERE cost.date LIKE CONCAT(:year, '-', :month, '%')")
    BigDecimal sumCurrentMonth(@Param("month") String month, @Param("year") String year);

    @Query("SELECT coalesce(SUM(cost.netValue), 0) FROM CostInvoiceEntity cost INNER JOIN cost.contractorEntity contr WHERE cost.date >= CONCAT(:year, '-01-01') " +
            "AND cost.date < concat(:year, '-', :month, '-01')")
    BigDecimal sumNetValueFromYearStart(@Param("month") String month, @Param("year") String year);


}
