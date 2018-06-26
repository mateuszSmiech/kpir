package pl.kpir.kpir.kpir.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.ContractorEntity;

import java.util.List;

@Repository
public interface ContractorEntityRepository  extends JpaRepository<ContractorEntity, Long> {

    @Query("SELECT contr FROM ContractorEntity contr INNER JOIN contr.companyId com INNER JOIN com.userEntity u WHERE u.id=:userId")
    List<ContractorEntity> findByCompanyId(@Param("userId") Long id );

}
