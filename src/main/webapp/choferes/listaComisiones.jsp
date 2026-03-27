<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de comisiones</title>
    <jsp:include page="/includes/imports.jsp" />
</head>

<body class="bg-light">

<jsp:include page="/includes/header.jsp" />

<div class="container mt-5 mb-5">

    <div class="card shadow-sm border-0 rounded-4">

        <div class="card-header bg-white border-bottom-0 pt-4 pb-0 px-4">
            <h2 class="h4 mb-0 text-dark fw-bold">Lista de comisiones</h2>
        </div>

        <div class="card-body p-4">
            <div class="table-responsive">
                <table class="table table-striped table-hover align-middle mb-0">

                    <thead class="table-light text-secondary">
                    <tr>
                        <th scope="col">Origen</th>
                        <th scope="col">Destino</th>
                        <th scope="col">Fecha Entrega</th>
                        <th scope="col" class="text-end">Comision</th>
                    </tr>
                    </thead>

                    <tbody class="border-top-0">
                    <c:forEach var="comisiones" items="${listaComisiones}">
                        <tr>
                            <td class="fw-semibold text-dark">${comisiones.origen}</td>
                            <td>${comisiones.destino}</td>
                            <td>${comisiones.fechaEntrega}</td>
                            <td class="text-end fw-bold text-success">
                                $${comisiones.comision}
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