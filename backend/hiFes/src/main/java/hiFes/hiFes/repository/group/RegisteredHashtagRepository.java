package hiFes.hiFes.repository.group;

import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.Hashtag;
import hiFes.hiFes.domain.group.RegisteredHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisteredHashtagRepository extends JpaRepository<RegisteredHashtag, Long> {
    List<RegisteredHashtag> findByHashtagTitleContaining(String searchTag);
}
