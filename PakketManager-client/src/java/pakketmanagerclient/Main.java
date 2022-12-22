/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakketmanagerclient;

import beans.DataBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    @EJB
    static DataBeanRemote dataBean;
    public static void main(String[] args) {
        new PakketStater(dataBean);
    }
    
}
