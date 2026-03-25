<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/dashboard">Inicio</a>
                </li>

                <c:if test="${sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/usuarios">Usuarios</a>
                </li>
                </c:if>

                <c:if test="${sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR' || sessionScope.usuarioLogueado.cargo == 'LOGISTICA'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/choferes">Choferes</a>
                </li>
                </c:if>

                <c:if test="${sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR' || sessionScope.usuarioLogueado.cargo == 'MANTENIMIENTO'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/camiones">Camiones</a>
                </li>
                </c:if>

                <c:if test="${sessionScope.usuarioLogueado.cargo == 'ADMINISTRADOR' || sessionScope.usuarioLogueado.cargo == 'LOGISTICA'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/viajes">Viajes</a>
                </li>
                </c:if>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Ajustes
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar Sesion</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>