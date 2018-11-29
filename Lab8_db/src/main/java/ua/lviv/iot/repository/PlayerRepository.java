package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.domain.DotaPlayer;

public interface PlayerRepository extends JpaRepository<DotaPlayer, Integer> {
}
