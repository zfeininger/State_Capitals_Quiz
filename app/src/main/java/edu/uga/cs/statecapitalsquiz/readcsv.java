package edu.uga.cs.statecapitalsquiz;

public class readcsv {

    private long id;
    private String state;
    private String capitalCity;
    private String additionalCity1;
    private String additionalCity2;

    public readcsv() {
        this.id = -1;
        this.state = null;
        this.capitalCity = null;
        this.additionalCity1 = null;
        this.additionalCity2 = null;
    }

    public readcsv(String state, String capitalCity, String additionalCity1, String additionalCity2) {
        this.id = -1;
        this.state = state;
        this.capitalCity = capitalCity;
        this.additionalCity1 = additionalCity1;
        this.additionalCity2 = additionalCity2;
    }

    public long getId() { return id; }
    public void setId(long id) {this.id = id;}
    public String getState() {return state;}
    public void setState(String state) { this.state = state; }
    public String getCapitalCity() {return capitalCity;}
    public void setCapitalCity(String capitalCity) { this.capitalCity = capitalCity; }
    public String getAdditionalCity1() { return additionalCity1; }
    public void setAdditionalCity1(String additionalCity1) { this.additionalCity1 = additionalCity1; }
    public String getAdditionalCity2() {return additionalCity2;}
    public void setAdditionalCity2() { this.additionalCity2 = additionalCity2; }

    public String toString() {
        return id + ": " + state + " " + capitalCity + " " + additionalCity1 + " " + additionalCity2;
    }

}
