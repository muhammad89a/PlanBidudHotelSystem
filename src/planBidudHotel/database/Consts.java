package planBidudHotel.database;

import java.net.URLDecoder;

public class Consts {

    protected static final String DB_FILEPATH = getDBPath();
    public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";

    public static final String SQL_SEL_RESERVATION = "SELECT * FROM Reservation";


    /**
     * find the correct path of the DB file
     *
     * @return the path of the DB file (from eclipse or with runnable file)
     */
    private static String getDBPath() {
        try {
            String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decoded = URLDecoder.decode(path, "UTF-8");
            // System.out.println(decoded) - Can help to check the returned path
            System.out.println("decoded:" + decoded);
            if (decoded.contains(".jar")) {
                decoded = decoded.substring(0, decoded.lastIndexOf('/'));
                return decoded + "/database/planBidudHotelDB_hw1.accdb";
            } else {
//                decoded = decoded.substring(0, decoded.lastIndexOf("out/"));
                System.out.println("decoded:"+decoded + "planBidudHotel/database/planBidudHotelDB_hw1.accdb");
                return decoded + "planBidudHotel/database/planBidudHotelDB_hw1.accdb";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
