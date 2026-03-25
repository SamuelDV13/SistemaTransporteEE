<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Index</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body>
    <jsp:include page="/includes/header.jsp" />

    <div>
        <h1>Bienvenido ${sessionScope.usuarioLogueado.nombre} !!Que gusto verte de nuevo!!</h1>
        <h2>Panel de Control</h2>


        <c:if test="${sessionScope.usuarioLogueado.cargo == 'LOGISTICA' || sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
            <div>
                <h3>Viajes en Ruta: ${kpis.viajesEnRuta}</h3>
            </div>
        </c:if>

        <c:if test="${sessionScope.usuarioLogueado.cargo == 'LOGISTICA' || sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
        <div>
            <h3>Camiones Disponibles: ${kpis.camionesDisponibles}</h3>
        </div>
        </c:if>

        <c:if test="${sessionScope.usuarioLogueado.cargo == 'LOGISTICA' || sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
        <div>
            <h3>Choferes Libres: ${kpis.choferesLibres}</h3>
        </div>
        </c:if>

        <c:if test="${sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
        <div>
            <h3>Ingresos del Mes: ${kpis.gananciasMes}</h3>
        </div>
        </c:if>

    </div>

</body>
</html>
