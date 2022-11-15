/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas Van Laer
 */
@WebServlet(name = "GenericHandler", urlPatterns = {"/"})
public class GenericHandler extends HttpServlet {

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
