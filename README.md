Sistema de Control Inventarial

Este proyecto es una aplicación de escritorio desarrollada en Java Swing.
Su propósito es permitir el control básico de artículos dentro de un inventario.

El sistema permite registrar, editar, eliminar, buscar, filtrar, ordenar y exportar artículos. También muestra estadísticas generales del inventario.

Funciones principales

Registrar artículos.
Editar artículos existentes.
Eliminar artículos.
Buscar por nombre o código.
Filtrar por categoría.
Ordenar por nombre, precio o cantidad.
Ver estadísticas del inventario.
Exportar la información a un archivo.
Validar datos ingresados.
Evitar artículos duplicados.
Tecnologías utilizadas
Java
Java Swing
Apache NetBeans
Programación Orientada a Objetos
Colecciones en Java
Manejo de excepciones



Estructura del proyecto


src/
├── modelo/
│   └── Articulo.java
├── negocio/
│   └── ArticuloNegocio.java
├── repositorio/
│   └── ArticuloRepositorio.java
├── presentacion/
│   ├── MainFrame.java
│   ├── ArticuloPanel.java
│   ├── ListaArticuloPanel.java
│   └── EstadisticaPanel.java
├── excepciones/
│   ├── DatoInvalidoException.java
│   └── ArticuloDuplicadoException.java
└── util/
    └── ArchivoUtil.java

    
Organización del sistema

El proyecto está dividido por capas:

Modelo: contiene la clase Articulo.
Negocio: contiene las validaciones y reglas del sistema.
Repositorio: guarda y administra los artículos.
Presentación: contiene las ventanas y paneles gráficos.
Excepciones: maneja errores personalizados.
Util: contiene funciones auxiliares, como exportar datos.
