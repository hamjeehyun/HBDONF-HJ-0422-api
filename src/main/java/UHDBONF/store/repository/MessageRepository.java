package UHDBONF.store.repository;


import UHDBONF.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String>, JpaSpecificationExecutor<Message> {
    List<Message> findAllByTid(String tid);
    List<Message> findAllByUserUid(String userUid);
    Message findByTid(String tid);
}
