package ior.project.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String fName;
    protected String sName;
    protected String SSN;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    protected Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
//    @Override
//    public String toString() {
//        return "{\n" +
//                "\"id\":" + id + "\",\n" +
//                "\"firstName\":\"" + fName + "\",\n" +
//                "\"lastName\":\"" + sName + "\",\n" +
//                "\"address\":" + address + ",\n" +
//                "}\n";
//    }
}
