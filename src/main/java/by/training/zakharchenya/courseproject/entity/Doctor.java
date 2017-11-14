package by.training.zakharchenya.courseproject.entity;

public class Doctor {
    private int doctorId;
    private String doctorName;
    private String doctorSurname;
    private String prof;

    public Doctor(String doctorName, String doctorSurname, String prof) {
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.prof = prof;
    }

    public Doctor(int doctorId, String doctorName, String doctorSurname, String prof) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.prof = prof;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }
}
