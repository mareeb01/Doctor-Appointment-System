package com.example.doctorappointmentsystem.Doctors;

public class UploadMedicineModel {

    String medicineimg,orderId,page,pdate,pname,pphone,pproblem, patientId;

    public UploadMedicineModel() {
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public UploadMedicineModel(String medicineimg, String orderId, String page, String pdate, String pname, String pphone, String pproblem, String patientId) {
        this.medicineimg = medicineimg;
        this.orderId = orderId;
        this.page = page;
        this.pdate = pdate;
        this.pname = pname;
        this.pphone = pphone;
        this.pproblem = pproblem;
        this.patientId = patientId;
    }

    public String getMedicineimg() {
        return medicineimg;
    }

    public void setMedicineimg(String medicineimg) {
        this.medicineimg = medicineimg;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getPproblem() {
        return pproblem;
    }

    public void setPproblem(String pproblem) {
        this.pproblem = pproblem;
    }
}
