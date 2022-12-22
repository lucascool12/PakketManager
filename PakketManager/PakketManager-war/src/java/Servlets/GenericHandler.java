/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.DataBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author Lucas Van Laer
 */
@WebServlet(name = "GenericHandler", urlPatterns = {"/"})
public class GenericHandler extends HttpServlet {
    
    @EJB
    DataBeanRemote dataBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
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
        }
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
