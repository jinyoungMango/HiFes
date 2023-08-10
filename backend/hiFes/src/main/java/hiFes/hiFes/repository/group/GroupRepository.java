package hiFes.hiFes.repository.group;

import hiFes.hiFes.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findBygroupNameContainingOrContentContaining(String groupName, String content);
    List<Group> findByFestivalId(Long festivalId);

}
