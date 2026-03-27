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

<body class="bg-light">
<jsp:include page="/includes/header.jsp" />

<div class="container mt-5">

    <div class="row mb-5">
        <div class="col-12">
            <h1 class="display-6 fw-bold text-primary">¡Bienvenido, ${sessionScope.usuarioLogueado.nombre}!</h1>
            <p class="lead text-secondary">Qué gusto verte de nuevo por aquí.</p>
        </div>
    </div>

    <div class="row mb-4">
        <div class="col-12">
            <h2 class="h4 border-bottom pb-2 text-dark">Panel de Control</h2>
        </div>
    </div>

    <div class="row g-4">

        <c:if test="${sessionScope.usuarioLogueado.cargo == 'LOGISTICA' || sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
            <div class="col-md-6 col-lg-3">
                <div class="card text-bg-primary shadow-sm h-100 border-0 rounded-4">
                    <div class="card-body text-center mt-3 mb-3">
                        <h6 class="card-title text-uppercase fw-semibold mb-3">Viajes en Ruta</h6>
                        <h2 class="display-5 fw-bold mb-0">${kpis.viajesEnRuta}</h2>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${sessionScope.usuarioLogueado.cargo == 'LOGISTICA' || sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
            <div class="col-md-6 col-lg-3">
                <div class="card text-bg-info shadow-sm h-100 border-0 rounded-4">
                    <div class="card-body text-center mt-3 mb-3 text-white">
                        <h6 class="card-title text-uppercase fw-semibold mb-3">Camiones Disponibles</h6>
                        <h2 class="display-5 fw-bold mb-0">${kpis.camionesDisponibles}</h2>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${sessionScope.usuarioLogueado.cargo == 'LOGISTICA' || sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
            <div class="col-md-6 col-lg-3">
                <div class="card text-bg-warning shadow-sm h-100 border-0 rounded-4">
                    <div class="card-body text-center mt-3 mb-3">
                        <h6 class="card-title text-uppercase fw-semibold mb-3 text-dark">Choferes Libres</h6>
                        <h2 class="display-5 fw-bold mb-0 text-dark">${kpis.choferesLibres}</h2>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
            <div class="col-md-6 col-lg-3">
                <div class="card text-bg-success shadow-sm h-100 border-0 rounded-4">
                    <div class="card-body text-center mt-3 mb-3">
                        <h6 class="card-title text-uppercase fw-semibold mb-3">Ingresos del Mes</h6>
                        <h2 class="display-5 fw-bold mb-0">$ ${kpis.gananciasMes}</h2>
                    </div>
                </div>
            </div>
        </c:if>

    </div>
</div>

</body>
</html>