package planBidudHotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import planBidudHotel.database.Consts;
import planBidudHotel.utils.Routes;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

public class FXMLReportsController extends  BaseFXMLController implements Initializable {

    @FXML
    AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void onBackPressed(ActionEvent actionEvent) {
        loadScreen(Routes.HOME,"Home",pane);
    }

    public void getReport1(ActionEvent actionEvent) {
        compileQueryHotelReport();
    }

    static final String fileName = "src/planBidudHotel/views/jasper/hotelReport.jrxml";
    static final String outFile = "src/planBidudHotel/views/jasper/";


    public void compileQueryHotelReport() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("**********************************************");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {

                File file = new File(outFile+"Reports"+new Timestamp(new Date().getTime()) +".pdf");
                OutputStream outputSteam = new FileOutputStream(file);
                JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
                JasperPrint print = JasperFillManager.fillReport(
                        jasperDesign,
                        new HashMap<String, Object>(), conn);
                JasperExportManager.exportReportToPdfStream(print, outputSteam);

                JFrame frame = new JFrame("Hotel Report");
                frame.getContentPane().add(new JRViewer(print));
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.pack();
                frame.setVisible(true);

            } catch (SQLException | JRException | NullPointerException | FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void hotelReport() throws JRException, FileNotFoundException {

        Map<String, Object> parameter = new HashMap<String, Object>();


        JRBeanCollectionDataSource studentCollectionDataSource = new JRBeanCollectionDataSource(new ArrayList<>());

        parameter.put("studentDataSource", studentCollectionDataSource);
        parameter.put("title", new String("Hi, I am Title"));

        JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameter, new JREmptyDataSource());

        File file = new File(outFile+"Reports"+new Timestamp(new Date().getTime()) +".pdf");
        OutputStream outputSteam = new FileOutputStream(file);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);

        System.out.println("Report Generated!");
    }

    ///xml
    /**
     * exports customers from db to xml.
     */
    public void exportCustomersToXML(Callback callback) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(
                         Consts.SELECT_ALL_REQUESTS_And_OWNERS);
                 ResultSet rs = stmt.executeQuery()) {

                // create document object.
                Document doc = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder().newDocument();

                // push root element into document object.
                Element rootElement = doc.createElement("TestResultInfo");
                rootElement.setAttribute("exportDate", LocalDateTime.now().toString());
                doc.appendChild(rootElement);

                while (rs.next()) {     // run on all customer records..
                    // create customer element.
                    Element testResult = doc.createElement("TestResult");

                    Element person = doc.createElement("Person");
                    Element nurse = doc.createElement("Nurse");
                    Element testBoard = doc.createElement("TestBoard");
                    // push elements to customer.
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

                        Element element = doc.createElement(
                                rs.getMetaData().getColumnName(i)); // push element to doc.
                        rs.getObject(i); // for wasNull() check..
                        element.appendChild(doc.createTextNode(
                                rs.wasNull() ? "" : rs.getString(i)));  // set element value.
                        if(i<=5){
                            if(i==1){
                                createElementIDAttr(doc,rs,person,i);
                            }else {
                                person.appendChild(element);  // push element to customer.
                            }
                            if(i==5)
                                testResult.appendChild(person);
                        }else if(i<=10){
                            if(i==6){
                                createElementIDAttr(doc,rs,nurse,i);
                            }else {
                                nurse.appendChild(element);  // push element to customer.
                            }
                            if(i==10)
                                testResult.appendChild(nurse);
                        }else if(i<=14){
                            if(i==11){
                                createElementIDAttr(doc,rs,testBoard,i);
                            }else {
                                testBoard.appendChild(element);  // push element to customer.
                            }
                            if(i==14)
                                testResult.appendChild(testBoard);
                        }else {
                            //15//16
                            testResult.appendChild(element);
                        }
                    }
                    // push customer to document's root element.
                    rootElement.appendChild(testResult);
                }
                // write the content into xml file

                DOMSource source = new DOMSource(doc);
                File file = new File("xml/TestResult_"+ LocalDate.now()+".xml");
                file.getParentFile().mkdir(); // create xml folder if doesn't exist.
                StreamResult result = new StreamResult(file);
                TransformerFactory factory = TransformerFactory.newInstance();

                // IF CAUSES ISSUES, COMMENT THIS LINE.
                factory.setAttribute("indent-number", 2);
                //

                Transformer transformer = factory.newTransformer();

                // IF CAUSES ISSUES, COMMENT THESE 2 LINES.
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                //
                transformer.transform(source, result);
                System.out.println("Tests results data exported successfully!");
                callback.call(true);

            } catch (SQLException | NullPointerException | ParserConfigurationException
                    | TransformerException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void createElementIDAttr(Document doc, ResultSet rs, Element element, int index) throws SQLException {
        Attr attr = doc.createAttribute("ID");
        attr.setValue(rs.getString(index));
        element.setAttributeNode(attr);
    }


}
