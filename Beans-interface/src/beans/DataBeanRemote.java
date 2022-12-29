/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author lucas
 */
@Remote
public interface DataBeanRemote {
    public ArrayList<Object> getPakketen();
    public ArrayList<Object> getPakketen(int knr);
    public Object getPakket(int pnr);
    public Object getKoerier(int knr);
    public void setPakketStatus(Object pakket, int status);
    public void setPakketStatus(int pnr, int status);
    public int getStatus(String status);
    public String getStatusNaam(int status);
    public int getPakketStatus(Object pakket);
    public int getPakketStatus(int pnr);
    public Object addPakket(
            int pgewicht,
            int pstatus,
            String lnaam,
            String lstraat,
            int lnummer,
            int lpostcode,
            String lgemeente,
            String pcommentaar,
            int knr);
    public void setPakketKoerier(int pnr, int knr);
    public void setPakketKoerier(Object pakket, Object koerier);
    public int getPrimaryKey(Object ent);
    public int getMaxPakketNr();
    public void printPakket(Object p);
    public long getAantalPakketMetStatus(int status);
    public int getAantalStatussen();
}
