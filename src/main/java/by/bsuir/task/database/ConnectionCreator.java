package by.bsuir.task.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {

    public Connection createConnection() {
        try {
            Class<? extends ConnectionCreator> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");

            Properties property = new Properties();
            property.load(inputStream);

            String url = property.getProperty("db.url");
            String name = property.getProperty("db.name");
            String password = property.getProperty("db.password");
            String driver = property.getProperty("db.driver");

            Class.forName(driver);
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Driver is not found" + e.getMessage(), e);
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found" + e.getMessage(), e);
        }
    }
}
