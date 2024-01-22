<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Product Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }

        h2 {
            color: #333;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input, textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 12px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #0056b3;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        a {
            display: block;
            margin-top: 10px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>
<body>
    <h2>Product Form</h2>
    <form action="<c:url value='/products/new'/>" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required><br/>

        <label for="description">Description:</label>
        <textarea id="description" name="description">${product.description}</textarea><br/>

        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="${product.price}" required><br/>

        <input type="submit" value="Save">
    </form>
    <c:out value="${product.id}" />

    <br/>

    <a href="<c:url value='/products'/>">Back to Product List</a>
</body>
</html>