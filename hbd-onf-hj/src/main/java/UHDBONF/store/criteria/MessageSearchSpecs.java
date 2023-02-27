package UHDBONF.store.criteria;

import UHDBONF.domain.entity.Message;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageSearchSpecs {
    public static Specification<Message> searchWith(Map<MessageSearchKeys, Object> searchKeyword) {
        return (Specification<Message>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            query.orderBy(builder.desc(root.get("created")));
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }

    private static List<Predicate> getPredicateWithKeyword(Map<MessageSearchKeys, Object> searchKeyword, Root<Message> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        for (MessageSearchKeys key : searchKeyword.keySet()) {
            switch (key) {
                case USER_UID:
                case UID:
                case TID:
                case EXPOSE:
                    predicate.add(builder.equal(
                            root.get(key.getValue()),searchKeyword.get(key)
                    ));
                    break;
            }
        }
        return predicate;
    }
}
