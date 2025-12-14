<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de camiones</title>
</head>

<body>
    <div>

        <h2>${(camion.id != null && camion.id > 0) ? "Editar Camion" : "Nuevo Camion"}</h2>

        <form action="${pageContext.request.contextPath}/camiones" method="POST">

            <input type="hidden" name="accion" value="guardar">
            <input type="hidden" name="id" value="${camion.id}">

            <div>
                <label for="placa">Placa:</label>
                <input type="text" name="placa" id="placa" value="${camion.placa}">
            </div>

            <div>
                <label for="modelo">Modelo:</label>
                <input type="text" name="modelo" id="modelo" value="${camion.modelo}">
            </div>

            <div>
                <label for="capacidad">Capacidad:</label>
                <input type="text" name="capacidad" id="capacidad" value="${camion.capacidad}">
            </div>

            <div>
                <label for="estado">Estado:</label>
                <select name="estado" id="estado">
                    <option value="">--- Seleccionar ---</option>
                    <option value="DISPONIBLE" ${(camion.estado == "DISPONIBLE") ? "selected" : ""}>Disponible</option>
                    <option value="EN_VIAJE" ${(camion.estado == "EN_VIAJE") ? "selected" : ""}>En viaje</option>
                    <option value="MANTENIMIENTO" ${(camion.estado == "MANTENIMIENTO") ? "selected" : ""}>Mantenimiento</option>
                </select>
            </div>

            <div>
                <input type="submit" value="${(camion.id != null && camion.id > 0) ? "Actualizar" : "Crear"}">
            </div>

        </form>

    </div>
</body>
</html>
