package hiFes.hiFes.repository;

import hiFes.hiFes.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}