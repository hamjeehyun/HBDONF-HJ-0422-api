package UHDBONF.store.repository;


import UHDBONF.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
User findByTid(String tid);
User findByUid(String uid);
boolean existsByUid(String uid);
}
