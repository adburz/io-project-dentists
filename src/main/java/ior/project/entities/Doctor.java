package ior.project.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {
    private String specialization;
    private String academicTitle;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();

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

//    @Override
//    public String toString() {
//        super.toString();
//        return "{\n" +
//                "\"id\":" + getId() + "\",\n" +
//                "\"firstName\":\"" + getfName() + "\",\n" +
//                "\"lastName\":\"" + getsName() + "\",\n" +
//                "\"specialization\":\"" + specialization + "\",\n" +
//                "\"academicTitle\":\"" + academicTitle + "\",\n" +
//                "\"visits\":" + visits + ",\n" +
//                "}\n";
//    }

}
