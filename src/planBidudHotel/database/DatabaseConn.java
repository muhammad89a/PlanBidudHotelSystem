package planBidudHotel.database;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConn {

    private static DatabaseConn databaseConn = null;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseConn() {
        createConnection();
    }

    public static   DatabaseConn getInstance() {
        if (databaseConn == null) {
            databaseConn = new DatabaseConn();
        }
        return databaseConn;
    }

    void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
        }
    }

    //
//    public boolean deleteBook(listBookController.Book book) {
//        try {
//            String deleteStatement = "DELETE FROM BOOK WHERE ID = ?";
//            PreparedStatement stmt = conn.prepareStatement(deleteStatement);
//            stmt.setString(1, book.getId());
//            int  res = stmt.executeUpdate();
//            if (res == 1) {
//                return true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    public boolean isBookAlreadyIssued(listBookController.Book book) {
//        try {
//            String checkstmt = "SELECT COUNT(*) FROM ISSUE WHERE bookID=?";
//            PreparedStatement stmt = conn.prepareStatement(checkstmt);
//            stmt.setString(1, book.getId());
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                int count = rs.getInt(1);
//                System.out.println(count);
//                return (count > 0);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occurred", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getLocalizedMessage());
            return false;
        } finally {
        }
    }

    ///
//    public void getReportExport(AuthContext context, java.util.Date from, java.util.Date to, OutputStream os) throws DSException {
//
//        isContextValidFor(context,roleId -> { if(roleId == -1) throw new DSAuthException("Invalid Context"); },1);
//
//        java.sql.Date sqlFromDate = convertUtilToSql(from);
//        java.sql.Date sqlToDate = convertUtilToSql(to);
//        String jasperFilePath = new File(JASPER_BIN).getPath();
//
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("fromDate",sqlFromDate);
//        params.put("toDate",sqlToDate);
//
//        try {
//            JasperPrint print = JasperFillManager.fillReport(jasperFilePath, params , connection);
//            byte[] arr = serialize(print);
//            os.write(arr);
//        } catch (JRException | IOException e) {
//            e.printStackTrace();
//        }
//    }

}
