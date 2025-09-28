<%-- 
    Document   : product
    Created on : Mar 1, 2022, 8:29:12 PM
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pe.dto.UserDTO"%>
<%@page import="pe.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management</title>
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
        <a href="MainController?action=ViewAdd">Add New Product</a>
        
        <p style="color: green;">${MESSAGE}</p>
        <p style="color: red;">${ERROR}</p>

        <h2>Search Products (Show all if no keyword)</h2>
        <form action="MainController" method="POST">
            <input type="text" name="searchValue" placeholder="Enter product description to search..." 
                   value="${param.searchValue}">
            <input type="submit" name="action" value="Search">
        </form>

            <h3>Product List - Using Scriptlet (Java Code in JSP)</h3>
            <%
                List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
                if (listProduct != null) {
            %>
                <table border="1">
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Size</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                    <%
                        int count = 1;
                        for (ProductDTO product : listProduct) {
                    %>
                        <tr>
                            <td><%= count++ %></td>
                            <td><%= product.getProductID() %></td>
                            <td><%= product.getName() %></td>
                            <td>$<%= String.format("%.2f", product.getPrice()) %></td>
                            <td><%= product.getSize() %></td>
                            <td><%= product.getDescription() %></td>
                            <td>
                                <form action="MainController" method="POST" style="display: inline;">
                                    <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                    <input type="hidden" name="searchValue" value="${param.searchValue}">
                                    <input type="text" name="name" value="<%= product.getName() %>" required>
                                    <input type="text" name="description" value="<%= product.getDescription() %>" required>
                                    <input type="number" name="price" value="<%= product.getPrice() %>" step="0.01" required>
                                    <input type="text" name="size" value="<%= product.getSize() %>" required>
                                    <input type="submit" name="action" value="Update">
                                </form>
                                <form action="MainController" method="POST" style="display: inline;">
                                    <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                    <input type="hidden" name="searchValue" value="${param.searchValue}">
                                    <input type="submit" name="action" value="Delete" onclick="return confirm('Are you sure you want to delete this product?')">
                                </form>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </table>
            <%
                }
            %>

            <h3>Product List - Using EL and JSTL</h3>
            <table border="1">
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Size</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="product" items="${LIST_PRODUCT}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${product.productID}</td>
                        <td>${product.name}</td>
                        <td>$${product.price}</td>
                        <td>${product.size}</td>
                        <td>${product.description}</td>
                        <td>
                            <form action="MainController" method="POST" style="display: inline;">
                                <input type="hidden" name="productID" value="${product.productID}">
                                <input type="hidden" name="searchValue" value="${param.searchValue}">
                                <input type="text" name="name" value="${product.name}" required>
                                <input type="text" name="description" value="${product.description}" required>
                                <input type="number" name="price" value="${product.price}" step="0.01" required>
                                <input type="text" name="size" value="${product.size}" required>
                                <input type="submit" name="action" value="Update">
                            </form>
                            <form action="MainController" method="POST" style="display: inline;">
                                <input type="hidden" name="productID" value="${product.productID}">
                                <input type="hidden" name="searchValue" value="${param.searchValue}">
                                <input type="submit" name="action" value="Delete" onclick="return confirm('Are you sure you want to delete this product?')">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
