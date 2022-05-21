package Bll;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTGTiki {
    private static int port = 60000;
    private static int numThread = 10;
    private static ServerSocket serverSocket = null;
    private static ExecutorService executorService = null;
    private static BufferedReader reader = null;
    private static KhoaRSA rsa = null;

    public ServerTGTiki(){
        rsa = new KhoaRSA();
        rsa.initFromStrings();
        executorService = Executors.newFixedThreadPool(numThread);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ServerTGTikiConnect(){
        System.out.println("Server binding at port "+port);
        System.out.println("Waiting for client...");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String keyMaHoa = reader.readLine();
                String key = rsa.decrypt(keyMaHoa);
                executorService.execute(new WorkTGTiki(socket, key));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ServerTGTikiClose(){
            executorService.shutdownNow();
            if(!serverSocket.isClosed()){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void main(String[] args){
        ServerTGTiki serverTGTiki = new ServerTGTiki();
        serverTGTiki.ServerTGTikiConnect();
    }

}
