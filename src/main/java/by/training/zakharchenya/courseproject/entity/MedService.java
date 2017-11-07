package by.training.zakharchenya.courseproject.entity;

import java.sql.Date;

/**
 * Created by Lagarde on 07.11.2017.
 */
public class MedService {
    private int serviceId;
    private String serviceName;
    private int patient;
    private int doctor;
    private double price;
    private Date serviceDate;
    private String description;

    public MedService(String serviceName, int patient, int doctor, double price, Date serviceDate, String description) {
        this.serviceName = serviceName;
        this.patient = patient;
        this.doctor = doctor;
        this.price = price;
        this.serviceDate = serviceDate;
        this.description = description;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedService that = (MedService) o;

        if (serviceId != that.serviceId) return false;
        if (patient != that.patient) return false;
        if (doctor != that.doctor) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
        if (serviceDate != null ? !serviceDate.equals(that.serviceDate) : that.serviceDate != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = serviceId;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + patient;
        result = 31 * result + doctor;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (serviceDate != null ? serviceDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
