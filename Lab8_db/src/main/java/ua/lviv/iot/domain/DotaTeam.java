package ua.lviv.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dota_team", schema = "dota")
public class DotaTeam {
    private String teamName;
    private String teamOwnerName;
    private String coachName;
    private String country;


    @Id
    @Column(name = "team_name", nullable = false, length = 50)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "team_owner_name", nullable = true, length = 50)
    public String getTeamOwnerName() {
        return teamOwnerName;
    }

    public void setTeamOwnerName(String teamOwnerName) {
        this.teamOwnerName = teamOwnerName;
    }

    @Basic
    @Column(name = "coach_name", nullable = true, length = 50)
    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private List<DotaPlayer> playersByTeam;

    @JsonIgnore
    @OneToMany(mappedBy = "teamByPlayer", targetEntity = DotaPlayer.class)
    public List<DotaPlayer> getPlayersByTeam() {
        return playersByTeam;
    }

    public void setPlayersByTeam(List<DotaPlayer> playersByTeam) {
        this.playersByTeam = playersByTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DotaTeam that = (DotaTeam) o;

        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;
        if (teamOwnerName != null ? !teamOwnerName.equals(that.teamOwnerName) : that.teamOwnerName != null)
            return false;
        if (coachName != null ? !coachName.equals(that.coachName) : that.coachName != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + (teamOwnerName != null ? teamOwnerName.hashCode() : 0);
        result = 31 * result + (coachName != null ? coachName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}