package DAL;

import ENTRY.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALproduct extends ConnectDatabase{
    public DALproduct(){ConnectionDB();}

    //Search SP
    public ArrayList<product> getProducts(String namesp){
        ArrayList<product> products = new ArrayList<product>();
        product sp = new product();
        ResultSet resultSet = readTable(namesp);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    sp = new product();
                    sp.setId(resultSet.getString("id"));
                    sp.setName(resultSet.getString("name"));
                    sp.setPath(resultSet.getString("img_url"));
                    products.add(sp);
                }
            }
            catch (SQLException e){
                System.out.println("DAL -> ConnectDatabase -> getProducts -> "+e.getMessage());
            }
        }
        return products;
    }
}
