package hiFes.hiFes.repository.group;

import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.JoinedGroup;
import hiFes.hiFes.domain.user.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinedGroupRepository extends JpaRepository<JoinedGroup, Long> {
    boolean existsByNormalUserAndGroup(NormalUser normalUser, Group group);
    JoinedGroup findByNormalUserAndGroup(NormalUser normalUser, Group group);

    List<JoinedGroup> findByNormalUserId(Long normalUserId);
    List<JoinedGroup> findByGroupId(Long groupId);
}
