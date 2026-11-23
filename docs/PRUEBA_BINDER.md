# Publicación y prueba del entorno

## Archivos que se deben subir

En la raíz de GitHub: `Dockerfile`, `README.md`, `.dockerignore`, `.gitignore`,
`.gitattributes` y las carpetas `notebooks`, `docs`, `scripts`, `ejemplos` con su contenido.
No subir todo dentro de una carpeta adicional ni subir únicamente el notebook.
El PDF de lineamientos es independiente de esta prueba.

Si se usa la interfaz de GitHub: Add file > Upload files, arrastrar los archivos
y carpetas y confirmar el commit. Comprobar que Dockerfile se vea en la raíz.
Los archivos que empiezan por punto también deben incluirse.

## Qué hace la construcción

1. Descarga una imagen oficial de Almond fijada por digest.
2. Comprueba el kernel incluido con ID `scala39` y Scala 3.9.0.
3. Copia el notebook y las guías al directorio de trabajo del usuario 1000.
4. Valida el formato y ejecuta todas las celdas con nbconvert.
5. Solo si todo termina correctamente queda lista la imagen para arrancar.

La comprobación de construcción también prepara parte de la caché del kernel.
No garantiza disponibilidad del servicio ni un tiempo de apertura determinado.

## Prueba desde el navegador

1. Abrir https://mybinder.org y proporcionar URL de GitHub, rama y ruta
   `notebooks/00_prueba_binder.ipynb` (tipo File).
2. Esperar la construcción; abrir Build logs si aparece un error.
3. Verificar el kernel `Scala 3.9`.
4. Reiniciar el kernel y ejecutar todas las celdas en orden.
5. Comprobar los mensajes y resultados de la tabla siguiente.
6. Cambiar `porcentajeDescuento` de 10 a 20 y ejecutar de nuevo desde esa celda.
   El total debe ser 280000. Las comprobaciones usan el porcentaje actual.
7. Restaurar 10 y repetir desde el kernel reiniciado.
8. Descargar el notebook para comprobar que se puede conservar el trabajo.
9. Abrir el enlace de lanzamiento desde otro computador o navegador: debe
   empezar con 10, aunque otra sesión haya cambiado el porcentaje.

## Resultados de referencia

| Comprobación | Resultado |
|---|---|
| Estado entre celdas | `ESTADO ENTRE CELDAS: OK` |
| Modelo | 4 productos; 3 disponibles |
| Precios base disponibles | 100000, 200000, 50000 COP |
| Precios con 10 % | 90000, 180000, 45000 COP |
| Total con 10 % | 315000 COP |
| Total con 20 % | 280000 COP |
| Original después de copy/map | Sigue con precio 100000 |
| Catálogo vacío | Total 0 |
| Búsqueda existente / ausente | Some / None |
| Última celda | `PRUEBA COMPLETA: OK` |

Los precios son BigDecimal creados a partir de textos. La regla didáctica no
incluye impuestos, cantidades, redondeo comercial ni importes inválidos.

## Registro de aceptación

- [ ] Imagen construida en Binder (guardar fecha y commit).
- [ ] Kernel correcto y todas las celdas ejecutadas desde cero.
- [ ] Variación al 20 % verificada y restaurada.
- [ ] Descarga y reapertura del notebook comprobadas.
- [ ] Probado por los cinco integrantes en sesiones independientes.
- [ ] Enlace definitivo fijado al commit probado.
- [ ] Respaldo ejecutable y copia de lectura preparados para clase.

## Problemas frecuentes

| Síntoma | Qué revisar |
|---|---|
| Repositorio no encontrado | URL, visibilidad pública y nombre de rama |
| No aparece Scala | Que se construyó el Dockerfile de la raíz; revisar logs de instalación |
| `No such kernel: scala39` | ID instalado frente a kernelspec del notebook |
| Falla la descarga de imagen o dependencias | Copiar error exacto; verificar disponibilidad y versiones antes de cambiarlas |
| Falla nbconvert durante construcción | Es una prueba fallida real: copiar la celda y el error de Build logs |
| Variable no encontrada | Reiniciar y ejecutar todas las celdas en orden |
| Notebook no encontrado | Ruta exacta y ubicación de carpetas en GitHub |
| Sesión desconectada | Puede haber caducado; abrir una nueva y volver a ejecutar |
| Cambios no aparecen en GitHub | Descargar el notebook y actualizar el repositorio por separado |

Si falla la construcción, enviar las últimas líneas de Build logs. Si falla una
celda, enviar su código y error completo. No hace falta compartir URL con token.

## Límites para planear la sesión

mybinder.org documenta sesiones temporales, cierre por inactividad, recursos
limitados (1–2 GB de RAM) y un máximo de 100 sesiones por repositorio. No es una
garantía de capacidad instantánea. Abrir antes de clase, probar desde la red
prevista y conservar un respaldo. No instalar más herramientas durante la charla.

Fuente: https://mybinder.readthedocs.io/en/latest/about/user-guidelines.html

## Verificación local opcional

Con Python y nbformat: `python scripts/validar_notebooks.py` valida formato,
pero no ejecuta Scala. Con Docker instalado puede probarse la imagen completa:

```sh
docker build -t tutorial-scala-binder .
docker run --rm -p 8888:8888 tutorial-scala-binder
```

Abrir el enlace con token que muestre esa ejecución local. Docker no es un
requisito para que el público participe desde Binder.
