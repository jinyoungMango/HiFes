package hiFes.hiFes.repository.group;

import hiFes.hiFes.domain.group.MyPic;
import hiFes.hiFes.domain.group.SharedPic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPicRepository extends JpaRepository<MyPic, Long> {
    List<MyPic> findBySharedPic(SharedPic sharedPic);
}
