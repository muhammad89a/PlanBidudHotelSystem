package planBidudHotel.entities;


import java.util.Date;

public class Request {

    private int id;
    private String email;
    private String phoneNo;
    private String fightNum;
    private Date expectedLandDate;
    private Date actualLandDate;
    private Date requestOpenDate;
    private Status status;
    private int numberRoomReserved;

    public Request(String email, String phoneNo, String fightNum, Date expectedLandDate, Date requestOpenDate) {
        this.email = email;
        this.phoneNo = phoneNo;
        this.fightNum = fightNum;
        this.expectedLandDate = expectedLandDate;
        this.requestOpenDate = requestOpenDate;
    }

    public Request(int id, String email, String phoneNo, String fightNum, Date expectedLandDate, Date actualLandDate, Date requestOpenDate, Status status, int numberRoomReserved) {
        this.id = id;
        this.email = email;
        this.phoneNo = phoneNo;
        this.fightNum = fightNum;
        this.expectedLandDate = expectedLandDate;
        this.actualLandDate = actualLandDate;
        this.requestOpenDate = requestOpenDate;
        this.status = status;
        this.numberRoomReserved = numberRoomReserved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFightNum() {
        return fightNum;
    }

    public void setFightNum(String fightNum) {
        this.fightNum = fightNum;
    }

    public Date getExpectedLandDate() {
        return expectedLandDate;
    }

    public void setExpectedLandDate(Date expectedLandDate) {
        this.expectedLandDate = expectedLandDate;
    }

    public Date getActualLandDate() {
        return actualLandDate;
    }

    public void setActualLandDate(Date actualLandDate) {
        this.actualLandDate = actualLandDate;
    }

    public Date getRequestOpenDate() {
        return requestOpenDate;
    }

    public void setRequestOpenDate(Date requestOpenDate) {
        this.requestOpenDate = requestOpenDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getNumberRoomReserved() {
        return numberRoomReserved;
    }

    public void setNumberRoomReserved(int numberRoomReserved) {
        this.numberRoomReserved = numberRoomReserved;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", fightNum='" + fightNum + '\'' +
                ", expectedLandDate=" + expectedLandDate +
                ", actualLandDate=" + actualLandDate +
                ", requestOpenDate=" + requestOpenDate +
                ", status=" + status +
                ", numberRoomReserved=" + numberRoomReserved +
                '}';
    }
}
