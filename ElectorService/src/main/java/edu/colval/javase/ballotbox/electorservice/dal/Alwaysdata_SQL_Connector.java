package edu.colval.javase.ballotbox.electorservice.dal;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Alwaysdata_SQL_Connector implements I_SQL_Connector{

    private Connection conn;

    public Alwaysdata_SQL_Connector() {
        this.conn = connect();
    }

    @Override
    public Connection connect() {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUser("boey");
        datasource.setPassword("ballotbox");
        datasource.setServerName("mysql-boey.alwaysdata.net"); //.com
        datasource.setPort(3306);
        datasource.setDatabaseName("boey_electorervice");
        Connection conn;
        // connection-string : jdbc:mariadb://mysql-boey.alwaysdata.net:3306/boey_electorservice?user=boey&password=ballotbox
        try {
            //conn = datasource.getConnection();
            StringBuilder url = new StringBuilder();
            url.append("jdbc:mariadb://");
            url.append(datasource.getServerName());
            url.append(":");
            url.append(datasource.getPort());
            url.append("/");
            url.append(datasource.getDatabaseName());
            url.append("?user=");
            url.append(datasource.getUser());
            url.append("&password=");
            url.append(/*datasource.getPassword())*/"ballotbox");
            String connectionString = url.toString();

            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(connectionString);

            return conn;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Alwaysdata_SQL_Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Connection getConnection() {
        return conn;
    }
}
