package com.example.db_hw;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDB {
    //String username = "root";
    //String password = DbHwApplication.pass;
// change to use in aws
    String username = "b53b88e7c5d5d8";
    String password = "d9014f52";
   // String url = "jdbc:mysql://"+DbHwApplication.ip+":3306/";
    String url = "jdbc:mysql://eu-cdbr-west-01.cleardb.com:3306/";

    //String url = "eu-cdbr-west-01.cleardb.com";
   // String schemaName = "mydb2";
    String schemaName = "heroku_62d31ac1d93af30";

    String tableName = "persons";
    List<String > persons = new ArrayList();

    public MyDB(){
        connectAndQuery();
    }

    private void connectAndQuery(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(url, username,password)){
            if(!conn.isClosed()){
                System.out.println("DB Conn ok ");
                initializeDatabase(conn);
//                // Get the rows:
                String sql = "SELECT * FROM " + tableName;
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();
                persons.clear();
                while (resultSet.next()){
                    String firstName = resultSet.getString("name");
                    System.out.println("Name: " + firstName);
                    persons.add(firstName);
                }
            }
            // new
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }
//hello cheking the change
    private void initializeDatabase(Connection conn) throws Exception{
        // 1. make sure there exists a schema, named mydb. If not, create one
        String sql = "CREATE DATABASE IF NOT EXISTS " + schemaName;
        Statement statement = conn.createStatement();
        statement.execute(sql);
        statement.execute("USE " + schemaName );

        // 2. make sure there exists a table, named persons. If not, create one:
        //    Primary key: idpersons INT AUTO_INCREMENT
        //    Column: name VARCHAR(45)
        //sql = "CREATE TABLE IF NOT EXISTS `mydb2`.`persons` (\n" +
                sql = "CREATE TABLE IF NOT EXISTS `heroku_62d31ac1d93af30`.`persons` (\n" +
                "  `idpersons` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`idpersons`));";
        statement.execute(sql); // DDL

        // 3. If there was no table named persons, then insert two rows into the new table: "Anna" and "Bent"
        // lav denne øvelse indtil kl 9.05
        sql = "INSERT IGNORE INTO " + tableName + " VALUES (1, 'Anna')";
        statement.execute(sql);
        sql = "INSERT IGNORE INTO " + tableName + " VALUES (2, 'Bent')";
        statement.execute(sql);
    }

    public List<String> getPersons() {
        return persons;
    }

}
