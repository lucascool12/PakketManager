/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entities.Koeriers;
import entities.Pakketen;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.DataBeanRemote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Lucas Van Laer
 */
@WebServlet(name = "GenericHandler", urlPatterns = {"*/GenericHandler"})
public class GenericHandler extends HttpServlet {
    @EJB private DataBeanRemote dbr;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final int ONDERWEG = dbr.getTransitNum();
        final int PROBLEEM = dbr.getProbleemNum();
        final int GELEVERD = dbr.getGeleverdNum();
        HttpSession sessie = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("Test") != null){
            if(request.getParameter("getPakketen") != null){
                dbr.getPakketen().forEach((p) -> {
                    dbr.printPakket(p);
                }); //System.out.println("-----" + );
            }else if(request.getParameter("getPakketen_knr_submit") != null){
                int knr = Integer.parseInt(request.getParameter("getPakketen_knr"));
                dbr.getPakketen(knr).forEach((p) -> {
                    dbr.printPakket(p);
                });
                //System.out.println("-----" + );
            }else if(request.getParameter("getPakket_submit") != null){
                int pnr = Integer.parseInt(request.getParameter("getPakket"));
                dbr.printPakket(dbr.getPakket(pnr));
                //System.out.println("-----" + );
            }else if(request.getParameter("getKoerier_submit") != null){
                int knr = Integer.parseInt(request.getParameter("getKoerier"));
                System.out.println("-----" + dbr.getKoerier(knr));
            }else if(request.getParameter("getPakketStatus_submit") != null){
                int pnr = Integer.parseInt(request.getParameter("getPakketStatus"));
                System.out.println("-----" + dbr.getPakketStatus(pnr));
            }else if(request.getParameter("addPakket_submit") != null){
                int pgewicht = Integer.parseInt(request.getParameter("addPakket_pgewicht"));
                int pstatus = Integer.parseInt(request.getParameter("addPakket_pstatus"));
                String lnaam = request.getParameter("addPakket_lnaam");
                String lstraat = request.getParameter("addPakket_lstraat");
                int lnummer = Integer.parseInt(request.getParameter("addPakket_lnummer"));
                int lpostcode = Integer.parseInt(request.getParameter("addPakket_lpostcode"));
                String lgemeente = request.getParameter("addPakket_lgemeente");
                int knr = Integer.parseInt(request.getParameter("addPakket_knr"));
                Object p = dbr.addPakket(
                        pgewicht,
                        pstatus,
                        lnaam,
                        lstraat,
                        lnummer,
                        lpostcode,
                        lgemeente,
                        "niks",
                        knr
                    );
                dbr.printPakket(p);
            }else if(request.getParameter("getMaxPnr") != null){
                System.out.println("-------" + dbr.getMaxPakketNr());
            }else if(request.getParameter("getStatussenAantal") != null){
                int status = Integer.parseInt(request.getParameter("getStatussenAantal_status"));
                System.out.println("getStatussenAantal_status");
                System.out.println("-------" + dbr.getAantalPakketMetStatus(status));
            }else if(request.getParameter("setStatus") != null){
                int pnr = Integer.parseInt(request.getParameter("setStatus_pnr"));
                int status = Integer.parseInt(request.getParameter("setStatus_status"));
                System.out.println("setStatus");
                dbr.setPakketStatus(pnr, status);
            }else if(request.getParameter("_getKoerier") != null){
                String username = request.getParameter("getKoerier_username");
                System.out.println("getKoerier");
                Koeriers k = (Koeriers) dbr.getKoerier(username);
                System.out.println(k.getKnr());
            }else if(request.getParameter("getKoeriers") != null){
                System.out.println("getKoeriers");
                dbr.getKoeriers().forEach((k) -> {
                    System.out.println(((Koeriers)k).getKnr());
                });
            }
            RequestDispatcher rd = request.getRequestDispatcher("test_databean.jsp");
            rd.forward(request, response);
            return;
        }
        
        
        
        
        
        
        
        //##### INDEX PAGE LOOK UP PACKAGE #####
        if (request.getParameter("hidden").equals("indexGlobalLookup")) {
            Pakketen pakket = (Pakketen) dbr.getPakket(Integer.parseInt(request.getParameter("pakketID")));
            request.setAttribute("pakketID", pakket.getPnr());
            request.setAttribute("pakketStatus", dbr.getStatusNaam(pakket.getPstatus()));
            
            RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
            rd.forward(request, response);
        
        //##### INDEX PAGE LOGIN BEDIENDE #####
        } else if (request.getParameter("hidden").equals("indexGlobalLoginBediende")) {
            ArrayList<Object> koeriers = dbr.getKoeriers();
            ArrayList<Object> pakketten = dbr.getPakketen();
            pakketten.forEach((p) -> {
                    dbr.printPakket(p);
                });
            sessie.setAttribute("koeriers", koeriers);
            sessie.setAttribute("bediendePakketten", pakketten);
            response.sendRedirect("bediende/overzichtBed.jsp");
        
        //##### INDEX PAGE LOGIN KOERIER #####
        } else if (request.getParameter("hidden").equals("indexGlobalLoginKoerier")) {
            response.sendRedirect("koerier/homeKoe.jsp");
        
        //##### LOOK UP PACKAGE PAGE RETURN TO INDEX PAGE #####
        } else if (request.getParameter("hidden").equals("statusGlobalTerug")) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        
        //##### KOERIER BUFFER PAGE TO OVERVIEW KOERIER PAGE #####
        } else if (request.getParameter("hidden").equals("bufferKoerierOverzicht")) {
            String naam = (String) request.getUserPrincipal().getName();
            ArrayList<Object> pakketlijst = dbr.getPakketen(dbr.getKoerier(naam));
            sessie.setAttribute("koerierPakketten", pakketlijst);
            sessie.setAttribute("curDate", new Date());

            RequestDispatcher rd = request.getRequestDispatcher("koerier/overzichtKoe.jsp");
            rd.forward(request, response);
            
        //##### GENERAL KOERIER PAGE LOOKUP PACKAGE #####
        } else if (request.getParameter("hidden").equals("overzichtKoerierBekijk")) {
            Pakketen pakket = (Pakketen) dbr.getPakket(Integer.parseInt(request.getParameter("pakketID")));
            request.setAttribute("pakket", pakket);
            
            RequestDispatcher rd = request.getRequestDispatcher("koerier/detailsKoe.jsp");
            rd.forward(request, response);
            
        //##### KOERIER LOOKUP PACKAGE PAGE LIST AS PROBLEM #####
        } else if (request.getParameter("hidden").equals("detailsKoerierProbleem")) {
            int pnr = Integer.parseInt(request.getParameter("pakketID"));
            Pakketen pakket = (Pakketen) dbr.getPakket(pnr);
            if (dbr.getPakketStatus(pakket) == GELEVERD) {
                request.setAttribute("probleem", "Niet veranderbaar");
                request.setAttribute("pakket", pakket);
                RequestDispatcher rd = request.getRequestDispatcher("koerier/detailsKoe.jsp");
                rd.forward(request, response);
                return;
            }
            dbr.setPakketStatus(pnr, PROBLEEM);
            pakket = (Pakketen) dbr.getPakket(pnr);
            request.setAttribute("pakket", pakket);
            
            RequestDispatcher rd = request.getRequestDispatcher("koerier/detailsKoe.jsp");
            rd.forward(request, response);
            
        //##### KOERIER LOOKUP PACKAGE PAGE LIST AS DELIVERED #####
        } else if (request.getParameter("hidden").equals("detailsKoerierGeleverd")) {
            int pnr = Integer.parseInt(request.getParameter("pakketID"));
            Pakketen pakket = (Pakketen) dbr.getPakket(pnr);
            if (dbr.getPakketStatus(pakket) == GELEVERD) {
                request.setAttribute("probleem", "Niet veranderbaar");
                request.setAttribute("pakket", pakket);
                RequestDispatcher rd = request.getRequestDispatcher("koerier/detailsKoe.jsp");
                rd.forward(request, response);
                return;
            }
            dbr.setPakketStatus(pnr, GELEVERD);
            pakket = (Pakketen) dbr.getPakket(pnr);
            request.setAttribute("pakket", pakket);
            
            RequestDispatcher rd = request.getRequestDispatcher("koerier/detailsKoe.jsp");
            rd.forward(request, response);
        
        //##### KOERIER LOOKUP PACKAGE PAGE RETURN TO GENERAL KOERIER PAGE #####
        } else if (request.getParameter("hidden").equals("detailsKoerierTerug")) {
            String naam = (String) request.getUserPrincipal().getName();
            ArrayList<Object> pakketlijst = dbr.getPakketen(dbr.getKoerier(naam));
            sessie.setAttribute("koerierPakketten", pakketlijst);
            RequestDispatcher rd = request.getRequestDispatcher("koerier/overzichtKoe.jsp");
            rd.forward(request, response);
        
        //##### GENERAL BEDIENDE PAGE LOOKUP PACKAGE #####
        } else if (request.getParameter("hidden").equals("overzichtBediendeBekijk")) {
            Pakketen pakket = (Pakketen) dbr.getPakket(Integer.parseInt(request.getParameter("pakketID")));
            Koeriers koerier = pakket.getKnr();
            request.setAttribute("pakket", pakket);
            request.setAttribute("koerier", koerier);
            
            RequestDispatcher rd = request.getRequestDispatcher("bediende/detailsBed.jsp");
            rd.forward(request, response);
        
        //##### GENERAL BEDIENDE PAGE ADD PACKAGE #####
        } else if (request.getParameter("hidden").equals("overzichtBediendeVoegtoe")) {
            int pgewicht = Integer.valueOf(request.getParameter("gewicht"));
            int pstatus = ONDERWEG;
            String lnaam = request.getParameter("gewicht");
            String lstraat = request.getParameter("straat");
            int lnummer = Integer.valueOf(request.getParameter("nummer"));
            int lpostcode = Integer.valueOf(request.getParameter("postcode"));
            String lgemeente = request.getParameter("gemeente");
            String pcommentaar = request.getParameter("gemeente");
            int knr = Integer.valueOf(request.getParameter("koerier"));
            Pakketen pakket = (Pakketen) dbr.addPakket(pgewicht, pstatus, lnaam, lstraat, lnummer, lpostcode, lgemeente, pcommentaar, knr);
            request.setAttribute("pakket", pakket);
            request.setAttribute("koerier", dbr.getKoerier(knr));
            
            RequestDispatcher rd = request.getRequestDispatcher("bediende/detailsBed.jsp");
            rd.forward(request, response);
        
        //##### BEDIENDE LOOKUP PACKAGE PAGE RETURN TO GENERAL BEDIENDE PAGE #####
        } else if (request.getParameter("hidden").equals("detailsBediendeTerug")) {
            ArrayList<Object> pakketten = dbr.getPakketen();
            sessie.setAttribute("bediendePakketten", pakketten);
            RequestDispatcher rd = request.getRequestDispatcher("bediende/overzichtBed.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("hidden").equals("logUit")) {
            sessie.invalidate();
            response.sendRedirect("index.jsp");
        } 
        
        /*try{
            URI uri = new URI(request.getRequestURI());
            ArrayList<String> path = new ArrayList<String>(Arrays.asList(uri.getPath().split("/")));
            path.remove(0); //empty entry because URI path startswith /
            System.out.println(path);
            //if moet van eerste entries naar laatste omdat java suckt
            if(path.get(0).equals("PakketManager-war") && path.size() == 1){
                request.setAttribute("path", path.get(0));
                RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
                rd.forward(request, response);
            }else if(path.get(1).equals("Koerier")){
                request.setAttribute("path", path.get(1));
                RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
                rd.forward(request, response);
            }else if(path.get(1).equals("Test")){
                if(request.getParameter("getPakketen") != null){
                    dataBean.getPakketen().forEach((p) -> {
                        dataBean.printPakket(p);
                    }); //System.out.println("-----" + );
                }else if(request.getParameter("getPakketen_knr_submit") != null){
                    int knr = Integer.parseInt(request.getParameter("getPakketen_knr"));
                    dataBean.getPakketen(knr).forEach((p) -> {
                        dataBean.printPakket(p);
                    });
                    //System.out.println("-----" + );
                }else if(request.getParameter("getPakket_submit") != null){
                    int pnr = Integer.parseInt(request.getParameter("getPakket"));
                    dataBean.printPakket(dataBean.getPakket(pnr));
                    //System.out.println("-----" + );
                }else if(request.getParameter("getKoerier_submit") != null){
                    int knr = Integer.parseInt(request.getParameter("getKoerier"));
                    System.out.println("-----" + dataBean.getKoerier(knr));
                }else if(request.getParameter("getPakketStatus_submit") != null){
                    int pnr = Integer.parseInt(request.getParameter("getPakketStatus"));
                    System.out.println("-----" + dataBean.getPakketStatus(pnr));
                }else if(request.getParameter("addPakket_submit") != null){
                    int pgewicht = Integer.parseInt(request.getParameter("addPakket_pgewicht"));
                    int pstatus = Integer.parseInt(request.getParameter("addPakket_pstatus"));
                    String lnaam = request.getParameter("addPakket_lnaam");
                    String lstraat = request.getParameter("addPakket_lstraat");
                    int lnummer = Integer.parseInt(request.getParameter("addPakket_lnummer"));
                    int lpostcode = Integer.parseInt(request.getParameter("addPakket_lpostcode"));
                    String lgemeente = request.getParameter("addPakket_lgemeente");
                    int knr = Integer.parseInt(request.getParameter("addPakket_knr"));
                    Object p = dataBean.addPakket(
                            pgewicht,
                            pstatus,
                            lnaam,
                            lstraat,
                            lnummer,
                            lpostcode,
                            lgemeente,
                            "niks",
                            knr
                        );
                    dataBean.printPakket(p);
                }else if(request.getParameter("getMaxPnr") != null){
                    System.out.println("-------" + dataBean.getMaxPakketNr());
                }else if(request.getParameter("getStatussenAantal") != null){
                    int status = Integer.parseInt(request.getParameter("getStatussenAantal_status"));
                    System.out.println("getStatussenAantal_status");
                    System.out.println("-------" + dataBean.getAantalPakketMetStatus(status));
                }else if(request.getParameter("setStatus") != null){
                    int pnr = Integer.parseInt(request.getParameter("setStatus_pnr"));
                    int status = Integer.parseInt(request.getParameter("setStatus_status"));
                    System.out.println("setStatus");
                    dataBean.setPakketStatus(pnr, status);
                }
                //else if(request.getParameter("setPakketStatus") != null){
                    //int knr = Integer.parseInt(request.getParameter("setPakketStatus"));
                    //System.out.println("-----" + dataBean.getKoerier(knr));
                //}
                RequestDispatcher rd = request.getRequestDispatcher("test_databean.jsp");
                rd.forward(request, response);
            }else{
                response.sendError(404);
            }
        }catch(URISyntaxException | IndexOutOfBoundsException e){
            System.out.println(e);
            throw new ServletException("Bad request");
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
