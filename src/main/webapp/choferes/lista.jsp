<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de choferes</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body>

    <jsp:include page="/includes/header.jsp" />

    <div>

        <h2>Lista de choferes</h2>

        <a href="${pageContext.request.contextPath}/choferes?accion=nuevo">Nuevo [+]</a>

        <table>
            <tr>
                <th>Nombre</th>
                <th>Telefono</th>
                <th>Numero de licencia</th>
                <th>Estado</th>
                <th>Editar</th>
                <th>Comisiones</th>
                <th>Eliminar</th>
            </tr>

            <c:forEach var="chofer" items="${choferes}">
                <tr>
                    <td>${chofer.nombre} ${chofer.apellidoPaterno} ${chofer.apellidoMaterno}</td>
                    <td>${chofer.telefono}</td>
                    <td>${(chofer.licencia.numeroLicencia != null) ? chofer.licencia.numeroLicencia : "Sin licencia"}</td>
                    <td>${chofer.estado}</td>
                    <td><a href="${pageContext.request.contextPath}/choferes?accion=editar&id=${chofer.id}">Editar</a></td>
                    <td><a href="${pageContext.request.contextPath}/choferes?accion=mostrarViajes&id=${chofer.id}">Ver</a></td>
                    <td><a onclick="return confirm('¿Deseas eliminar este registro?')"
                            href="${pageContext.request.contextPath}/choferes?accion=eliminar&id=${chofer.id}">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>

    </div>
</body>
</html>
