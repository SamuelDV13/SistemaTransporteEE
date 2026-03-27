<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm py-2">
    <div class="container">

        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/dashboard">
            LogisticaPro
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">Inicio</a>
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

            </ul>

            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle fw-semibold text-white" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        ${sessionScope.usuarioLogueado.nombre}
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end shadow border-0 mt-2">
                        <li>
                            <a class="dropdown-item text-danger fw-medium" href="${pageContext.request.contextPath}/logout">
                                Cerrar Sesion
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</nav>