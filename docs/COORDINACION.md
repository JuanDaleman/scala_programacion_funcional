# Acuerdos provisionales para el tutorial

El notebook inicial es una prueba técnica corta, no la presentación terminada.
Su código resuelto es visible: no usarlo como examen ni como reto oculto.

## Caso compartido

Catálogo de una tienda. Seleccionar productos disponibles, aplicar un descuento
y calcular el costo de una unidad de cada producto seleccionado. Buscar por ID.
Es diferente del reporte de pedidos del grupo teórico.

Contrato inicial (puede ajustarse antes de desarrollar las secciones):

- `Producto(id: Int, nombre: String, precio: BigDecimal, disponible: Boolean)`.
- `productos: List[Producto]`.
- `aplicarDescuento(precio: BigDecimal, porcentaje: Int): BigDecimal`.
- Porcentaje entre 0 y 100; precios válidos no negativos, expresados en COP.
- No incluir inventario por cantidades, entrada de archivos, impuestos ni
  redondeo comercial en la prueba mínima.

| Bloque | Recibe | Prepara | Entrega |
|---|---|---|---|
| 1. Entorno | Configuración común | Primera ejecución e instrucciones | Sesión lista |
| 2. Funciones | Tipos y regla de descuento acordados | val, tipos, expresiones, def | aplicarDescuento |
| 3. Datos | Modelo acordado | case class, List, copy | productos |
| 4. Colecciones | productos y aplicarDescuento | Lambdas, filter, map, foldLeft | Total y resultados intermedios |
| 5. Ausencia | Producto y productos | find, Option, pattern matching | Consulta y cierre |

Todos pueden trabajar con esta misma base sin esperar el notebook terminado de
otra persona. Antes de clase se integran las secciones y se prueba el recorrido.
Para evitar conflictos, cada integrante puede preparar un archivo separado y
una persona integra el notebook principal; no asumir que dos notebooks comparten
variables. El principal tendrá los cinco bloques en un mismo kernel.

## Material por integrante

Objetivo, explicación breve en Markdown, código inicial y terminado, resultados,
una modificación para practicar, fuentes y notas de exposición. Las diapositivas
de apoyo se preparan después de validar el código. El taller final y el tutorial
publicable se desarrollarán después de aprobar el entorno.

## Tiempo de referencia: 45 minutos

Entorno 4, funciones 7, datos 6, colecciones 10, ausencia 8, retos 7 y cierre 3.
Los 7 minutos de retos pueden distribuirse; no sumarlos otra vez a cada bloque.
La instalación se prepara antes de la clase.

## Próximos pasos

1. Publicar este esqueleto y completar el registro de PRUEBA_BINDER.md.
2. Confirmar modelo, tipos y nombres entre los cinco.
3. Desarrollar contenido por secciones.
4. Integrar, reiniciar y ejecutar todo; luego ensayar con cronómetro.
5. Publicar la versión de estudiantes; entregar soluciones después de los retos.
