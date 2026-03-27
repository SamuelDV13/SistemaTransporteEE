<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de camiones</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body class="bg-light">

<jsp:include page="/includes/header.jsp" />

<div class="container mt-5 mb-5">

    <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">

            <div class="card shadow-sm border-0 rounded-4">

                <div class="card-header bg-white border-bottom-0 pt-4 pb-0 px-4">
                    <h2 class="h4 mb-0 text-dark fw-bold text-center">
                        ${(camion.id != null && camion.id > 0) ? "Editar Camion" : "Nuevo Camion"}
                    </h2>
                </div>

                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/camiones" method="POST">

                        <input type="hidden" name="accion" value="guardar">
                        <input type="hidden" name="id" value="${camion.id}">

                        <div class="mb-3">
                            <label for="placa" class="form-label fw-semibold text-secondary">Placa:</label>
                            <input type="text" name="placa" id="placa" value="${camion.placa}" class="form-control form-control-lg">
                        </div>

                        <div class="mb-3">
                            <label for="modelo" class="form-label fw-semibold text-secondary">Modelo:</label>
                            <input type="text" name="modelo" id="modelo" value="${camion.modelo}" class="form-control form-control-lg">
                        </div>

                        <div class="mb-3">
                            <label for="capacidad" class="form-label fw-semibold text-secondary">Capacidad:</label>
                            <input type="text" name="capacidad" id="capacidad" value="${camion.capacidad}" class="form-control form-control-lg">
                        </div>

                        <div class="mb-4">
                            <label for="estado" class="form-label fw-semibold text-secondary">Estado:</label>
                            <select name="estado" id="estado" class="form-select form-select-lg">
                                <option value="">--- Seleccionar ---</option>
                                <option value="DISPONIBLE" ${(camion.estado == "DISPONIBLE") ? "selected" : ""}>Disponible</option>
                                <option value="EN_VIAJE" ${(camion.estado == "EN_VIAJE") ? "selected" : ""}>En viaje</option>
                                <option value="MANTENIMIENTO" ${(camion.estado == "MANTENIMIENTO") ? "selected" : ""}>Mantenimiento</option>
                            </select>
                        </div>

                        <div class="d-grid mt-4">
                            <input type="submit" value="${(camion.id != null && camion.id > 0) ? "Actualizar" : "Crear"}" class="btn btn-primary btn-lg fw-bold shadow-sm">
                        </div>

                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>