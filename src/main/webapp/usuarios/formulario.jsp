<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de usuarios</title>
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
                        ${(usuario.id != null && usuario.id > 0) ? "Editar Usuario" : "Nuevo Usuario"}
                    </h2>
                </div>

                <div class="card-body p-4 p-md-5">
                    <form action="${pageContext.request.contextPath}/usuarios" method="POST">

                        <input type="hidden" name="accion" value="guardar">
                        <input type="hidden" name="id" value="${usuario.id}">

                        <h5 class="text-primary border-bottom pb-2 mb-4">Datos Personales</h5>

                        <div class="row g-3 mb-4">
                            <div class="col-12">
                                <label for="nombre" class="form-label fw-semibold text-secondary">Nombre:</label>
                                <input type="text" name="nombre" id="nombre" value="${usuario.nombre}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="apellidoPaterno" class="form-label fw-semibold text-secondary">Apellido Paterno:</label>
                                <input type="text" name="apellidoPaterno" id="apellidoPaterno" value="${usuario.apellidoPaterno}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="apellidoMaterno" class="form-label fw-semibold text-secondary">Apellido Materno:</label>
                                <input type="text" name="apellidoMaterno" id="apellidoMaterno" value="${usuario.apellidoMaterno}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="telefono" class="form-label fw-semibold text-secondary">Telefono:</label>
                                <input type="text" name="telefono" id="telefono" value="${usuario.telefono}" class="form-control form-control-lg">
                            </div>

                            <div class="col-md-6">
                                <label for="email" class="form-label fw-semibold text-secondary">Email:</label>
                                <input type="email" name="email" id="email" value="${usuario.email}" class="form-control form-control-lg">
                            </div>
                        </div>

                        <h5 class="text-primary border-bottom pb-2 mb-4 mt-5">Credenciales y Acceso</h5>

                        <div class="row g-3 align-items-center">
                            <div class="col-md-6">
                                <label for="username" class="form-label fw-semibold text-secondary">Usuario:</label>
                                <input type="text" name="username" id="username" value="${usuario.username}" class="form-control form-control-lg bg-light">
                            </div>

                            <div class="col-md-6">
                                <label for="password" class="form-label fw-semibold text-secondary">Contraseña:</label>
                                <input type="password" name="password" id="password" class="form-control form-control-lg" placeholder="${(usuario.id != null && usuario.id > 0) ? "Dejar vacio para conservar la contraseña" : ""}">
                            </div>

                            <div class="col-12 mt-4">
                                <label for="cargo" class="form-label fw-semibold text-secondary">Cargo / Nivel de Acceso:</label>
                                <select name="cargo" id="cargo" class="form-select form-select-lg">
                                    <option value="">--- Seleccionar ---</option>
                                    <option value="ADMINISTRADOR" ${(usuario.cargo == "ADMINISTRADOR") ? "selected" : ""}>Administrador</option>
                                    <option value="LOGISTICA" ${(usuario.cargo == "LOGISTICA") ? "selected" : ""}>Logística</option>
                                    <option value="MANTENIMIENTO" ${(usuario.cargo == "MANTENIMIENTO") ? "selected" : ""}>Mantenimiento</option>
                                </select>
                            </div>
                        </div>

                        <div class="d-grid mt-5">
                            <input type="submit" value="${(usuario.id != null && usuario.id > 0) ? "Actualizar" : "Crear"}" class="btn btn-primary btn-lg fw-bold shadow-sm">
                        </div>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>