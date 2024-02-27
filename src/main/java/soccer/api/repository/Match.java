package soccer.api.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long id;
    private String local;
    private String visitor;
    private Integer localScore;
    private Integer visitorScore;
    private String stadium;
    private String competition;
    private Integer attendeesNumber;
    private LocalDate date;
    private Integer minutes;
    private String state;

    public Match() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public Integer getLocalScore() {
        return localScore;
    }

    public void setLocalScore(Integer localScore) {
        this.localScore = localScore;
    }

    public Integer getVisitorScore() {
        return visitorScore;
    }

    public void setVisitorScore(Integer visitorScore) {
        this.visitorScore = visitorScore;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public Integer getAttendeesNumber() {
        return attendeesNumber;
    }

    public void setAttendeesNumber(Integer attendeesNumber) {
        this.attendeesNumber = attendeesNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", local='" + local + '\'' +
                ", visitor='" + visitor + '\'' +
                ", localScore=" + localScore +
                ", visitorScore=" + visitorScore +
                ", stadium='" + stadium + '\'' +
                ", competition='" + competition + '\'' +
                ", attendeesNumber=" + attendeesNumber +
                ", date=" + date +
                ", minute=" + minutes +
                ", state='" + state + '\'' +
                '}';
    }
}
