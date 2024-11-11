# Tarea2C4102
# Proyecto: Árbol Binario de Búsqueda (ABB) y Árbol Splay en Java

Este proyecto implementa dos tipos de estructuras de datos en Java: el Árbol Binario de Búsqueda (ABB) y el Árbol Splay. Además, incluye clases de prueba y una clase `Experiment` que ejecuta experimentos con ambas estructuras, de acuerdo con el enunciado del proyecto.

## Estructura del Proyecto

El proyecto contiene las siguientes clases:

- **Clase `Nodo`**: Representa un nodo en el Árbol Binario de Búsqueda (ABB).
- **Clase `NodoSplay`**: Representa un nodo en el Árbol Splay.
- **Clase `ABB`**: Implementa el Árbol Binario de Búsqueda (ABB) clásico, que permite insertar y buscar elementos.
- **Clase `SplayTree`**: Implementa el Árbol Splay, que también permite insertar y buscar elementos, aplicando rotaciones en cada búsqueda para mejorar el rendimiento en accesos futuros.
- **Clase `TestABB`**: Realiza pruebas unitarias sobre el Árbol Binario de Búsqueda para verificar su correcto funcionamiento.
- **Clase `TestSplay`**: Realiza pruebas unitarias sobre el Árbol Splay para asegurar su funcionamiento adecuado.
- **Clase `Experiment`**: Ejecuta los experimentos solicitados en el enunciado, comparando el rendimiento de las operaciones de búsqueda e inserción en el ABB y en el Árbol Splay para un único valor de `N`.
- **Clase `Main`**: Ejecuta los experimentos en la clase `Experiment` para distintos valores de `N`, de acuerdo con el enunciado. Los valores de `N` se establecen en `0.1 * 10^6, 0.2 * 10^6, ..., 1.0 * 10^6`.

## Ejecución del Proyecto

Para probar las estructuras de datos y correr los experimentos, ejecuta los siguientes archivos:

- `TestABB`: Prueba de inserción y búsqueda en el Árbol Binario de Búsqueda (ABB).
- `TestSplay`: Prueba de inserción y búsqueda en el Árbol Splay.
- `Experiment`: Ejecuta los 4 escenarios de comparación de rendimiento entre ambas estructuras de datos para un único valor de `N`.
- `Main`: Ejecuta la clase `Experiment` con diferentes valores de `N` para obtener los resultados de los experimentos en todos los tamaños de entrada especificados en el enunciado.

Cada archivo imprimirá resultados que reflejan el funcionamiento y la eficiencia de las operaciones implementadas (en el caso de `Experiment` y `Main`).

## Resultados

La clase `Experiment` guarda los resultados en un archivo CSV, en el cual se incluye la siguiente información:
- Escenario: Número de escenario del experimento (1 a 4).
- Tipo de árbol: Indica si es `ABB` o `SplayTree`.
- Costo promedio: Tiempo promedio de búsqueda medido en nanosegundos.
- `N`: Tamaño de la entrada.
- `M`: Número de búsquedas realizadas.

## Librerías adicionales

No se requieren librerías adicionales. El proyecto utiliza únicamente las librerías estándar de Java, por lo que no es necesario instalar dependencias externas.
