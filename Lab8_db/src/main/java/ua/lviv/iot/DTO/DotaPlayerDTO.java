package ua.lviv.iot.DTO;


import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.controller.CompanyController;
import ua.lviv.iot.domain.AdCompany;
import ua.lviv.iot.domain.DotaPlayer;
import ua.lviv.iot.exceptions.NoSuchCompanyException;
import ua.lviv.iot.exceptions.NoSuchPlayerException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


public class DotaPlayerDTO extends ResourceSupport {
    DotaPlayer DotaPlayer;

    public DotaPlayerDTO(DotaPlayer player, Link selflink)
            throws NoSuchPlayerException, NoSuchCompanyException, ua.lviv.iot.exceptions.NoSuchTeamException {
        this.DotaPlayer = player;
        add(selflink);
        add(linkTo(methodOn(CompanyController.class).getCompaniesByPlayerId(player.getPlayerId())).withRel("companies"));
    }

    public Integer getDotaPlayerId() {
        return DotaPlayer.getPlayerId();
    }

    public String getDotaPlayerLastName() {
        return DotaPlayer.getLastName();
    }

    public String getDotaPlayerFirstName() {
        return DotaPlayer.getFirstName();
    }

    public Byte getYearsOfExperience() {
        return DotaPlayer.getYearsOfExperience();
    }

    public String getteamByteam() {
        if (DotaPlayer.getteamByPlayer() == null) return "";
        return DotaPlayer.getteamByPlayer().getTeamName();
    }

    public List<String> getCompanies() {
        List<String> nameList = new ArrayList<>();
        for (AdCompany company: DotaPlayer.getCompanies()) {
            nameList.add(company.getCompanyName());
        }
        return nameList;
    }

}
