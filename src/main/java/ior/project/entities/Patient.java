package ior.project.entities;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PATIENTS")
public class Patient extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.DETACH)
    private List<Visit> visits = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public int getAge(){
        if (birthDate != null) {
            return(LocalDate.now().getYear()-birthDate.getYear());
        }
        else {
            return(0);
        }
    }
}
