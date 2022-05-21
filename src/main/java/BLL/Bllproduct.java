package Bll;

import DAL.DALproduct;
import ENTRY.product;

import java.util.ArrayList;

public class Bllproduct {
    DALproduct daLproduct;

    public Bllproduct(){ daLproduct = new DALproduct(); }

    public ArrayList<product> getProducts(String namesp){
        ArrayList<product> products = null;
        if(daLproduct.getProducts(namesp).size() > 0){
            products = daLproduct.getProducts(namesp);
        }
        return products;
    }
}
