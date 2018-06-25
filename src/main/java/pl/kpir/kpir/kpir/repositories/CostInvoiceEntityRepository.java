package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kpir.kpir.kpir.model.CostInvoiceEntity;

public interface CostInvoiceEntityRepository extends JpaRepository<CostInvoiceEntity, Long> {
}
