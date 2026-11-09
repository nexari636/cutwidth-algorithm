# Algoritmo Cutwidth

## Autor
Jhon Byron Leturne Pluas

## Descripción
Este proyecto implementa el algoritmo **Cutwidth** para el cálculo y optimización del ancho de corte en teoría de grafos. El ancho de corte es una métrica fundamental para evaluar la complejidad y la capacidad de separación entre conjuntos de vértices en estructuras de grafos.

El repositorio está estructurado en tres componentes principales que permiten su ejecución tanto por consola como a través de una arquitectura web cliente-servidor:

1. **console_cutwidth:** Versión orientada a consola en Java para pruebas algorítmicas directas utilizando conjuntos de datos de grafos en formato Matrix Market (`.mtx.txt`).
2. **CutwidthServer:** Servidor backend desarrollado con Spring Boot que expone los servicios para el procesamiento de grafos.
3. **CutwidthView:** Interfaz de usuario web desarrollada con AngularJS, jQuery y GoJS para la visualización gráfica interactiva de los grafos y resultados.

## Tecnologías y Requisitos
- **Java (JDK 17 / 19)**
- **Spring Boot**
- **AngularJS y jQuery**
- **GoJS** (Visualización de grafos)
- **Maven** (Gestión de dependencias)

## Arquitectura y Diagramas

### Diagrama de Clases
<img src="https://github.com/user-attachments/assets/2ac697ee-f2c3-4866-bbb9-3ba4fdb38d9b" alt="Diagrama de clases" width="700">

### Esquema del Sistema
<img src="https://github.com/user-attachments/assets/73fddd99-94a4-4714-8567-048b78402c8c" alt="Esquema del sistema" width="700">
