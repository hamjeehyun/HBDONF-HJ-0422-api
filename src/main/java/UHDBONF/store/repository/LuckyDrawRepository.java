package UHDBONF.store.repository;


import UHDBONF.domain.entity.LuckyDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LuckyDrawRepository extends JpaRepository<LuckyDraw, String>, JpaSpecificationExecutor<LuckyDraw> {
    LuckyDraw findTopByOrderBySequenceDesc();
    LuckyDraw findByTid(String tid);
}
