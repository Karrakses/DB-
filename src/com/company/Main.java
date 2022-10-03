package com.company;

import com.company.Utils.Map;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        DBConnect dbConnect = new DBConnect();
        Scanner scanner = new Scanner(System.in);

        System.out.println("¬ведите логин:");
        dbConnect.username = scanner.nextLine();

        System.out.println("¬ведите пароль:");
        dbConnect.password = scanner.nextLine();

        dbConnect.ConnectToDB();
        //dbConnect.dbExecuteQuery("Select * from test");
        dbConnect.dbExecuteUpdate("SELECT id_test, test from test ORDER BY id_test ");

        //SELECT PERSON_ID, FIRST_NAME, LAST_NAME FROM PERSON ORDER BY LAST_NAME
    }
}
