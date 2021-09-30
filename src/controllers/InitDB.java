package controllers;


import models.Account;
import models.User;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;

public class InitDB {

    public static void dropTable(String table){
        Statement s = null;
        try {
            String dropTable = "DROP TABLE IF EXISTS " + table;
            s = initialiseDB().createStatement();
            s.executeUpdate(dropTable);
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("Encountered an error when dropping table " + table);
        }
        finally {
            try {
                s.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void databaseStartup() throws SQLException {
        createUsersTable(initialiseDB());
        createAccountsTable(initialiseDB());
        createTransactionsTable(initialiseDB());
    }

    public static Connection initialiseDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            con = DriverManager.getConnection("jdbc:sqlite:../BankingDB.db", config.toProperties());
        }
        catch(ClassNotFoundException cnfEx){
            cnfEx.printStackTrace();
            System.out.println("The SQLite class could not be found.");
            System.out.println("Are you missing a dependency?");
            System.out.println("You can dowbload the required class from - https://github.com/xerial/sqlite-jdbc/releases");
        }
        catch(SQLException sqlEx){
            sqlEx.printStackTrace();
            System.out.println("An SQL Exception has been encountered.");
            System.out.println("Make sure you are connected to the internet and/or update the location of your database in the settings.");
        }
        return con;
    }

    private static void createUsersTable(Connection con) throws SQLException {
        Statement s = null;
        try {
            s = con.createStatement();
            String createUsersTable = "CREATE TABLE IF NOT EXISTS tblUsers " +
                    "(USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " USER_FORENAME TEXT NOT NULL," +
                    " USER_SURNAME TEXT NOT NULL," +
                    " USER_USERNAME TEXT NOT NULL," +
                    " USER_PASSWORD TEXT NOT NULL)";
            s.executeUpdate(createUsersTable);
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("Error encountered when creating users table");
        }
        finally {
            s.close();
            con.close();;
        }
    }

    private static void createAccountsTable(Connection con) throws SQLException {
        Statement s = null;
        try {
            s = con.createStatement();
            String createAccountsTable = "CREATE TABLE IF NOT EXISTS tblAccounts " +
                    "(ACCOUNT_NUMBER INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " ACCOUNT_BALANCE REAL NOT NULL," +
                    " ACCOUNT_NAME TEXT NOT NULL," +
                    " ACCOUNT_TYPE TEXT NOT NULL," +
                    " ACCOUNT_USER_ID INTEGER," +
                    " FOREIGN KEY (ACCOUNT_USER_ID) REFERENCES tblUsers(USER_ID))";
            s.executeUpdate(createAccountsTable);
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("Error encountered when creating accounts table");
        }
        finally {
            s.close();
            con.close();;
        }
    }

    private static void createTransactionsTable(Connection con) throws SQLException {
        Statement s = null;
        try {
            s = con.createStatement();
            String createTransactionsTable = "CREATE TABLE IF NOT EXISTS tblTransactions " +
                    "(TRANSACTION_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " TRANSACTION_DATE TEXT NOT NULL," +
                    " TRANSACTION_PARTY TEXT NOT NULL," +
                    " TRANSACTION_AMOUNT REAL NOT NULL," +
                    " TRANSACTION_ACCOUNT_NUMBER INTEGER, " +
                    " FOREIGN KEY (TRANSACTION_ACCOUNT_NUMBER) REFERENCES tblAccounts(ACCOUNT_NUMBER))";
            s.executeUpdate(createTransactionsTable);
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("Error encountered when creating transactions table");
        }
        finally {
            s.close();
            con.close();;
        }
    }

    public static User checkLoginDetails(String username, String password){
        PreparedStatement pst = null;
        try {
            String query = "SELECT * FROM tblUsers WHERE USER_USERNAME = ? AND USER_PASSWORD = ?";
            pst = initialiseDB().prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                int userID = rs.getInt("USER_ID");
                String forename = rs.getString("USER_FORENAME");
                String surname = rs.getString("USER_SURNAME");
                String UN = rs.getString("USER_USERNAME");
                String PW = rs.getString("USER_PASSWORD");
                return new User(userID, UN, PW, forename, surname);
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("Encountered an error trying to validate login details.");
        }
        finally {
            try {
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public static void addAccount(Connection con, int userID, Account account){
        PreparedStatement pst = null;
        try {

            String addAccountQuery = "INSERT INTO tblAccounts (ACCOUNT_BALANCE, ACCOUNT_NAME, ACCOUNT_TYPE, ACCOUNT_USER_ID) VALUES " +
                    "(?, ?, ?, ?)";
            pst = con.prepareStatement(addAccountQuery);
            pst.setDouble(1, account.getAccountBalance());
            pst.setString(2, account.getAccountName());
            pst.setString(3, account.getAccountType());
            pst.setInt(4, userID);
            pst.executeUpdate();
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("Encountered an error when adding a new account");
        }
        finally {
            try {
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static ArrayList<Account> getAccountNames(Connection con, int userID){
        PreparedStatement pst = null;
        try {
            String getAccountNames = "SELECT ACCOUNT_NUMBER, ACCOUNT_NAME FROM tblAccounts WHERE ACCOUNT_USER_ID = ?";
            pst = con.prepareStatement(getAccountNames);
            pst.setInt(1, userID);
            ResultSet rs = pst.executeQuery();
            ArrayList<Account> accounts = new ArrayList<>();
            while(rs.next()){
                int accountNumber = rs.getInt("ACCOUNT_NUMBER");
                String accountName = (rs.getString("ACCOUNT_NAME"));
                accounts.add(new Account(accountNumber, accountName));
            }
            return accounts;
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("Encountered an error when getting account names");
        }
        finally {
            try {
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public static Account getSelectedAccount(Connection con, int accNumber){
        PreparedStatement pst = null;
        try {
            String getSelectedAccount = "SELECT * FROM tblAccounts WHERE ACCOUNT_NUMBER = ?";
            pst = con.prepareStatement(getSelectedAccount);
            pst.setInt(1, accNumber);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                int number = rs.getInt("ACCOUNT_NUMBER");
                String name = rs.getString("ACCOUNT_NAME");
                String type  = rs.getString("ACCOUNT_TYPE");
                double balance = rs.getDouble("ACCOUNT_BALANCE");
                return new Account(number, name, balance, type);
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
            System.out.println("Encountered a problem trying to retrieve selected account");
        }
        finally{
            try {
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }


}
