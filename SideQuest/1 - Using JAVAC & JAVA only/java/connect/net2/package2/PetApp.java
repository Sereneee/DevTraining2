package net2.package2;

import java.sql.*;
import java.util.*;  // Import the Scanner class

public class PetApp {

    // connect to database / create db
    public static void createNewDatabase(String url, String dbname) {
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("\nDriver name: \t\t\t" + meta.getDriverName());
                System.out.println("Database created: \t\t" + dbname);
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // create pet table in database
    public static void createPetTable(String url) {
        
        String tblNm = "pet", sql = "";

        // drop table first, to get rid of old data
        sql = "DROP TABLE IF EXISTS " + tblNm;
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // drop table
            pstmt.executeUpdate();
            System.out.println("Table dropped (if exists): \t" + tblNm);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // SQL statement for creating a new table
        sql = "CREATE TABLE "+ tblNm + " ("
            +"id integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
            +"name varchar(30) NOT NULL,"
            +"age int(10) NULL,"
            +"type varchar(20) NULL"
            +");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table created: \t\t\t" + tblNm);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // insert some data
    public static void initData(String url) {
        String sql = "insert into pet (name, age, type) values"
        +"('gratel', 2, 'dog'),"
        +"('benji', 17, 'cat'),"
        +"('apple', 192, 'snapping turtle'),"
        +"('cammy', 56001, 'dino'),"
        +"('pi', 35, 'tiger');";
 
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("\nAdded some data into table 'pet'.\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // view all data
    public static void selectAll(String url){
        String sql = "SELECT * FROM pet;";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            System.out.println("-------------ALL AVAILABLE PETS------------");
            System.out.println("id \t" + "name \t" + "age \t" + "type");
            System.out.println("-------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                rs.getString("name") + "\t" +
                rs.getInt("age") + "\t" +
                rs.getString("type"));
            }
            System.out.println("-------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  
    // insert data
    public static void insertPet(String url){

        Scanner read = new Scanner(System.in);  // Create a Scanner object
        String name = "", type = "";
        int age = 999999999;

        try {
            // retrieve user input
            System.out.print("Enter pet NAME: ");
            name = read.nextLine();

            System.out.print("Enter pet AGE: ");
            age = read.nextInt();
            read.nextLine();

            System.out.print("Enter pet TYPE: ");
            type = read.nextLine();    

            // execute sql statement
            String sql = "INSERT INTO pet (name, age, type) VALUES(?,?, ?)";
    
            try (Connection conn = DriverManager.getConnection(url);
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, type);
                pstmt.executeUpdate();
                System.out.println("\nSuccesfully added a new pet.\n");
            } catch (SQLException e) {
                System.out.println("\nFailed to add a new pet.");
                System.out.println(e.getMessage() + "\n");
            }
        }catch(Exception e) {
            System.out.println("\nFailed to add a new pet.");
            System.err.println("Error: " + e + "\n");
        }
    }

    // update data
    public static void updatePet(String url) {
        Scanner read = new Scanner(System.in);  // Create a Scanner object
        int id = 999999999, age = 999999999;
        String name = "", type = "";
        
        try {
            // retrieve user input
            System.out.print("\nEnter ID of pet to be UPDATED: ");
            id = read.nextInt();
            read.nextLine();

            System.out.print("Enter NEW pet NAME: ");
            name = read.nextLine();
            
            System.out.print("Enter NEW pet AGE: ");
            age = read.nextInt();
            read.nextLine();

            System.out.print("Enter NEW pet TYPE: ");
            type = read.nextLine();

        
            String sql = "UPDATE pet SET name = ? , "
                + "age = ? ,"
                + "type = ? "
                + "WHERE id = ?";
    
            try (Connection conn = DriverManager.getConnection(url);
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                // set the corresponding param
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, type);
                pstmt.setInt(4, id);

                // execute the update statement
                pstmt.executeUpdate();
                System.out.println("\nSuccesfully updated pet with ID: " + id + ".\n");
    
            } catch (SQLException e) {
                System.out.println("\nFailed to update pet.");
                System.err.println("Error: " + e + "\n");
            }
        }catch(Exception e){
            System.out.println("\nFailed to update pet.");
            System.err.println("Error: " + e + "\n");
        }
    }

    // delete data
    public static void deletePet(String url) {
        Scanner read = new Scanner(System.in);  // Create a Scanner object
        int id = 999999999;

        try {
            System.out.print("\nEnter ID of pet to be DELETED: ");
            id = read.nextInt();
            

            String sql = "DELETE FROM pet WHERE id = ?";
    
            try (Connection conn = DriverManager.getConnection(url);
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                // set the corresponding param
                pstmt.setInt(1, id);
                // execute the delete statement
                pstmt.executeUpdate();
                System.out.println("\nSuccesfully deleted pet with ID: " + id + ".\n");
                        
            } catch (SQLException e) {
                System.out.println("\nFailed to delete pet.");
                System.err.println("Error: " + e + "\n");
            }
        }catch(Exception e){
            System.out.println("\nFailed to delete pet.");
            System.err.println("Error: " + e + "\n");
        }
    }

    public static void main(String[] args) {

        // initialising database
        System.out.println("\n~~~~~~~~~~~WELCOME TO THE PET APP~~~~~~~~~~~\n");
        System.out.println("Initialising database...");
        String dbname = "petDb.db";
        String url = "jdbc:sqlite:C:/sqlite/db/" + dbname;
        createNewDatabase(url, dbname);
        createPetTable(url);
        initData(url);
        selectAll(url);
        System.out.println("\nInitialisation complete...\n");

        String input = "";
        Scanner read = new Scanner(System.in);
        
        do{            
            System.out.print("________________Pet App Menu________________"
            + "\n1. Insert new pet"
            + "\n2. Update pet by id"
            + "\n3. Delete pet by id"
            + "\n4. View all pet"
            + "\n0. Exit."
            + "\nPlease select an option: ");
            input = read.nextLine();
                        
            if(input.equals("1")){
                insertPet(url);
            }else if(input.equals("2")){
                updatePet(url);
            }else if(input.equals("3")){
                deletePet(url);
            }else if(input.equals("4")){
                System.out.println();
                selectAll(url);
                System.out.println();
            }else if(input.equals("0")){
                System.out.println("\nYou have decided to exit the Pet App. Goodbye.\n");
                break;
            }else{
                System.out.println("\nNo such option. Please choose again.\n");
            }
            
        }while(!input.equals("0"));
        
        read.close();


    }
}