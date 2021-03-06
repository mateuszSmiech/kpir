package pl.kpir.kpir.kpir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kpir.kpir.kpir.model.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.email=:email")
    UserEntity findUserByEmail(@Param("email") String email);

}
