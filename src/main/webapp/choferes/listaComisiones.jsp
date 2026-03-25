<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de comisiones</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body>

    <jsp:include page="/includes/header.jsp" />

    <div>

        <h1>Lista de comisiones</h1>

        <table>
            <tr>
                <th>Origen</th>
                <th>Destino</th>
                <th>Fecha Entrega</th>
                <th>Comision</th>
            </tr>

            <c:forEach var="comisiones" items="${listaComisiones}">
            <tr>
                <td>${comisiones.origen}</td>
                <td>${comisiones.destino}</td>
                <td>${comisiones.fechaEntrega}</td>
                <td>$${comisiones.comision}</td>
            </tr>
            </c:forEach>
        </table>

    </div>

</body>
</html>
