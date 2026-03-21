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

<body>
    <div>

        <h1>Iniciar Sesion</h1>


        <form action="${pageContext.request.contextPath}/login" method="POST">

        <div>
            <label for="username">Usuario: </label>
            <input type="text" name="username" id="username">
        </div>

        <div>
            <label for="password">Contraseña: </label>
            <input type="password" name="password" id="password">
        </div>


        <div>
            <input type="submit" value="Ingresar">
        </div>

        <div>
            <h6>${error}</h6>
        </div>

        </form>

    </div>
</body>
</html>
