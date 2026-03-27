<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de Viajes</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body class="bg-light">

<jsp:include page="/includes/header.jsp" />

<div class="container mt-5 mb-5">

    <div class="row justify-content-center">
        <div class="col-12 col-xl-10">

            <div class="card shadow-sm border-0 rounded-4">

                <div class="card-header bg-white border-bottom-0 pt-4 pb-0 px-4 text-center">
                    <h2 class="h4 mb-0 text-dark fw-bold">
                        ${(viaje.id != null && viaje.id > 0) ? "Editar viaje" : "Nuevo viaje"}
                    </h2>
                </div>

                <div class="card-body p-4 p-md-5">
                    <form action="${pageContext.request.contextPath}/viajes" method="POST">

                        <input type="hidden" name="accion" value="guardar">
                        <input type="hidden" name="id" value="${viaje.id}">
                        <input type="hidden" name="idCamionAnt" value="${viaje.camion.id}">
                        <input type="hidden" name="idChoferAnt" value="${viaje.chofer.id}">

                        <h5 class="text-primary border-bottom pb-2 mb-4">Ruta y Tiempos</h5>

                        <div class="row g-3 mb-5">
                            <div class="col-md-6">
                                <label for="origen" class="form-label fw-semibold text-secondary">Origen:</label>
                                <input type="text" name="origen" id="origen" value="${viaje.origen}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="destino" class="form-label fw-semibold text-secondary">Destino:</label>
                                <input type="text" name="destino" id="destino" value="${viaje.destino}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-4">
                                <label for="fechaSalida" class="form-label fw-semibold text-secondary">Fecha de salida:</label>
                                <input type="date" name="fechaSalida" id="fechaSalida" value="${viaje.fechaSalida}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-4">
                                <label for="fechaEstimada" class="form-label fw-semibold text-secondary">Fecha estimada:</label>
                                <input type="date" name="fechaEstimada" id="fechaEstimada" value="${viaje.fechaEstimada}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-4">
                                <label for="fechaEntrega" class="form-label fw-semibold text-secondary">Fecha de entrega:</label>
                                <input type="date" name="fechaEntrega" id="fechaEntrega" readonly value="${viaje.fechaEntrega}" class="form-control form-control-lg bg-light text-muted">
                            </div>
                        </div>

                        <h5 class="text-primary border-bottom pb-2 mb-4">Asignación y Detalles</h5>

                        <div class="row g-3 align-items-center">
                            <div class="col-md-6">
                                <label for="choferId" class="form-label fw-semibold text-secondary">Chofer:</label>
                                <select name="choferId" id="choferId" class="form-select form-select-lg">
                                    <option value="">-- Seleccionar un chofer --</option>
                                    <c:forEach var="chofer" items="${listaChoferes}">
                                        <option value="${chofer.id}">${chofer.texto}</option>
                                    </c:forEach>
                                    <c:if test="${viaje != null}">
                                        <option value="${viaje.chofer.id}" selected>${viaje.chofer.nombre} ${viaje.chofer.apellidoPaterno} ${viaje.chofer.apellidoMaterno}</option>
                                    </c:if>
                                </select>
                            </div>

                            <div class="col-md-6">
                                <label for="camionId" class="form-label fw-semibold text-secondary">Camión:</label>
                                <select name="camionId" id="camionId" class="form-select form-select-lg">
                                    <option value="">-- Seleccionar un camión --</option>
                                    <c:forEach var="camion" items="${listaCamiones}">
                                        <option value="${camion.id}">${camion.texto}</option>
                                    </c:forEach>
                                    <c:if test="${viaje != null}">
                                        <option value="${viaje.camion.id}" selected>${viaje.camion.modelo} - ${viaje.camion.placa} - ${viaje.camion.capacidad}ton</option>
                                    </c:if>
                                </select>
                            </div>

                            <div class="col-md-6">
                                <label for="costo" class="form-label fw-semibold text-secondary">Costo:</label>
                                <div class="input-group input-group-lg">
                                    <span class="input-group-text bg-light border-end-0 fw-bold">$</span>
                                    <input type="text" name="costo" id="costo" value="${viaje.costo}" class="form-control border-start-0 ps-0">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <label for="estado" class="form-label fw-semibold text-secondary">Estado:</label>
                                <select name="estado" id="estado" class="form-select form-select-lg">
                                    <option value="">--- Seleccionar ---</option>
                                    <option value="EN_PREPARACION" ${(viaje.estado == "EN_PREPARACION") ? "selected" : ""}>En preparacion</option>
                                    <option value="EN_PROCESO" ${(viaje.estado == "EN_PROCESO") ? "selected" : ""}>En proceso</option>
                                    <option value="COMPLETADO" ${(viaje.estado == "COMPLETADO") ? "selected" : ""}>Completado</option>
                                    <option value="CANCELADO" ${(viaje.estado == "CANCELADO") ? "selected" : ""}>Cancelado</option>
                                </select>
                            </div>
                        </div>

                        <c:if test="${viaje == null || viaje.estado != 'COMPLETADO'}">
                            <div class="d-grid mt-5">
                                <input type="submit" value="${(viaje.id != null && viaje.id > 0) ? "Actualizar" : "Crear"}" class="btn btn-primary btn-lg fw-bold shadow-sm">
                            </div>
                        </c:if>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>