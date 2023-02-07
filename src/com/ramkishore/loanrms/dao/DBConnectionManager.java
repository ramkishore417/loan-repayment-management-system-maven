package com.ramkishore.loanrms.dao;

/*
 * @created 02/02/2023 - 07:52 PM
 * @project loan-repayment-management-system
 * @author RAMKISHORE
 */

import com.ramkishore.loanrms.exception.LoanRepaymentException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {

    private static Connection con = null;
    private static DBConnectionManager instance;

    private DBConnectionManager() throws LoanRepaymentException {
        // TYPE YOUR CODE HERE
        FileInputStream fis=null;

        try {
            fis = new FileInputStream("database.properties");
        } catch (FileNotFoundException e) {
            throw new LoanRepaymentException("\nError: Database properties file is not found, check for the file location - DBConnectionManager() | "+e.getMessage());
        }

        //Load Database Properties file
        Properties properties = new Properties();
        try {
            properties.load(fis);
        } catch (IOException e) {
            throw new LoanRepaymentException("\nError: Unable to load properties, property fields missing - DBConnectionManager() | "+e.getMessage());
        }

        String drivername = (String) properties.get("drivername");
        String url = (String) properties.get("url");
        String username = (String) properties.get("username");
        String password = (String) properties.get("password");

        // Load Driver class
        try {
            Class.forName(drivername);
        } catch (ClassNotFoundException e) {
            throw new LoanRepaymentException("Driver Name not found, check Driver name correctly "+e.getMessage());
        }

        //Create Connection
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new LoanRepaymentException("\nError: Unable to establish Database connection, check connection details - DBConnectionManager() | "+e.getMessage());
        }
    }
    public static DBConnectionManager getInstance() throws LoanRepaymentException {
        try {
            if ((instance == null) || (instance.getConnection().isClosed())) {
                instance = new DBConnectionManager(); // class "DBConnectionManager" object is created and Default constructor will get called.
            }
        } catch (Exception e) {
            throw new LoanRepaymentException(e.getMessage());
        }
        return instance;
    }
    public Connection getConnection() {
        return con;
    }
}

