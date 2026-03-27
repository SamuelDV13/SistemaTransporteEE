<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Usuarios</title>
    <jsp:include page="/includes/imports.jsp"/>
</head>

<body class="bg-light">

<jsp:include page="/includes/header.jsp"/>

<div class="container mt-5 mb-5">

    <div class="card shadow-sm border-0 rounded-4">

        <div class="card-header bg-white border-bottom-0 pt-4 pb-0 px-4 d-flex justify-content-between align-items-center">
            <h2 class="h4 mb-0 text-dark fw-bold">Lista de Usuarios</h2>
            <a href="${pageContext.request.contextPath}/usuarios?accion=nuevo" class="btn btn-primary fw-semibold shadow-sm">
                Nuevo [+]
            </a>
        </div>

        <div class="card-body p-4">
            <div class="table-responsive">
                <table class="table table-striped table-hover align-middle mb-0">

                    <thead class="table-light text-secondary">
                    <tr>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido Paterno</th>
                        <th scope="col">Apellido Materno</th>
                        <th scope="col">Usuario</th>
                        <th scope="col">Cargo</th>
                        <th scope="col" class="text-center">Editar</th>
                        <th scope="col" class="text-center">Eliminar</th>
                    </tr>
                    </thead>

                    <tbody class="border-top-0">
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                            <td class="fw-semibold text-dark">${usuario.nombre}</td>
                            <td>${usuario.apellidoPaterno}</td>
                            <td>${usuario.apellidoMaterno}</td>

                            <td class="fst-italic text-muted">${usuario.username}</td>

                            <td class="fw-medium">${usuario.cargo}</td>

                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/usuarios?accion=editar&id=${usuario.id}"
                                   class="btn btn-sm btn-outline-primary">
                                    Editar
                                </a>
                            </td>
                            <td class="text-center">
                                <a onclick="return confirm('¿Deseas eliminar este usuario?')"
                                   href="${pageContext.request.contextPath}/usuarios?accion=eliminar&id=${usuario.id}"
                                   class="btn btn-sm btn-outline-danger">
                                    Eliminar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>

    </div>
</div>

</body>
</html>