package by.bsuir.task.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final ReentrantLock connectionLock = new ReentrantLock();
    private static final ReentrantLock returnLock = new ReentrantLock();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private final ConnectionCreator connectionCreator = new ConnectionCreator();
    private Deque<Connection> connections;
    private Semaphore semaphore;
    private int connectionSize;

    private ConnectionPool() {
        readConnectionSizeFromProperties();
        initConnections();
        createConnections();
    }

    public static ConnectionPool getInstance() {
        if (!initialized.get()) {
            try {
                lock.lock();
                if (!initialized.get()) {
                    instance = new ConnectionPool();
                    initialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }


    private void initConnections() {
        connections = new ArrayDeque<>();
        semaphore = new Semaphore(connectionSize);
    }


    private void createConnections() {
        for (int i = 0; i < connectionSize; i++) {
            Connection connection = connectionCreator.createConnection();
            connections.push(connection);
        }
        if (connections.isEmpty()) {
            throw new IllegalArgumentException("Connections are not created!");
        }
    }


    private void readConnectionSizeFromProperties() {
        try {
            Class<? extends ConnectionPool> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");

            Properties property = new Properties();
            property.load(inputStream);

            connectionSize = Integer.parseInt(property.getProperty("db.connectionSize"));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found" + e.getMessage(), e);
        }
    }


    public Connection getConnection() {
        try {
            connectionLock.lock();
            semaphore.acquire();
            return connections.pop();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException();
        } finally {
            connectionLock.unlock();
        }
    }

    public void returnConnection(Connection connection) {
        try {
            returnLock.lock();
            connections.push(connection);
            semaphore.release();
        } finally {
            returnLock.unlock();
        }
    }
}
