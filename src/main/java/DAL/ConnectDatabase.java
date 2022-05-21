package DAL;

import java.sql.*;

public class ConnectDatabase {
    static Connection connection;
    static Statement statement;
    static PreparedStatement preparedStatement;

    //Connection
    public static void ConnectionDB(){
        String host = "localhost";
        String port = "3306";
        String dbname = "price_statistics";
        String dbuser = "root";
        String dbpwd = "";

        String dbPath ="jdbc:mysql://"+host+":" + port + "/" + dbname + "?useUnicode=yes&characterEncoding=UTF-8";

        try{
            connection = (Connection) DriverManager.getConnection(dbPath, dbuser, dbpwd);
            statement = connection.createStatement();
            System.out.println("connected");
        }
        catch (SQLException e){
            System.out.println("ERROR -> DatabaseManager -> ConectionDB -> "+e.getMessage());
        }
    }

    public static ResultSet readTable(String namesp){
        String sql = "SELECT * FROM products WHERE name LIKE '%"+namesp+"%' ORDER BY id ASC";
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        }
        catch (SQLException e) {
            System.out.println("DAL -> ConnectDatabase -> readTable -> "+e.getMessage());
        }
        return rs;
    }

    public static void main(String[] args){
        ConnectionDB();
    }

}
