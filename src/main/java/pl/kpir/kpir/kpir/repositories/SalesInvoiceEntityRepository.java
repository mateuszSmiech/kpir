package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;

import java.math.BigDecimal;
import java.util.List;

public interface SalesInvoiceEntityRepository extends JpaRepository<SalesInvoiceEntity, Long> {

    @Query("SELECT sales FROM SalesInvoiceEntity sales INNER JOIN sales.companyId comp " +
            "WHERE comp.id=:id " +
            "AND MONTH(sales.date) = :month  " +
            "AND YEAR(sales.date) = :year")
    List<SalesInvoiceEntity> findByCompanyId(@Param("id") Long id, @Param("month") int month, @Param("year") int year );

    @Query("SELECT sales FROM SalesInvoiceEntity sales INNER JOIN sales.companyId comp " +
            "WHERE comp.id = :id " +
            "AND MONTH(sales.date) = :month " +
            "AND YEAR(sales.date) = :year")
    List<SalesInvoiceEntity> findInvoiceByDate(@Param("id") Long id, @Param("month") int month, @Param("year") int year);


    @Query("SELECT COALESCE(SUM(sales.netValue), 0) FROM SalesInvoiceEntity sales INNER JOIN sales.companyId comp " +
            "WHERE comp.id = :id " +
            "AND YEAR(sales.date) = :year " +
            "AND MONTH(sales.date) = :month")
    BigDecimal sumNetValueForCurrentMonth(@Param("id") Long id, @Param("month") int month, @Param("year") int year);

    @Query("SELECT COALESCE(SUM(sales.netValue), 0) FROM SalesInvoiceEntity sales INNER JOIN sales.companyId comp " +
            "WHERE comp.id = :id " +
            "AND YEAR(sales.date) = :year " +
            "AND MONTH(sales.date) < :month")
    BigDecimal sumNetValueFromYearStart(@Param("id") Long id ,@Param("month") int month, @Param("year") int year);

}
