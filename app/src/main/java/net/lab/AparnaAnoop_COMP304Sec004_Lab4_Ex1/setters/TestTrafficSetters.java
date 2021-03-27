package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table")
public class TestTrafficSetters {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String testResult;
    private String testDate;
    private String testRoute;
    private String test_type;
    private int eaminerId;
    private int app_id;
    public TestTrafficSetters(String testResult, String testDate, String testRoute, String test_type, int eaminerId, int app_id) {
        this.testResult = testResult;
        this.testDate = testDate;
        this.eaminerId = eaminerId;
        this.testRoute = testRoute;
        this.app_id = app_id;
        this.test_type = test_type;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getTestResult() {
        return testResult;
    }
    public String getTestDate() {
        return testDate;
    }
    public int getEaminerId() {
        return eaminerId;
    }

    public String getTestRoute() {
        return testRoute;
    }

    public void setTestRoute(String testRoute) {
        this.testRoute = testRoute;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }
}