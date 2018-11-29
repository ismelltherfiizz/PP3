package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.DotaTeamDTO;
import ua.lviv.iot.DTO.DotaPlayerDTO;
import ua.lviv.iot.domain.DotaPlayer;
import ua.lviv.iot.exceptions.ExistsCompanyForPlayerException;
import ua.lviv.iot.exceptions.NoSuchCompanyException;
import ua.lviv.iot.exceptions.NoSuchPlayerException;
import ua.lviv.iot.repository.PlayerRepository;
import ua.lviv.iot.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository repository;

    @GetMapping(value = "/api/player/company/{companyName}")
    public ResponseEntity<List<DotaPlayerDTO>> getPlayersByCompanyName(@PathVariable String companyName)
            throws NoSuchPlayerException, NoSuchCompanyException, ua.lviv.iot.exceptions.NoSuchTeamException {
        List<DotaPlayer> playerList = playerService.getPlayersByCompanyName(companyName);

        Link link = linkTo(methodOn(PlayerController.class).getAllPlayers()).withSelfRel();

        List<DotaPlayerDTO> playersDTO = new ArrayList<>();
        for (DotaPlayer entity : playerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getPlayerId()).withSelfRel();
            DotaPlayerDTO dto = new DotaPlayerDTO(entity, selfLink);
            playersDTO.add(dto);
        }

        return new ResponseEntity<>(playersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/player/{playerId}")
    public @ResponseBody ResponseEntity<DotaPlayerDTO> getPlayer(@PathVariable Integer playerId)
            throws NoSuchPlayerException, NoSuchCompanyException, ua.lviv.iot.exceptions.NoSuchTeamException {
        DotaPlayer player = playerService.getPlayer(playerId);
        Link link = linkTo(methodOn(PlayerController.class).getPlayer(playerId)).withSelfRel();

        DotaPlayerDTO playerDTO = new DotaPlayerDTO(player, link);

        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/player")
    public ResponseEntity<List<DotaPlayerDTO>> getAllPlayers()
            throws NoSuchPlayerException, NoSuchCompanyException, ua.lviv.iot.exceptions.NoSuchTeamException {
        List<DotaPlayer> playerList = playerService.getAllPlayers();
        Link link = linkTo(methodOn(PlayerController.class).getAllPlayers()).withSelfRel();

        List<DotaPlayerDTO> personsDTO = new ArrayList<>();
        for (DotaPlayer entity : playerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getPlayerId()).withSelfRel();
            DotaPlayerDTO dto = new DotaPlayerDTO(entity, selfLink);
            personsDTO.add(dto);
        }

        return new ResponseEntity<>(personsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/player/team/{teamName}")
    public ResponseEntity<List<DotaPlayerDTO>> getPlayersByteamName(@PathVariable String teamName)
            throws ua.lviv.iot.exceptions.NoSuchTeamException, NoSuchPlayerException, NoSuchCompanyException {
        List<DotaPlayer> playerList = playerService.getPlayersByteamName(teamName);
        Link link = linkTo(methodOn(PlayerController.class).getAllPlayers()).withSelfRel();

        List<DotaPlayerDTO> personsDTO = new ArrayList<>();
        for (DotaPlayer entity : playerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getPlayerId()).withSelfRel();
            DotaPlayerDTO dto = new DotaPlayerDTO(entity, selfLink);
            personsDTO.add(dto);
        }

        return new ResponseEntity<>(personsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/player/team/{teamName}")
    public  ResponseEntity<String> addPlayer(@RequestBody DotaPlayer newPlayer, @PathVariable String teamName)
            throws ua.lviv.iot.exceptions.NoSuchTeamException, NoSuchPlayerException, NoSuchCompanyException {

        playerService.createPlayer(newPlayer, teamName);

        return new ResponseEntity<>("Dota Player Created", HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/player/{oldPlayerId}/team/{teamName}")
    public  ResponseEntity<String> updatePerson(@RequestBody DotaPlayer newPlayer,
                                                @PathVariable Integer oldPlayerId,
                                                @PathVariable String teamName)
            throws ua.lviv.iot.exceptions.NoSuchTeamException, NoSuchPlayerException, NoSuchCompanyException {
        playerService.updatePlayer(newPlayer, oldPlayerId, teamName);


        return new ResponseEntity<>("Player Updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/player/{playerId}")
    public ResponseEntity deletePerson(@PathVariable Integer playerId)
            throws ua.lviv.iot.exceptions.NoSuchTeamException, NoSuchPlayerException {
        playerService.deletePlayer(playerId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "api/player/{playerId}/company/{companyName}")
    public ResponseEntity addCompanyForPlayer(@PathVariable Integer playerId,
                                              @PathVariable String companyName)
            throws NoSuchPlayerException, NoSuchCompanyException, ExistsCompanyForPlayerException {
        playerService.addCompanyForPlayer(playerId, companyName);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
