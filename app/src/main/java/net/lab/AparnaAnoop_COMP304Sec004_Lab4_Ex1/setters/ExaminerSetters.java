package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table")
public class ExaminerSetters {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstname;
    private String lastname;
    private String testcenter;
    private String password;
    public ExaminerSetters(String firstname, String lastname, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
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
    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }
}