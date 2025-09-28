/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.dto.ProductDTO;
import pe.utils.DBUtils;

/**
 *
 * @author hd
 */
public class ProductDAO {
    
    private static final String GET_ALL_PRODUCTS = "SELECT id, name, description, price, size, status FROM tblProduct";
    private static final String SEARCH_BY_DESCRIPTION = "SELECT id, name, description, price, size, status FROM tblProduct WHERE description LIKE ?";
    private static final String UPDATE_PRODUCT = "UPDATE tblProduct SET name = ?, description = ?, price = ?, size = ? WHERE id = ?";
    private static final String ADD_PRODUCT = "INSERT INTO tblProduct (id, name, description, price, size, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_PRODUCT = "DELETE FROM tblProduct WHERE id = ?";
    
    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCTS);
                rs = ptm.executeQuery();
                
                while (rs.next()) {
                    String productID = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String size = rs.getString("size");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, name, description, price, size, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
    
    public List<ProductDTO> searchByDescription(String searchValue) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_DESCRIPTION);
                ptm.setString(1, "%" + searchValue + "%");
                rs = ptm.executeQuery();
                
                while (rs.next()) {
                    String productID = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String size = rs.getString("size");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, name, description, price, size, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
    
    public boolean updateProduct(String productID, String name, String description, float price, String size) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setString(1, name);
                ptm.setString(2, description);
                ptm.setFloat(3, price);
                ptm.setString(4, size);
                ptm.setString(5, productID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
    
    public boolean addProduct(String productID, String name, String description, float price, String size) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT);
                ptm.setString(1, productID);
                ptm.setString(2, name);
                ptm.setString(3, description);
                ptm.setFloat(4, price);
                ptm.setString(5, size);
                ptm.setBoolean(6, true); // Default status is true
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
    
    public boolean deleteProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT);
                ptm.setString(1, productID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
}