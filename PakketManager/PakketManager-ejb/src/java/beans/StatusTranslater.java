/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Van Laer
 */
public class StatusTranslater {
    public static final String TRANSIT = "Transit";
    public static final String PROBLEEM = "Probleem";
    public static final String GELEVERD = "Geleverd";
    public static final int TRANSIT_STAT = 0;
    public static final int PROBLEEM_STAT = 1;
    public static final int GELEVERD_STAT = 2;
    public static final int AANTAL_STATUS = 3; //begint bij 0 tot aantal - 1
    
    public static String getStatusNaam(int status) {
        switch(status){
            case StatusTranslater.GELEVERD_STAT: return StatusTranslater.GELEVERD;
            case StatusTranslater.PROBLEEM_STAT: return StatusTranslater.PROBLEEM;
            default: return StatusTranslater.TRANSIT;
        }
    }
    
    public static int getStatus(String status) {
        if(status.trim().toLowerCase().equals(StatusTranslater.GELEVERD.toLowerCase())){
            return StatusTranslater.GELEVERD_STAT;
        }else if(status.trim().toLowerCase().equals(StatusTranslater.PROBLEEM.toLowerCase())){
            return StatusTranslater.PROBLEEM_STAT;
        }else {
            return StatusTranslater.TRANSIT_STAT;
        }
    }
}
