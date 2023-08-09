package hiFes.hiFes.repository;

import hiFes.hiFes.domain.CompletedStampMission;
import hiFes.hiFes.domain.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletedStampMissionRepository extends JpaRepository<CompletedStampMission, Long> {
    List<CompletedStampMission> findByNormalUser_normalUserId(Long normalUserId);
}
