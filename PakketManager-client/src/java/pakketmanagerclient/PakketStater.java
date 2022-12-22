/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakketmanagerclient;
import beans.DataBeanRemote;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


/**
 *
 * @author lucas
 */
public class PakketStater extends JFrame implements PropertyChangeListener{
    private final DataBeanRemote db;
    public ArrayList<JLabel> statusAantal;
    public PakketStater(final DataBeanRemote db){
        super();
        this.db = db;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(db.getAantalStatussen(),2));
        statusAantal = new ArrayList<>();
        for(int i = 0; i < db.getAantalStatussen(); i++){
            this.add(new JLabel(db.getStatusNaam(i)), i);
            JLabel statussen = new JLabel(Integer.toString(db.getAantalPakketMetStatus(i)));
            statusAantal.add(statussen);
            this.add(statussen, db.getAantalStatussen() + i);
        }
        this.pack();
        this.setVisible(true);
        db.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("pstatus") || evt.getPropertyName().equals("pakket")){
            for(int i = 0; i < statusAantal.size(); i++){
                this.setAantalStatus(i, db.getAantalPakketMetStatus(i));
            }
        }
    }
    
    private void setAantalStatus(int status, int aantal){
        statusAantal.get(status).setText(Integer.toString(aantal));
    }
}
