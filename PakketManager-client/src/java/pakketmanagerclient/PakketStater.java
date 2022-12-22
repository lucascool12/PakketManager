/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakketmanagerclient;
import beans.DataBeanRemote;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 *
 * @author lucas
 */
public class PakketStater extends JFrame
{
    private final DataBeanRemote db;
    public ArrayList<JLabel> statusAantal;
    public PakketStater(final DataBeanRemote db){
        super();
        this.db = db;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, db.getAantalStatussen()));
        statusAantal = new ArrayList<>();
        for(int i = 0; i < db.getAantalStatussen(); i++){
            this.add(new JLabel(db.getStatusNaam(i)), 2, i);
            JLabel statussen = new JLabel(Long.toString(db.getAantalPakketMetStatus(i)));
            statusAantal.add(statussen);
            this.add(statussen, 1, i);
        }
        JButton b = new JButton("Refresh");
        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < db.getAantalStatussen(); i++){
                    setAantalStatus(i, db.getAantalPakketMetStatus(i));
                }
            }
         
        });
        this.add(b);
        this.pack();
        this.setVisible(true);
    }

    
    private void setAantalStatus(int status, long aantal){
        statusAantal.get(status).setText(Long.toString(aantal));
    }
}
