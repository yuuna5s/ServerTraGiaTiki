package GUI;

import Bll.ServerTGTiki;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIServerTGTiki extends JFrame{
    private JButton closeButton;
    private JButton openButton;
    private JPanel jPanel1;
    private JLabel TTJLabel;
    private ServerTGTiki serverTGTiki = null;

    public GUIServerTGTiki(){
        setContentPane(jPanel1);
        setSize(500,250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        serverTGTiki = new ServerTGTiki();

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TTJLabel.setText("Close");
                serverTGTiki.ServerTGTikiClose();
            }
        });

        TTJLabel.setText("Open");
        serverTGTiki.ServerTGTikiConnect();

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    public static void main(String[] args){
        GUIServerTGTiki guiServerTGTiki = new GUIServerTGTiki();
    }

}
