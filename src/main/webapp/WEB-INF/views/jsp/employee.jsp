<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee page</title>
</head>
<body>
<table style="align-items: center">
  <tr>
    <th>First Name</th>
    <th>Second Name</th>
    <th>Position</th>
    <th>Phone</th>
    <th>Salary</th>
  </tr>
   <tr>
      <td>${employee.firstName}</td>
      <td>${employee.secondName}</td>
      <td>${employee.position}</td>
      <td>${employee.phone}</td>
      <td>${employee.salary}</td>
    </tr>
</table>
</body>
</html>
