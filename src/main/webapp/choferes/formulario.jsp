<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario choferes</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body class="bg-light">

<jsp:include page="/includes/header.jsp" />

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-12 col-lg-8">

            <div class="card shadow-sm border-0 rounded-4">

                <div class="card-header bg-white border-bottom-0 pt-4 pb-0 px-4 text-center">
                    <h2 class="h4 mb-0 text-dark fw-bold">
                        ${(chofer.id != null && chofer.id > 0) ? "Editar Chofer" : "Nuevo Chofer"}
                    </h2>
                </div>

                <div class="card-body p-4 p-md-5">
                    <form action="${pageContext.request.contextPath}/choferes" method="POST">

                        <input type="hidden" name="accion" value="guardar">
                        <input type="hidden" name="id" value="${chofer.id}">
                        <input type="hidden" name="licencia_id" value="${chofer.licencia.id}">

                        <h5 class="text-primary border-bottom pb-2 mb-4">Datos Personales</h5>

                        <div class="row g-3 mb-4">
                            <div class="col-12">
                                <label for="nombre" class="form-label fw-semibold text-secondary">Nombre:</label>
                                <input type="text" name="nombre" id="nombre" value="${chofer.nombre}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="apellidoPaterno" class="form-label fw-semibold text-secondary">Apellido Paterno:</label>
                                <input type="text" name="apellidoPaterno" id="apellidoPaterno" value="${chofer.apellidoPaterno}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="apellidoMaterno" class="form-label fw-semibold text-secondary">Apellido Materno:</label>
                                <input type="text" name="apellidoMaterno" id="apellidoMaterno" value="${chofer.apellidoMaterno}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="telefono" class="form-label fw-semibold text-secondary">Telefono:</label>
                                <input type="text" name="telefono" id="telefono" value="${chofer.telefono}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="comision" class="form-label fw-semibold text-secondary">Comision:</label>
                                <div class="input-group input-group-lg">
                                    <span class="input-group-text bg-light border-end-0 fw-bold">$</span>
                                    <input type="number" name="comision" id="comision" value="${chofer.comision}" class="form-control border-start-0 ps-0">
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="estado" class="form-label fw-semibold text-secondary">Estado:</label>
                                <select name="estado" id="estado" class="form-select form-select-lg">
                                    <option value="">--- Seleccionar ---</option>
                                    <option value="DISPONIBLE" ${(chofer.estado == "DISPONIBLE") ? "selected" : ""}>Disponible</option>
                                    <option value="EN_VIAJE" ${(chofer.estado == "EN_VIAJE") ? "selected" : ""}>En viaje</option>
                                    <option value="BAJA_TEMPORAL" ${(chofer.estado == "BAJA_TEMPORAL") ? "selected" : ""}>Baja temporal</option>
                                </select>
                            </div>
                        </div>

                        <h5 class="text-primary border-bottom pb-2 mb-4 mt-5">Información de Licencia</h5>

                        <div class="row g-3 align-items-center">
                            <div class="col-md-6">
                                <label for="numero_licencia" class="form-label fw-semibold text-secondary">Numero de Licencia:</label>
                                <input type="text" name="numero_licencia" id="numero_licencia" value="${chofer.licencia.numeroLicencia}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="fecha_vencimiento" class="form-label fw-semibold text-secondary">Fecha de vencimiento:</label>
                                <input type="date" name="fecha_vencimiento" id="fecha_vencimiento" value="${chofer.licencia.fechaVencimiento}" class="form-control form-control-lg">
                            </div>

                            <div class="col-12 text-end mt-3">
                                <a href="${pageContext.request.contextPath}/choferes?accion=remover&id=${chofer.id}"
                                   class="btn btn-outline-danger btn-sm fw-medium">
                                    Desvincular licencia
                                </a>
                            </div>
                        </div>

                        <div class="d-grid mt-5">
                            <input type="submit" value="${(chofer.id != null && chofer.id > 0) ? "Actualizar" : "Crear"}" class="btn btn-primary btn-lg fw-bold shadow-sm">
                        </div>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>