# Tutorial Scala: prueba inicial en Binder

Esqueleto técnico para una exposición práctica de 45 minutos y cinco integrantes.
Caso de uso: catálogo de productos, disponibilidad, descuentos y búsqueda.
Este material prueba el entorno; todavía no es el tutorial final ni el taller.

## Qué abrir

- `notebooks/00_prueba_binder.ipynb`: prueba ejecutable con comprobaciones.
- `docs/PRUEBA_BINDER.md`: publicación, lanzamiento, resultados y diagnóstico.
- `docs/COORDINACION.md`: acuerdos y bloques para desarrollar el tutorial.
- `Dockerfile`: entorno remoto y ejecución de la prueba al construir la imagen.
- `scripts/validar_notebooks.py`: validación del formato del notebook.
- `ejemplos/PruebaBinder.scala`: el mismo código agrupado en un programa de respaldo.

## Entorno

Imagen oficial `almondsh/almond:0.15.0`, fijada por digest en Dockerfile.
Kernel incluido en esa imagen: Almond 0.15.0 + Scala 3.9.0, ID `scala39`.
Jupyter y Java proceden de la imagen base. No se usa Python para resolver el
ejemplo: Python solo participa en la infraestructura de Jupyter y la validación.
No se requieren Spark, GPU, claves ni paquetes adicionales de Scala.

## Publicar y abrir

1. Crear un repositorio **público** en GitHub.
2. Subir los archivos de este esqueleto manteniendo sus carpetas. El Dockerfile
   debe estar en la raíz del repositorio, no dentro de otra carpeta contenedora.
3. En https://mybinder.org elegir GitHub y pegar la URL del repositorio.
4. Indicar la rama que realmente exista (por ejemplo `main`).
5. En la ruta de apertura indicar `notebooks/00_prueba_binder.ipynb` y elegir
   el tipo **File**, si el formulario ofrece ese selector.
6. Pulsar Launch y revisar Build logs. La primera construcción puede tardar.
7. En Jupyter comprobar el kernel **Scala 3.9** y seleccionar
   **Kernel > Restart Kernel and Run All Cells** (el texto puede variar).

Resultado final esperado: `PRUEBA COMPLETA: OK`. Total: **315000 COP**.

La URL genérica para abrir JupyterLab tiene esta forma (reemplazar los campos):

```text
https://mybinder.org/v2/gh/USUARIO/REPOSITORIO/main?urlpath=lab/tree/notebooks/00_prueba_binder.ipynb
```

No es un enlace publicado todavía. Cuando el ensayo funcione, sustituir `main`
por el hash del commit probado para fijar la versión de clase.

## Estado de verificación

- La publicación en GitHub y el lanzamiento en Binder están **pendientes**.
- El Dockerfile ejecutará el notebook durante la construcción y detendrá la
  construcción si alguna celda falla; esto no equivale a una prueba ya realizada.
- Consultar `docs/PRUEBA_BINDER.md` para registrar las verificaciones reales.

## Guardar el trabajo

Binder es temporal. Descargar el `.ipynb` para conservar cambios. Guardar en
Jupyter no actualiza GitHub. Cada estudiante abre el enlace de lanzamiento para
obtener su propia sesión; no compartir la URL temporal con token de una sesión.

El PDF de lineamientos que ya estaba en la carpeta local no se necesita para
Binder y no se copia a la imagen. Revisar por separado si se quiere publicarlo.

## Fuentes técnicas

- https://github.com/almond-sh/examples
- https://almond.sh/docs/install-versions
- https://almond.sh/docs/install-advanced
- https://mybinder.readthedocs.io/en/latest/tutorials/dockerfile.html
- https://mybinder.readthedocs.io/en/latest/about/user-guidelines.html
- https://mybinder.readthedocs.io/en/latest/howto/user_interface.html
