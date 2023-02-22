package Model;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author hcorrea
 */
public class Config {

    private String DB_Provider;
    private String DB_Port;
    private String DB_DataBaseName;
    private String DB_User;
    private String DB_Password;
    private String DB_Server;
    private String DB_DriverClass;

    private void loadConfig() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("Config.xml"));

            DB_Provider = document.getElementsByTagName("DB_Provider").item(0).getTextContent();
            DB_Port = document.getElementsByTagName("DB_Port").item(0).getTextContent();
            DB_DataBaseName = document.getElementsByTagName("DB_DataBaseName").item(0).getTextContent();
            DB_User = document.getElementsByTagName("DB_User").item(0).getTextContent();
            DB_Password = document.getElementsByTagName("DB_Password").item(0).getTextContent();
            DB_Server = document.getElementsByTagName("DB_Server").item(0).getTextContent();
            DB_DriverClass = document.getElementsByTagName("DB_DriverClass").item(0).getTextContent();

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public String getDB_Provider() {
        if (DB_Provider == null) {
            loadConfig();
        }
        return DB_Provider;
    }

    public String getDB_Server() {
        if (DB_Server == null) {
            loadConfig();
        }
        return DB_Server;
    }

    public String getDB_Port() {
        if (DB_Port == null) {
            loadConfig();
        }
        return DB_Port;
    }

    public String getDB_DataBaseName() {
        if (DB_DataBaseName == null) {
            loadConfig();
        }
        return DB_DataBaseName;
    }

    public String getDB_User() {
        if (DB_User == null) {
            loadConfig();
        }
        return DB_User;
    }

    public String getDB_Password() {
        if (DB_Password == null) {
            loadConfig();
        }
        return DB_Password;
    }

    public String getDB_DriverClass() {
        if (DB_DriverClass == null) {
            loadConfig();
        }
        return DB_DriverClass;
    }

    //private String connectionstr = "jdbc:sqlserver://localhost:1433;databaseName=mit_realstate;user=mit;password=123321.mit"
    public String getConnectionstr() {
        return getDB_Provider() + "://" + getDB_Server() + ":" + getDB_Port() + ";databaseName=" + getDB_DataBaseName() + ";user=" + getDB_User() + ";password=" + getDB_Password();
    }
}
