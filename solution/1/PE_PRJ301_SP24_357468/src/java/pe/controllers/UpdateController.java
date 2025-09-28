/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.dao.ProductDAO;
import pe.dto.ProductDTO;
import pe.dto.UserDTO;

/**
 *
 * @author hd
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String PRODUCT_PAGE = "product.jsp";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = PRODUCT_PAGE;
        try {
            // Check if user is logged in
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                url = LOGIN_PAGE;
            } else {
                String productID = request.getParameter("productID");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String priceStr = request.getParameter("price");
                String size = request.getParameter("size");
                String searchValue = request.getParameter("searchValue");
                
                if (productID != null && name != null && description != null && 
                    priceStr != null && size != null) {
                    
                    try {
                        float price = Float.parseFloat(priceStr);
                        ProductDAO dao = new ProductDAO();
                        boolean hasUpdated = dao.updateProduct(productID, name, description, price, size);
                        
                        if (hasUpdated) {
                            // Refresh the search results after successful update
                            if (searchValue != null && !searchValue.trim().isEmpty()) {
                                List<ProductDTO> listProduct = dao.searchByDescription(searchValue);
                                request.setAttribute("LIST_PRODUCT", listProduct);
                            }
                            request.setAttribute("MESSAGE", "Update successfully!");
                        } else {
                            request.setAttribute("ERROR", "Update failed!");
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("ERROR", "Invalid price format!");
                    }
                } else {
                    request.setAttribute("ERROR", "Missing required fields!");
                }
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
            request.setAttribute("ERROR", "An error occurred during update");
            url = ERROR;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
