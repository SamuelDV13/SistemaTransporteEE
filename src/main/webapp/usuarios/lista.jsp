<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Usuarios</title>
</head>
<body>

<div>
    <h2>Lista de Usuarios</h2>

    <a href="${pageContext.request.contextPath}/usuarios?accion=nuevo">Nuevo [+]</a>

    <table>
        <tr>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>Usuario</th>
            <th>Cargo</th>
            <th>Editar</th>
            <th>Eliminar</th>
        </tr>

        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.nombre}</td>
                <td>${usuario.apellidoPaterno}</td>
                <td>${usuario.apellidoMaterno}</td>
                <td>${usuario.username}</td>
                <td>${usuario.cargo}</td>
                <td><a href="${pageContext.request.contextPath}/usuarios?accion=editar&id=${usuario.id}">Editar</a></td>
                <td><a onclick="return confirm('¿Deseas eliminar este usuario?')"
                       href="${pageContext.request.contextPath}/usuarios?accion=eliminar&id=${usuario.id}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>

    </table>

</div>

</body>
</html>
