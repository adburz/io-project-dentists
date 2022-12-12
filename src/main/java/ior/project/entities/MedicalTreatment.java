package ior.project.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "medicalTreatments")
public class MedicalTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int toothNo;
    private MedicalTreatmentType type;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToothNo() {
        return toothNo;
    }

    public void setToothNo(int toothNo) {
        this.toothNo = toothNo;
    }

    public MedicalTreatmentType getType() {
        return type;
    }

    public void setType(MedicalTreatmentType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
}


