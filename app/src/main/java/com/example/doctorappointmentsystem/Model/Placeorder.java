package com.example.doctorappointmentsystem.Model;

public class Placeorder {

    String pname, page, pphone, pdate, pproblem,medicineimg,orderId, patientId;

    public Placeorder() {
    }

    public Placeorder(String pname, String page, String pphone, String pdate, String pproblem, String orderId, String patientId) {
        this.pname = pname;
        this.page = page;
        this.pphone = pphone;
        this.pdate = pdate;
        this.pproblem = pproblem;
        this.medicineimg = medicineimg;
        this.orderId = orderId;
        this.patientId = patientId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPproblem() {
        return pproblem;
    }

    public void setPproblem(String pproblem) {
        this.pproblem = pproblem;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
