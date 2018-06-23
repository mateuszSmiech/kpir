package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kpir.kpir.kpir.model.CompanyEntity;
import pl.kpir.kpir.kpir.model.UserEntity;

import java.util.List;


@Repository
public interface CompanyEntityRepository extends JpaRepository<CompanyEntity, Long> {

    @Query("SELECT com FROM CompanyEntity com inner join com.userEntity u WHERE u.id=:userId")
    List<CompanyEntity> findByUserId(@Param("userId") Long id);
}
