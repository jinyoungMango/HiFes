package hiFes.hiFes.repository;

import hiFes.hiFes.domain.HostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HostUserRepository extends JpaRepository<HostUser, Long> {
    Optional<HostUser> findByEmail(String email);
    Optional<HostUser> findByName(String name);
    Optional<HostUser> findByPhoneNo(String phoneNo);
    Optional<HostUser> findByOrganization(String organization);
    Optional<HostUser> findByOrgNo(String orgNo);
    Optional<HostUser> findByOrgCode(String orgCode);

}
