/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fddb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class checkDocumentServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            String receivedDocumentHash = request.getParameter("hash");
            boolean flag1=false;
            boolean flag2=false;
           
            String receivedMobNo = request.getParameter("mobno"); 
            String receivedPayload = request.getParameter("payload");
            String receivedPayloadData = request.getParameter("payloadData");
            String dataToHash=receivedPayload+GenerateMarksheetPDF.getStudentDetailsAndMarks(receivedMobNo)+receivedPayloadData;
           // out.println("dataToHash:  "+dataToHash);
            String hashOfData=Block.computeHashofData(dataToHash);
            hashOfData=hashOfData.replace('+','s');
            //out.println("Received:  "+receivedDocumentHash);
            
           //out.println("Now calculated hash:  "+hashOfData);
            
            if(receivedDocumentHash.equals(hashOfData))
                flag1=true;
            
                for(int i=0;i<RegisterMarksServlet.chain.size();i++){               
               
                String authenticateHash=RegisterMarksServlet.chain.get(i).getHash(); 
                             
                if(receivedDocumentHash.equals(authenticateHash)){
                     flag2=true;  
                     //out.println("AAauthenticateHash:  "+authenticateHash);
                }     
                                  
           }                 
                
                 //out.println("Flag1 : "+flag1);  
                 //out.println("Flag2 : "+flag2); 
                 
                 
                if(flag1&&flag2)
                {
                out.println("<br><br><br><br><center><h1>FILE IS ORIGINAL...</h1><center><br><br>");
                out.println("<img src=\"img/Correct.gif\" width=\"260\" height=\"260\">");
                out.println("<br><br><br><br><center><h1>FILE IS ORIGINAL...</h1><center><br><br>");
                out.println("<img src=\"img/Correct.gif\" width=\"260\" height=\"260\">");
                out.println("<br><br><br><br><center><h1>FILE IS ORIGINAL...</h1><center><br><br>");
                out.println("<img src=\"img/Correct.gif\" width=\"260\" height=\"260\">");
                out.println("<br><br><br><br><center><h1>FILE IS ORIGINAL...</h1><center><br><br>");
                out.println("<img src=\"img/Correct.gif\" width=\"260\" height=\"260\">");
                }
                else{
                out.println("<br><br><br><br><center><h1>FILE IS FAKE OR MANUPULATED..</h1><center><br><br>");
                out.println("<img src=\"img/Wrong.gif\" width=\"260\" height=\"260\">");
                out.println("<br><br><br><br><center><h1>FILE IS FAKE OR MANUPULATED..</h1><center><br><br>");
                out.println("<img src=\"img/Wrong.gif\" width=\"260\" height=\"260\">");
                out.println("<br><br><br><br><center><h1>FILE IS FAKE OR MANUPULATED..</h1><center><br><br>");
                out.println("<img src=\"img/Wrong.gif\" width=\"260\" height=\"260\">");
                out.println("<br><br><br><br><center><h1>FILE IS FAKE OR MANUPULATED..</h1><center><br><br>");
                out.println("<img src=\"img/Wrong.gif\" width=\"260\" height=\"260\">");

                }
                
            out.println("</head>");
            out.println("<body>");
            
            out.println("</body>");
            out.println("</html>");
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
