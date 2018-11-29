package ua.lviv.iot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.DTO.DotaTeamDTO;
import ua.lviv.iot.domain.DotaTeam;
import ua.lviv.iot.exceptions.NoSuchCompanyException;
import ua.lviv.iot.exceptions.NoSuchPlayerException;
import ua.lviv.iot.service.TeamService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping(value = "/api/team")
    public ResponseEntity<List<DotaTeamDTO>> getAllteams()
            throws ua.lviv.iot.exceptions.NoSuchTeamException, NoSuchPlayerException, NoSuchCompanyException {
        List<DotaTeam> teamList = teamService.getAllteams();
        Link link = linkTo(methodOn(TeamController.class).getAllteams()).withSelfRel();

        List<DotaTeamDTO> teamsDTO = new ArrayList<>();
        for (DotaTeam entity : teamList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getTeamName()).withSelfRel();
            DotaTeamDTO dto = new DotaTeamDTO(entity, selfLink);
            teamsDTO.add(dto);
        }

        return new ResponseEntity<>(teamsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/team/{teamName}")
    public ResponseEntity<DotaTeamDTO> getteam(@PathVariable String teamName)
            throws ua.lviv.iot.exceptions.NoSuchTeamException, NoSuchPlayerException, NoSuchCompanyException {
        DotaTeam team = teamService.getteam(teamName);
        Link link = linkTo(methodOn(TeamController.class).getteam(teamName)).withSelfRel();

        DotaTeamDTO cityDTO = new DotaTeamDTO(team, link);

        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }
}
