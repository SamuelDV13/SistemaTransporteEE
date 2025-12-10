<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de usuarios</title>
</head>

<body>
    <div>

        <h2>${(usuario.id != null && usuario.id > 0) ? "Editar Usuario" : "Nuevo Usuario"}</h2>

        <form action="${pageContext.request.contextPath}/usuarios" method="POST">

            <input type="hidden" name="accion" value="guardar">
            <input type="hidden" name="id" value="${usuario.id}">

            <div>
                <label for="nombre">Nombre:</label>
                <input type="text" name="nombre" id="nombre" value="${usuario.nombre}">
            </div>

            <div>
                <label for="apellidoPaterno">Apellido Paterno:</label>
                <input type="text" name="apellidoPaterno" id="apellidoPaterno" value="${usuario.apellidoPaterno}">
            </div>

            <div>
                <label for="apellidoMaterno">Apellido Materno:</label>
                <input type="text" name="apellidoMaterno" id="apellidoMaterno" value="${usuario.apellidoMaterno}">
            </div>

            <div>
                <label for="telefono">Telefono:</label>
                <input type="text" name="telefono" id="telefono" value="${usuario.telefono}">
            </div>

            <div>
                <label for="username">Usuario:</label>
                <input type="text" name="username" id="username" value="${usuario.username}">
            </div>

            <div>
                <label for="password">Contraseña:</label>
                <input type="password" name="password" id="password" placeholder="${(usuario.id != null && usuario.id > 0) ? "Dejar vacio para conservar la contraseña" : ""}">
            </div>

            <div>
                <label for="email">Email:</label>
                <input type="text" name="email" id="email" value="${usuario.email}">
            </div>

            <div>
                <label for="cargo">Cargo:</label>
                <select name="cargo" id="cargo">
                    <option value="">--- Seleccionar ---</option>
                    <option value="ADMINISTRADOR" ${(usuario.cargo == "ADMINISTRADOR") ? "selected" : ""}>Administrador</option>
                    <option value="LOGISTICA" ${(usuario.cargo == "LOGISTICA") ? "selected" : ""}>Logistica</option>
                    <option value="MANTENIMIENTO" ${(usuario.cargo == "MANTENIMIENTO") ? "selected" : ""}>Mantenimiento</option>
                </select>
            </div>

            <div>
                <input type="submit" value="${(usuario.id != null && usuario.id > 0) ? "Actualizar" : "Crear"}">
            </div>
        </form>

    </div>
</body>
</html>
