package com.example.doctorappointmentsystem.Patient;

public class Bookedoptlistmodel {

    String pimg,pname,pdisc,atime,page,orderId,pphone, patientId;

    public Bookedoptlistmodel() {
    }

    public Bookedoptlistmodel( String pimg,String pname,String page, String pdisc, String atime, String orderId, String pphone, String patientId) {
        this.pimg = pimg;
        this.pname = pname;
        this.pdisc = pdisc;
        this.atime = atime;
        this.page = page;
        this.orderId = orderId;
        this.pphone = pphone;
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdisc() {
        return pdisc;
    }

    public void setPdisc(String pdisc) {
        this.pdisc = pdisc;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }
}
