Repositorio Frontend: https://github.com/Elidallanacristanchocaceres/Proyecto_RentaHerramientas_JeissonPerez_ErickArias_ElidallanaCristancho 

📚 README - Sistema de Gestión de Herramientas (Backend)

🌟 Descripción General

Este proyecto es un sistema backend para la gestión de herramientas, reservas, usuarios y facturas. Desarrollado con Spring Boot y Spring Security, ofrece autenticación JWT, roles de usuario y operaciones CRUD.

🛠️ Tecnologías Principales
Java 17 🍵

Spring Boot 🚀

Spring Security 🔐

JWT (JSON Web Tokens) 🔑

JPA/Hibernate 🗄️

Maven 📦

Base de datos relacional (configurable) 💾

📂 Estructura del Proyecto
🗃️ Paquetes Principales
````bash
com.herramienta.herramienta_app
├── application         # Lógica de aplicación
├── domain              # Entidades y lógica de negocio
├── infrastructure      # Implementaciones concretas
│   ├── controllers     # Controladores REST
│   ├── repositories    # Repositorios y servicios
│   └── security        # Configuración de seguridad
└── HerramientaAppApplication.java # Punto de entrada
````
🔐 Autenticación y Autorización
📌 Endpoints Públicos
POST /auth/login - Inicio de sesión

POST /auth/registro - Registro de nuevos usuarios

🛡️ Roles Disponibles
ADMIN 👑 - Acceso total

PROVEEDOR 🛠️ - Gestión de herramientas

CLIENTE 👤 - Realizar reservas

📋 Modelo de Datos Principal

🚀 Endpoints Principales
👥 Usuarios (/api/usuarios)
GET / - Listar todos (solo ADMIN)

GET /buscar?email={email} - Buscar por email

POST / - Crear usuario

PUT /{id} - Actualizar usuario

🛠️ Herramientas (/api/herramientas)
GET / - Listar herramientas disponibles

POST / - Crear herramienta (PROVEEDOR/ADMIN)

PUT /{id} - Actualizar herramienta

📅 Reservas (/api/reservas)
POST / - Crear reserva (CLIENTE)

GET / - Listar todas las reservas

💰 Pagos (/pagos)
POST / - Procesar pago (CLIENTE)

GET / - Listar todos los pagos

⚙️ Configuración
🔧 Variables de Entorno
````bash
properties
jwt.secret=TuClaveSecretaBase64 (mínimo 32 caracteres)
jwt.expiration=86400000 # 24 horas en ms
````
🏁 Inicialización
El sistema crea automáticamente los roles básicos al iniciar:

ADMIN 👑

PROVEEDOR 🛠️

CLIENTE 👤

🧪 Testing
Pruebas básicas de contexto incluidas

Se recomienda implementar:

Pruebas de integración para controladores

Pruebas unitarias para servicios

📌 Notas Importantes
Todas las peticiones a /api/** requieren autenticación JWT

Los tokens JWT deben incluirse en el header Authorization: Bearer <token>

La seguridad está configurada con CORS para desarrollo local

🚀 Cómo Empezar
Clona el repositorio

Configura tu base de datos en application.properties

Ejecuta mvn spring-boot:run
