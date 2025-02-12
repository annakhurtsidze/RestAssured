package DataBaseAccessSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseAccessSQL {

//connection ტიპის ობიექტი რომელიც უზრუნველყოფს ბაზასთან კავშირს
    public static Connection connectionSMS;

public static Connection getConnectionSMS(){

    try {
        if(connectionSMS == null || connectionSMS.isClosed()){

            //sql სერვერთან დაკავშირება
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //მონაცემთა ბაზასთან ქონექშენი
                connectionSMS = DriverManager.getConnection("jdbc:sqlserver://10.195.105.247; encrypt=false; trustedServerCertificate = false" , "Training", "Aa123456");
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return connectionSMS;
}
}
