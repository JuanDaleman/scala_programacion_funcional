# Plantilla de presentaciones académicas

Base reutilizable extraída de la presentación de referencia [index.html](../index.html). Copia esta carpeta completa a otro proyecto y abre [index.html](./index.html) en el navegador. No requiere instalación ni compilación. Necesita conexión a internet para Reveal.js y KaTeX.

## Análisis del diseño original

- Lienzo de 1280 × 720 (16:9), escalado por Reveal.js con margen exterior de 0.05.
- Fondo blanco y composición alineada a la izquierda; portada y cierre centrados.
- Helvetica/Arial; cuerpo de 26–28 px, títulos de contenido de 39 px, capítulos de 61 px. Estos valores corresponden al lienzo, no a los píxeles finales de pantalla.
- Azul #112c5e para títulos y estructura; naranja #dc842a para numeración, separadores y énfasis; verde #32734a para resultados. Texto #283746, texto secundario #5f6e7c, líneas #dce3e9 y superficies #f3f6f8.
- Línea naranja bajo los títulos; divisores grises entre columnas; tarjetas con borde lateral; conclusiones con borde superior naranja.
- Pie institucional de 9vh en pantalla, controles sobre el pie y contador en la esquina inferior izquierda.
- Jerarquía narrativa: portada → agenda → motivación → capítulos → explicación y evidencia → conclusiones → referencias → cierre.
- Componentes originales: agenda enlazada, dos y tres columnas, cuadrícula 2 × 2, diagramas de flujo, fórmulas, figuras con pie, tablas y bibliografía.

Se consolidaron las reglas generales de las hojas originales en una sola hoja de estilos. Las clases de portada y cierre sustituyen los estilos ligados a identificadores concretos. Se eliminaron los textos de RSA, identificadores de migración y dependencias de sus simuladores. Se conserva el logo y el pie para mantener la identidad visual: reemplázalos cuando adaptes la plantilla a otra institución.

## Archivos

- [index.html](./index.html): doce diapositivas de ejemplo y comentarios para localizar cada patrón.
- [css/styles.css](./css/styles.css): identidad visual, distribuciones y componentes.
- [js/presentation.js](./js/presentation.js): configuración de Reveal.js y KaTeX.
- [assets/](./assets/): imágenes locales de identidad; añade aquí tus ilustraciones.

La carpeta es independiente de los archivos de la presentación original.

## Adaptación

1. Copia la carpeta completa.
2. Cambia el título del documento, la descripción, el aria-label de la presentación y los textos de portada y cierre.
3. Duplica o elimina elementos section dentro de .slides. Cada sección debe tener un id único; el id del título también debe ser único y coincidir con aria-labelledby.
4. Actualiza los destinos de la agenda (por ejemplo, #/capitulo) y su numeración.
5. Cambia los colores en las variables --deck-* al inicio del CSS.
6. Sustituye el logo de portada y el pie institucional en assets. Actualiza el texto alternativo del logo. Para retirar la identidad, elimina la imagen de portada y las reglas de fondo del pie, tanto en pantalla como en impresión.
7. Sustituye el recuadro de figura por una imagen, por ejemplo: <img src="assets/mi-figura.png" alt="Descripción del contenido de la figura">.
8. Reemplaza las fórmulas, los datos y las referencias de ejemplo.

## Catálogo de componentes

| Uso | Clase |
| --- | --- |
| Portada / cierre | cover / closing |
| Índice enlazado | agenda |
| Separador de capítulo | chapter, chapter-number, chapter-subtitle |
| Etiqueta y pregunta de apertura | eyebrow, lead-question |
| Comparación en dos columnas | split |
| Síntesis en tres columnas | thirds |
| Cuadrícula de cuatro conceptos | math-grid |
| Nota destacada | callout |
| Diagrama horizontal / vertical | flow / flow vertical |
| Paso / flecha / resultado | flow-node / flow-arrow / valid |
| Fórmula / leyenda / fuente | formula / caption / source |
| Figura con atribución | source-figure |
| Código o procedimiento | pseudocode |
| Referencias en columnas | bibliography |

Conserva cover, closing y chapter al cambiar los identificadores. Para un flujo vertical añade la clase vertical a flow. Las fórmulas usan delimitadores \( ... \) en línea y \[ ... \] en bloque. El plugin matemático puede retirarse del HTML si no hay fórmulas.

## Navegación y exportación

- Flechas o espacio: avanzar; flecha izquierda: retroceder.
- Esc: vista general de diapositivas.
- F: pantalla completa cuando el navegador lo permita.
- Los enlaces de la agenda permiten saltar a cada bloque.
- Para PDF, abre index.html?print-pdf en el navegador, espera a que carguen las fórmulas e imprime como PDF en horizontal, sin encabezados ni pies del navegador y con gráficos de fondo activados. Revisa el resultado antes de compartirlo.

## Límites de composición

Mantén el contenido dentro del área útil: 48 px de margen lateral, 28 px superior y 86 px reservados abajo. Prefiere frases breves, tres a cinco puntos por diapositiva y títulos de una o dos líneas. Divide una diapositiva si el contenido invade el pie. Las imágenes deben tener texto alternativo y las fuentes deben incluir atribución verificable.

Reveal.js 5.2.1 y KaTeX 0.16.11 se cargan desde CDN con versiones fijas. Para uso sin conexión, descarga sus distribuciones y dependencias (incluidas las fuentes de KaTeX) y actualiza las rutas y la configuración del plugin.
