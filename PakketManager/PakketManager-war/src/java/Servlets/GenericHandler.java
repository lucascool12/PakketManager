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

/**
 *
 * @author Lucas Van Laer
 */
//@WebServlet(name = "GenericHandler", urlPatterns = {"/"})
public class GenericHandler extends HttpServlet {
    @EJB private DataBeanRemote dbr;
    private static final int ONDERWEG = 0;
    private static final int PROBLEEM = 1;
    private static final int GELEVERD = 2;
    
    public void init() {
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessie = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        
        if (request.getParameter("hidden").equals("indexGlobalLookup")) {
            Pakketen pakket = (Pakketen) dbr.getPakket(Integer.parseInt(request.getParameter("pakketID")));
            request.setAttribute("pakketID", pakket.getPnr());
            request.setAttribute("pakketStatus", pakket.getPstatus());
            
            RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("hidden").equals("indexGlobalLoginBediende")) {
            response.sendRedirect("bediende/detailsBed.jsp");
        } else if (request.getParameter("hidden").equals("indexGlobalLoginKoerier")) {
            response.sendRedirect("koerier/detailsKoe.jsp");
        } else if (request.getParameter("hidden").equals("statusGlobalTerug")) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("hidden").equals("overzichtKoerierBekijk")) {
            Pakketen pakket = (Pakketen) dbr.getPakket(Integer.parseInt(request.getParameter("pakketID")));
            request.setAttribute("pakket", pakket);
            
            RequestDispatcher rd = request.getRequestDispatcher("koerier/detailsKoe.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("hidden").equals("detailsKoerierProbleem")) {
            Pakketen pakket = (Pakketen) dbr.getPakket(Integer.parseInt(request.getParameter("pakketID")));
            if (dbr.getPakketStatus(pakket) == PROBLEEM || dbr.getPakketStatus(pakket) == GELEVERD) {
                return;
            }
            dbr.setPakketStatus(pakket, PROBLEEM);
            request.setAttribute("pakket", pakket);
            
            RequestDispatcher rd = request.getRequestDispatcher("koerier/detailsKoe.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("hidden").equals("detailsKoerierGeleverd")) {
            Pakketen pakket = (Pakketen) dbr.getPakket(Integer.parseInt(request.getParameter("pakketID")));
            if (dbr.getPakketStatus(pakket) == ONDERWEG || dbr.getPakketStatus(pakket) == PROBLEEM) {
                return;
            }
            dbr.setPakketStatus(pakket, GELEVERD);
            request.setAttribute("pakket", pakket);
            
            RequestDispatcher rd = request.getRequestDispatcher("koerier/detailsKoe.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("hidden").equals("detailsKoerierTerug")) {
            RequestDispatcher rd = request.getRequestDispatcher("koerier/overzichtKoe.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("hidden").equals("overzichtBediendeBekijk")) {
            Pakketen pakket = (Pakketen) dbr.getPakket(Integer.parseInt(request.getParameter("pakketID")));
            Koeriers koerier = pakket.getKnr();
            request.setAttribute("pakket", pakket);
            request.setAttribute("koerier", koerier);
            
            RequestDispatcher rd = request.getRequestDispatcher("bediende/overzichtBed.jsp");
            rd.forward(request, response);
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
            
            RequestDispatcher rd = request.getRequestDispatcher("bediende/detailsBed.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("hidden").equals("detailsBediendeTerug")) {
            RequestDispatcher rd = request.getRequestDispatcher("bediende/detailsBed.jsp");
            rd.forward(request, response);
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
