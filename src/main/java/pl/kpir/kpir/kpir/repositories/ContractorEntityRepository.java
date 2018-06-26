package pl.kpir.kpir.kpir.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.ContractorEntity;

@Repository
public interface ContractorEntityRepository  extends JpaRepository<ContractorEntity, Long> {



}



