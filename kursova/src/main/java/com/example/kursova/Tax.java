package com.example.kursova;

public class Tax {
    private int idtax;
    private String User;
    private String TypeTax;
    private int StartBalance;
    private int Participant;
    private int Tax;

    public Tax(int id, String user, String typeTax, int startBalance, int participant, int tax) {
        User = user;
        TypeTax = typeTax;
        StartBalance = startBalance;
        Participant = participant;
        Tax = tax;
        idtax = id;
    }

    public Tax() {

    }

    public int getIdtax() {
        return idtax;
    }

    public void setIdtax(int idtax) {
        this.idtax = idtax;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getTypeTax() {
        return TypeTax;
    }

    public void setTypeTax(String typeTax) {
        TypeTax = typeTax;
    }

    public int getStartBalance() {
        return StartBalance;
    }

    public void setStartBalance(int startBalance) {
        StartBalance = startBalance;
    }

    public int getParticipant() {
        return Participant;
    }

    public void setParticipant(int participant) {
        Participant = participant;
    }

    public int getTax() {
        return Tax;
    }

    public void setTax(int tax) {
        Tax = tax;
    }
}
