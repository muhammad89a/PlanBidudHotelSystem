package planBidudHotel.entities;

public class Hotel {

    private int id;
    private String hotelName;
    private int totalRooms;
    private String managerFirstName;
    private String managerLastName;
    private String managerPhoneNo;

    public Hotel(int id, String hotelName, int totalRooms, String managerFirstName, String managerLastName, String managerPhoneNo) {
        this.id = id;
        this.hotelName = hotelName;
        this.totalRooms = totalRooms;
        this.managerFirstName = managerFirstName;
        this.managerLastName = managerLastName;
        this.managerPhoneNo = managerPhoneNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getManagerPhoneNo() {
        return managerPhoneNo;
    }

    public void setManagerPhoneNo(String managerPhoneNo) {
        this.managerPhoneNo = managerPhoneNo;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", totalRooms=" + totalRooms +
                ", managerFirstName='" + managerFirstName + '\'' +
                ", managerLastName='" + managerLastName + '\'' +
                ", managerPhoneNo='" + managerPhoneNo + '\'' +
                '}';
    }

    /************************************************************************/

    public int calcAvailableRooms(){
        return totalRooms;
    }
}
