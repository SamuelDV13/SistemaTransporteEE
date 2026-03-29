# 🚚 Sistema de Gestión Logística y Flotillas

Una plataforma web empresarial diseñada para la administración integral de viajes, control de flotillas (camiones) y gestión de personal (choferes y usuarios). 

Este sistema fue construido desde cero aplicando los fundamentos de **Java Enterprise Edition (Jakarta EE)**, priorizando una arquitectura limpia, la seguridad del lado del servidor y una interfaz de usuario estandarizada.

---

## ✨ Características y Módulos Principales

* **Arquitectura MVC Estricta:** Separación clara entre Vistas (JSP/JSTL), Controladores (Servlets) y Modelos/DAOs, sin depender de frameworks pesados de terceros.
* **Seguridad y Control de Accesos:** Implementación de Filtros de Servlet (`Filter`) para proteger rutas y restringir el acceso basándose en 3 niveles de roles:
  * 👑 `ADMINISTRADOR`: Acceso total, incluyendo gestión de usuarios y métricas financieras.
  * 🗺️ `LOGISTICA`: Gestión operativa de viajes y choferes.
  * 🔧 `MANTENIMIENTO`: Control exclusivo del catálogo y estado de camiones.
* **Lógica de Negocio Financiera:** Cálculo automático de comisiones para choferes y protección de datos inmutables en viajes con estado "COMPLETADO".
* **UI/UX Corporativa:** Interfaz responsiva y moderna construida con **Bootstrap 5**, utilizando un Design System consistente (tarjetas, tablas financieras alineadas a la derecha y validaciones visuales de estado).

---

## 🛠️ Stack Tecnológico

**Back-End:**
* Java 21
* Jakarta EE (Servlets, JSP, JSTL)
* Manejo seguro de credenciales mediante JNDI (Pool de Conexiones).

**Front-End:**
* HTML5 / CSS3
* Bootstrap 5

**Base de Datos:**
* Oracle DB
* JDBC con manejo manual de Transacciones (`commit` / `rollback`) para garantizar la integridad de los datos.

---

## ⚙️ Instalación y Configuración Local

Para clonar y ejecutar este proyecto en tu entorno local, sigue estos pasos:

1. **Clonar el repositorio:**
   ```bash
    git clone [https://github.com/SamuelDV13/SistemaTransporteEE.git](https://github.com/SamuelDV13/SistemaTransporteEE.git)
