package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kpir.kpir.kpir.model.InvoiceEntity;

public interface InvoiceEntityRepository extends JpaRepository<InvoiceEntity, Long> {
}
