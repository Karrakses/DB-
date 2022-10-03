package com.company;

import com.company.Utils.Map;
import java.io.IOException;
import java.sql.*;

import com.sun.rowset.CachedRowSetImpl;

public class DBConnect {


    //Connection
    public static Connection connection = null;

    public String jbdcURL = "jdbc:postgresql://46.229.214.241:5432/postgres";
    public String username;
    public String password;
    public void ConnectToDB() throws IOException, SQLException {
        try {

            Connection connection = DriverManager.getConnection(jbdcURL,username,password);
            this.connection = connection;
            System.out.println("Connect");
      //      connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error to connect");
        }
        finally {
      //      connection.close();
        }

    }
    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
               connection.close();
            }
        } catch (Exception e){
            throw e;
        }
    }

    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            ConnectToDB();
            System.out.println("Select statement: " + queryStmt + "\n");
            //Create statement
            stmt = connection.createStatement();
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
                crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException | IOException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);

        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public  void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            ConnectToDB();
            //Create Statement
            stmt = connection.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException | IOException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);

        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }

}
