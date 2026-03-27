<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Iniciar Sesion</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body class="bg-light d-flex align-items-center justify-content-center vh-100">
<div class="card shadow-lg p-4 border-0 rounded-4" style="max-width: 400px; width: 100%;">

    <h1 class="text-center mb-4 fs-2 fw-bold text-primary">Iniciar Sesion</h1>

    <form action="${pageContext.request.contextPath}/login" method="POST">

        <div class="mb-3">
            <label for="username" class="form-label fw-semibold text-secondary">Usuario: </label>
            <input type="text" name="username" id="username" class="form-control form-control-lg">
        </div>

        <div class="mb-4">
            <label for="password" class="form-label fw-semibold text-secondary">Contraseña: </label>
            <input type="password" name="password" id="password" class="form-control form-control-lg">
        </div>

        <div class="d-grid mb-3">
            <input type="submit" value="Ingresar" class="btn btn-primary btn-lg fw-bold">
        </div>

        <div class="text-center mt-2">
            <h6 class="text-danger fw-bold m-0">${error}</h6>
        </div>

    </form>

</div>
</body>
</html>