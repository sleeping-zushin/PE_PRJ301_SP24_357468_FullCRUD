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
import pe.dto.UserDTO;
import pe.utils.DBUtils;

/**
 *
 * @author hd
 */
public class UserDAO {
    
    private static final String LOGIN = "SELECT userID, fullName, roleID, password FROM tblUsers WHERE userID = ? AND password = ?";
    
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT userID, fullName, roleID, password FROM tblUsers WHERE userID = ? AND password = ?");
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, fullName, roleID, password);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return user;
    }
}