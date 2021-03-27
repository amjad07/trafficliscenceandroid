package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table")
public class ApplicantSetters {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstname;
    private String lastname;
    private String testcenter;
    private int eaminerId;
    public ApplicantSetters(String firstname, String lastname, int eaminerId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.eaminerId = eaminerId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public int getEaminerId() {
        return eaminerId;
    }

    public String getTestcenter() {
        return testcenter;
    }

    public void setTestcenter(String testcenter) {
        this.testcenter = testcenter;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEaminerId(int eaminerId) {
        this.eaminerId = eaminerId;
    }
}