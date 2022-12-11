package ior.project.entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DOCTORS")
public class Doctor extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String specialization;
    private String academicTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.DETACH)
    private List<Visit> visits = new ArrayList<>();
}
