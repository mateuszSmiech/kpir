package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kpir.kpir.kpir.model.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {


}
