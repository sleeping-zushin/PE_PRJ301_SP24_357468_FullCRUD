<%-- 
    Document   : addProduct
    Created on : Mar 1, 2022, 8:29:12 PM
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pe.dto.UserDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Product</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <h1>Welcome ${LOGIN_USER.fullName}</h1>
        <a href="MainController?action=Logout">Logout</a>
        <a href="MainController?action=Search">Back to Product List</a>
        

        <p style="color: green;">${MESSAGE}</p>
        <p style="color: red;">${ERROR}</p>
        <h2>Add New Product</h2>
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>Product ID:</td>
                    <td><input type="text" name="productID" required></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" required></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><input type="text" name="description" required></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="number" name="price" step="0.01" required></td>
                </tr>
                <tr>
                    <td>Size:</td>
                    <td><input type="text" name="size" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="action" value="Add">
                        <input type="button" value="Cancel" onclick="window.location.href='MainController?action=Search'">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
