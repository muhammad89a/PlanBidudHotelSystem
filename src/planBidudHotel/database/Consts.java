package planBidudHotel.database;

import planBidudHotel.entities.Status;

import java.net.URLDecoder;
import java.sql.Date;

public class Consts {

    protected static final String DB_FILEPATH = getDBPath();
    public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
    /**
     * find the correct path of the DB file
     *
     * @return the path of the DB file (from eclipse or with runnable file)
     */
    private static String getDBPath() {
        try {
            String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decoded = URLDecoder.decode(path, "UTF-8");
            System.out.println("decoded:" + decoded);
            if (decoded.contains(".jar")) {
                decoded = decoded.substring(0, decoded.lastIndexOf('/'));
                return decoded + "/database/planBidudSystemDB.accdb";
            } else {
                return decoded + "planBidudHotel/database/planBidudSystemDB.accdb";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //country
    public static final String SQL_SEL_COUNTRY = "SELECT * FROM Country";
    public static final String SQL_INSERT_COUNTRY = "insert into Country (CountName, NeedSolation)"
            + " VALUES (?, ?);";
    // request
    public static final String SQL_SEL_REQUEST = "SELECT * FROM Request";
    public static final String SQL_DEL_REQUEST = "DELETE FROM Request WHERE ID=?;";

    public static final String SQL_INSERT_REQUEST = "insert into Request (eMail, PhoneNum, fightNum,ExpextedLandDate,RequestOpenDate,RequestStatus,CountryID,OwnerID,HotelID,CityID)"
            + " VALUES (?, ?, ?, ?, ?, ?,?,?,?,?);";
    public static final String SQL_UPD_REQUEST = "UPDATE Request" + "SET personID = ?, email= ?"
            + "SET phoneNumber = ?, tripID= ?" + "WHERE ID = ?;";

    public static final String SELECT_ALL_REQUESTS_And_OWNERS = "SELECT * FROM Request,Citizen  Where Request.OwnerID=Citizen.citizenID";




}
