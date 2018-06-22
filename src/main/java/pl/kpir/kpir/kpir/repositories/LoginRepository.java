package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kpir.kpir.kpir.model.UserEntity;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {


}
