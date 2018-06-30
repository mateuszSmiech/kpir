package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;
import pl.kpir.kpir.kpir.model.SalesInvoiceEntity;

import java.util.Arrays;
import java.util.List;

public interface CostInvoiceEntityRepository extends JpaRepository<CostInvoiceEntity, Long> {

    @Query("SELECT cost FROM CostInvoiceEntity cost INNER JOIN cost.companyId com INNER JOIN com.userEntity u WHERE u.id=:userId")
    List<CostInvoiceEntity> findByCompanyId(@Param("userId") Long id );

    @Query("SELECT cost FROM CostInvoiceEntity cost INNER JOIN cost.contractorEntity contr WHERE cost.date LIKE CONCAT(:year, '-', :month, '%')")
    List<CostInvoiceEntity> findInvoiceByDate(@Param("month") String month, @Param("year") String year);



}
