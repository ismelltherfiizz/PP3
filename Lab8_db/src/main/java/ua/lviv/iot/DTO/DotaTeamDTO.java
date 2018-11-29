package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.controller.PlayerController;
import ua.lviv.iot.domain.DotaTeam;
import ua.lviv.iot.domain.DotaPlayer;
import ua.lviv.iot.exceptions.NoSuchCompanyException;
import ua.lviv.iot.exceptions.NoSuchPlayerException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class DotaTeamDTO extends ResourceSupport {
    DotaTeam DotaTeam;

    public DotaTeamDTO(DotaTeam team, Link selflink)
            throws ua.lviv.iot.exceptions.NoSuchTeamException, NoSuchPlayerException, NoSuchCompanyException {
        this.DotaTeam = team;
        add(selflink);
        add(linkTo(methodOn(PlayerController.class).getPlayersByteamName(team.getTeamName())).withRel("players"));
    }

    public String getDotaTeamName() {
        return DotaTeam.getTeamName();
    }

    public String getDotaTeamOwnerName() {
        return DotaTeam.getTeamOwnerName();
    }

    public String getDotaTeamCoachName() {
        return DotaTeam.getCoachName();
    }

    public String getDotaTeamCountry() {
        return DotaTeam.getCountry();
    }

    public List<Integer> getPlayersByteam() {
        List<Integer> playerList = new ArrayList<>();
        for (DotaPlayer player: DotaTeam.getPlayersByTeam()) {
            playerList.add(player.getPlayerId());
        }
        return playerList;
    }
}
