package hiFes.hiFes.repository.group;


import hiFes.hiFes.domain.group.SharedPic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharedPicRepository extends JpaRepository<SharedPic, Long> {
    List<SharedPic> findByGroupId(Long groupId);

}
