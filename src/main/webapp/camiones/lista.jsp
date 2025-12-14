<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Camiones</title>
</head>

<body>
    <div>
        <h2>Lista de Camiones</h2>

        <a href="${pageContext.request.contextPath}/camiones?accion=nuevo">Nuevo [+]</a>

        <table>
            <tr>
                <th>Placa</th>
                <th>Modelo</th>
                <th>Capacidad</th>
                <th>Estado</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>

            <c:forEach var="camion" items="${camiones}">
                <tr>
                    <td>${camion.placa}</td>
                    <td>${camion.modelo}</td>
                    <td>${camion.capacidad}</td>
                    <td>${camion.estado}</td>
                    <td><a href="${pageContext.request.contextPath}/camiones?accion=editar&id=${camion.id}">Editar</a></td>
                    <td><a onclick="return confirm('¿Deseas eliminar este registro?')"
                            href="${pageContext.request.contextPath}/camiones?accion=eliminar&id=${camion.id}">Eliminar</a></td>
                </tr>
            </c:forEach>

        </table>

    </div>
</body>
</html>
