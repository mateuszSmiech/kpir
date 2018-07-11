package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;

import java.util.List;

public interface SalesInvoiceEntityRepository extends JpaRepository<SalesInvoiceEntity, Long> {

    @Query("SELECT sales FROM SalesInvoiceEntity sales INNER JOIN sales.companyId com INNER JOIN com.userEntity u WHERE u.id=:userId")
    List<SalesInvoiceEntity> findByCompanyId(@Param("userId") Long id );

    @Query("SELECT sales FROM SalesInvoiceEntity sales INNER JOIN sales.contractorEntity contr WHERE sales.date LIKE CONCAT(:year, '-', :month, '%')")
    List<SalesInvoiceEntity> findInvoiceByDate(@Param("month") String month, @Param("year") String year);

}
