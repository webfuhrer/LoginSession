/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetelogin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mañanas
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

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
       String accion=request.getParameter("accion");
       if (accion.equals("login"))
       {
           //acciones de login
           String usuario=request.getParameter("usuario");
           String password=request.getParameter("password");
           boolean usuario_ok=AccesoBD.verificarUsuario(usuario, password);
           if (usuario_ok)
           {
               HttpSession session=request.getSession();
               session.setAttribute("usuario", usuario);
               request.getRequestDispatcher("paginaprivada.jsp").forward(request, response);
           }else
               
           {
              
                       
               request.setAttribute("respuesta",3);
               request.getRequestDispatcher("index.jsp").forward(request, response);
           }
           
       }else if (accion.equals("registro"))
       {
           //acciones de registro
           String nombre=request.getParameter("nombre");
           String usuario=request.getParameter("usuario");
           String password=request.getParameter("password");
           int respuesta=AccesoBD.insertarUsuario(nombre, usuario, password);
           //0-Todo bien; 1-Usuario repe; 2-Fallo conexion o algo así
           request.setAttribute("respuesta", respuesta);
           request.getRequestDispatcher("index.jsp").forward(request, response);
       }
       else
       {
           request.getRequestDispatcher("index.jsp").forward(request, response);
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
