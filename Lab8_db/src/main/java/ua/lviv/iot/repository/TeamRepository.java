package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.DotaTeam;

@Repository
public interface TeamRepository extends JpaRepository<DotaTeam, String> {
}
