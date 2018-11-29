package ua.lviv.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ad_company", schema = "dota")
public class AdCompany {
    private String companyName;
    private Double budget;
    private String industry;
    private String country;
    private List<DotaPlayer> players;


    @Id
    @Column(name = "company_name", nullable = false, length = 50)
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "budget", nullable = true, precision = 0)
    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Basic
    @Column(name = "industry", nullable = true, length = 50)
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 50)
    public String getcountry() {
        return country;
    }

    public void setcountry(String country) {
        this.country = country;
    }


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "playerscompanies", schema = "dota",
            joinColumns = @JoinColumn(name = "company_name", referencedColumnName = "company_name", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "player_id", nullable = false))
    public List<DotaPlayer> getPlayers() {
        return players;
    }
    public void setPlayers(List<DotaPlayer> players) {
        this.players = players;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdCompany that = (AdCompany) o;

        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (budget != null ? !budget.equals(that.budget) : that.budget != null) return false;
        if (industry != null ? !industry.equals(that.industry) : that.industry != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyName != null ? companyName.hashCode() : 0;
        result = 31 * result + (budget != null ? budget.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}