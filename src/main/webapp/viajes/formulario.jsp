<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de Viajes</title>
</head>

<body>
    <div>

        <h2>${(viaje.id != null && viaje.id > 0) ? "Editar viaje" : "Nuevo viaje"}</h2>

        <form action="${pageContext.request.contextPath}/camiones" method="POST">

            <input type="hidden" name="accion" value="guardar">
            <input type="hidden" name="id" value="${viaje.id}">

            <div>
                <label for="origen">Origen:</label>
                <input type="text" name="origen" id="origen" value="${viaje.origen}">
            </div>

            <div>
                <label for="destino">Destino:</label>
                <input type="text" name="destino" id="destino" value="${viaje.destino}">
            </div>

            <div>
                <label for="fechaSalida">Fecha de salida:</label>
                <input type="date" name="fechaSalida" id="fechaSalida" value="${viaje.fechaSalida}">
            </div>

            <div>
                <label for="fechaEstimada">Fecha estimada:</label>
                <input type="date" name="fechaEstimada" id="fechaEstimada" value="${viaje.fechaEstimada}">
            </div>

            <div>
                <label for="fechaEntrega">Fecha de entrega:</label>
                <input type="date" name="fechaEntrega" id="fechaEntrega" readonly value="${viaje.fechaEntrega}">
            </div>

            <div>
                <label for="choferId">Chofer:</label>
                <select name="choferId" id="choferId">
                    <c:forEach var="chofer" items="${listaChoferes}">
                        <option value="${chofer.id}" ${(chofer.id == viaje.chofer.id) ? "selected" : ""}>${chofer.texto}</option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="camionId">Camion:</label>
                <select name="camionId" id="camionId">
                    <c:forEach var="camion" items="${listaCamiones}">
                        <option value="${camion.id}" ${(camion.id == viaje.camion.id) ? "selected" : ""}>${camion.texto}</option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="costo">Costo:</label>
                <input type="text" name="costo" id="costo" value="${viaje.costo}">
            </div>

            <div>
                <label for="estado">Estado:</label>
                <select name="estado" id="estado">
                    <option value="">--- Seleccionar ---</option>
                    <option value="EN_PREPARACION" ${(viaje.estado == "EN_PREPARACION") ? "selected" : ""}>En preparacion</option>
                    <option value="EN_PROCESO" ${(viaje.estado == "EN_PROCESO") ? "selected" : ""}>En proceso</option>
                    <option value="COMPLETADO" ${(viaje.estado == "COMPLETADO") ? "selected" : ""}>Completado</option>
                    <option value="CANCELADO" ${(viaje.estado == "CANCELADO") ? "selected" : ""}>Cancelado</option>
                </select>
            </div>

            <div>
                <input type="submit" value="${(viaje.id != null && viaje.id > 0) ? "Actualizar" : "Crear"}">
            </div>

        </form>

    </div>
</body>
</html>
