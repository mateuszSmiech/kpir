package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kpir.kpir.kpir.model.ContractorEntity;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;

import java.util.List;

public interface CostInvoiceEntityRepository extends JpaRepository<CostInvoiceEntity, Long> {
}
