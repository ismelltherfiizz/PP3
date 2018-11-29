package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.DotaTeam;
import ua.lviv.iot.exceptions.NoSuchTeamException;
import ua.lviv.iot.repository.TeamRepository;
import ua.lviv.iot.repository.PlayerRepository;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamRepository TeamRepository;

    @Autowired
    PlayerRepository playerRepository;

    public List<DotaTeam> getAllteams() {return TeamRepository.findAll();}

    public DotaTeam getteam(String teamName) throws NoSuchTeamException {
        DotaTeam team = TeamRepository.findById(teamName).get();
        if (team == null) throw new NoSuchTeamException();
        return team;
    }

}
