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

<body>

    <jsp:include page="/includes/header.jsp" />

    <div>
        <h2>${(chofer.id != null && chofer.id > 0) ? "Editar Chofer" : "Nuevo Chofer"}</h2>

        <form action="${pageContext.request.contextPath}/choferes" method="POST">

            <input type="hidden" name="accion" value="guardar">
            <input type="hidden" name="id" value="${chofer.id}">
            <input type="hidden" name="licencia_id" value="${chofer.licencia.id}">


            <div>
                <label for="nombre">Nombre:</label>
                <input type="text" name="nombre" id="nombre" value="${chofer.nombre}">
            </div>

            <div>
                <label for="apellidoPaterno">Apellido Paterno:</label>
                <input type="text" name="apellidoPaterno" id="apellidoPaterno" value="${chofer.apellidoPaterno}">
            </div>

            <div>
                <label for="apellidoMaterno">Apellido Materno:</label>
                <input type="text" name="apellidoMaterno" id="apellidoMaterno" value="${chofer.apellidoMaterno}">
            </div>

            <div>
                <label for="telefono">Telefono:</label>
                <input type="text" name="telefono" id="telefono" value="${chofer.telefono}">
            </div>

            <div>
                <label for="comision">Comision:</label>
                <input type="number" name="comision" id="comision" value="${chofer.comision}">
            </div>

            <div>
                <label for="estado">Estado:</label>
                <select name="estado" id="estado">
                    <option value="">--- Seleccionar ---</option>
                    <option value="DISPONIBLE" ${(chofer.estado == "DISPONIBLE") ? "selected" : ""}>Disponible</option>
                    <option value="EN_VIAJE" ${(chofer.estado == "EN_VIAJE") ? "selected" : ""}>En viaje</option>
                    <option value="BAJA_TEMPORAL" ${(chofer.estado == "BAJA_TEMPORAL") ? "selected" : ""}>Baja temporal</option>
                </select>
            </div>

            <div>
                <label for="numero_licencia">Numero de Licencia:</label>
                <input type="text" name="numero_licencia" id="numero_licencia" value="${chofer.licencia.numeroLicencia}">
            </div>

            <div>
                <label for="fecha_vencimiento">Fecha de vencimiento:</label>
                <input type="date" name="fecha_vencimiento" id="fecha_vencimiento" value="${chofer.licencia.fechaVencimiento}">
            </div>

            <div>
                <a href="${pageContext.request.contextPath}/choferes?accion=remover&id=${chofer.id}">Desvincular licencia</a>
            </div>

            <div>
                <input type="submit" value="${(chofer.id != null && chofer.id > 0) ? "Actualizar" : "Crear"}">
            </div>

        </form>

    </div>
</body>
</html>
