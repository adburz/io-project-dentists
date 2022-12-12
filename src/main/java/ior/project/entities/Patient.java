package ior.project.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "patients")
public class Patient extends Person {
    private Date birthDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public int getAge() {
        if (birthDate != null) {
            return (LocalDate.now().getYear() - birthDate.getYear());
        } else {
            return (0);
        }
    }
}
