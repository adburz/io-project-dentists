package ior.project.entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "visit", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicalTreatment> medicalTreatments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public void addMedicalTreatment(MedicalTreatment medicalTreatment) {
        medicalTreatments.add(medicalTreatment);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<MedicalTreatment> getMedicalTreatments() {
        return medicalTreatments;
    }

    public void setMedicalTreatments(List<MedicalTreatment> medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "{" + "\n"+
                "\"id\":" + id +"\n,"+
                "\"date\":\"" + date + "\"" +"\n,"+
                "\"description\":\"" + description + "\"" +"\n,"+
                "\"doctor\":" + doctor +"\n,"+
                "\"patient\":" + patient +"\n,"+
                "\"medicalTreatments\":" + medicalTreatments +"\n,"+
                "\"bill\":" + bill +"\n,"+
                '}'+"\n";
    }
}
