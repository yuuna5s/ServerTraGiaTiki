package Bll;

import ENTRY.product;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WorkTGTiki implements Runnable {
    private Socket socket;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private KhoaAES khoaAES = null;

    public WorkTGTiki() {
    }

    public WorkTGTiki(Socket socket, String key) {
        this.khoaAES = new KhoaAES();
        this.khoaAES.setStringKey(key);
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e) {
            //Lỗi
            System.out.println("Lỗi Bll -> WorkTGTiki -> WorkTGTiki");
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Client " + socket.toString() + " accepted");
        try {
            while (true) {
                String lineMaHoa = reader.readLine();
                String line = khoaAES.decrypt(lineMaHoa);
                if (line.equalsIgnoreCase("byeservertiki")) {
                    System.out.println("Client " + socket.toString() + " close");
                    break;
                }
                work(line);
            }
        } catch (IOException e) {
            //Lỗi
            System.out.println("Lỗi Bll -> WorkTGTiki -> run");
            e.printStackTrace();
        }
    }

    public void work(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
        String bool = stringTokenizer.nextToken();
        String nameorid = stringTokenizer.nextToken();
        if (bool.equalsIgnoreCase("search")) {
            SearchSP(nameorid);
        }
        if (bool.equalsIgnoreCase("detail")) {
            DetailSP(nameorid);
        }
    }

    public void sent(String line){
        try {
            String lineMaHoa = khoaAES.encrypt(line);
            writer.write(lineMaHoa + "\n");
            writer.flush();
        } catch (IOException e) {
            //Lỗi
            System.out.println("Lỗi Bll -> WorkTGTiki -> sent");
            e.printStackTrace();
        }
    }

    //Search sent
    public void SearchSP(String line){
        Bllproduct bllproduct = new Bllproduct();
        ArrayList<product> productList = bllproduct.getProducts(line);
        if(productList!=null){
            sent(""+productList.size());
            for(product product : productList){
                String linesent = product.getId()+";"+product.getName()+";"+product.getPath();
                sent(linesent);
            }
        }
        else {
            sent("Không tìm thấy sản phẩm nào cả");
        }
    }

    //Detail
    public void DetailSP(String line){

    }
}