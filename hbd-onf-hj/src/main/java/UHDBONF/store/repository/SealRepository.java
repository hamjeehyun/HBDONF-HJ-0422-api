package UHDBONF.store.repository;

import UHDBONF.domain.entity.Seal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SealRepository extends JpaRepository<Seal, String>, JpaSpecificationExecutor<Seal> {
}
