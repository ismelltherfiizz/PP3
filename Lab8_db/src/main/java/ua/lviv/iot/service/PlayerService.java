package ua.lviv.iot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.AdCompany;
import ua.lviv.iot.domain.DotaTeam;
import ua.lviv.iot.domain.DotaPlayer;
import ua.lviv.iot.domain.Logger;
import ua.lviv.iot.exceptions.ExistsCompanyForPlayerException;
import ua.lviv.iot.exceptions.NoSuchCompanyException;
import ua.lviv.iot.exceptions.NoSuchPlayerException;
import ua.lviv.iot.repository.TeamRepository;
import ua.lviv.iot.repository.CompanyRepository;
import ua.lviv.iot.repository.LoggerRepository;
import ua.lviv.iot.repository.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    TeamRepository TeamRepository;

    @Autowired
    LoggerRepository loggerRepository;

    public List<DotaPlayer> getPlayersByCompanyName(String companyName) throws NoSuchCompanyException {
        AdCompany company = companyRepository.findById(companyName).get();
        if (company == null) throw new NoSuchCompanyException();
        return company.getPlayers();
    }

    public DotaPlayer getPlayer(Integer playerId) throws NoSuchPlayerException {
        DotaPlayer player = playerRepository.findById(playerId).get();
        if (player == null) throw new NoSuchPlayerException();
        return player;
    }

    public List<DotaPlayer> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<DotaPlayer> getPlayersByteamName(String teamName) throws ua.lviv.iot.exceptions.NoSuchTeamException {
        DotaTeam team = TeamRepository.findById(teamName).get();
        if (team == null) throw new ua.lviv.iot.exceptions.NoSuchTeamException();
        return team.getPlayersByTeam();
    }

    @Transactional
    public void createPlayer(DotaPlayer player, String teamName) throws ua.lviv.iot.exceptions.NoSuchTeamException {
        if (!teamName.equals("")) {
            DotaTeam team = TeamRepository.findById(teamName).get();
            if (team == null) throw new ua.lviv.iot.exceptions.NoSuchTeamException();
            player.setteamByPlayer(team);
        }
        playerRepository.save(player);
    }


    @Transactional
    public void updatePlayer(DotaPlayer newPlayer, Integer oldPlayerId, String teamName)
            throws NoSuchPlayerException, ua.lviv.iot.exceptions.NoSuchTeamException {
        DotaTeam team = TeamRepository.findById(teamName).get();
        if (!teamName.equals("")) {
            if (team == null) throw new ua.lviv.iot.exceptions.NoSuchTeamException();
        }

        DotaPlayer player = playerRepository.findById(oldPlayerId).get();
        if (player == null) throw new NoSuchPlayerException();

        player.setLastName(newPlayer.getLastName());
        player.setFirstName(newPlayer.getFirstName());
        if (!teamName.equals("")) player.setteamByPlayer(team);
        else player.setteamByPlayer(null);
        player.setYearsOfExperience(newPlayer.getYearsOfExperience());
        playerRepository.save(player);
    }


    @Transactional
    public void deletePlayer(Integer playerId)
            throws NoSuchPlayerException, ua.lviv.iot.exceptions.NoSuchTeamException {
        DotaPlayer player = playerRepository.findById(playerId).get();
        if (player == null) throw new NoSuchPlayerException();
        playerRepository.delete(player);
    }

    @Transactional
    public void addCompanyForPlayer(Integer playerId, String companyName)
            throws NoSuchCompanyException, NoSuchPlayerException, ExistsCompanyForPlayerException {
        DotaPlayer DotaPlayer;
        AdCompany adCompany;
        if (playerRepository.findById(playerId).isPresent()) {
            DotaPlayer = playerRepository.findById(playerId).get();
        } else throw new NoSuchPlayerException();

        if (companyRepository.findById(companyName).isPresent()) {
            adCompany = companyRepository.findById(companyName).get();
        } else throw new NoSuchCompanyException();


        Logger logger = new Logger();
        logger.setCompanyName(adCompany.getCompanyName());
        logger.setPlayerId(DotaPlayer.getPlayerId());

        if (DotaPlayer.getCompanies().contains(adCompany)) throw new ExistsCompanyForPlayerException();
        loggerRepository.save(logger);
    }


}
