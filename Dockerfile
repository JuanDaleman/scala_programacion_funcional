# Imagen oficial de Almond, fijada por version y digest (linux/amd64).
FROM almondsh/almond:0.15.0@sha256:9797891e76c95f063303b57cfbc369b636a66fd0e5d6744efc9095a61397ef79

USER 1000
WORKDIR /home/jovyan

# La imagen fijada ya contiene Almond 0.15.0 con Scala 3.9.0.
# Comprobar el identificador usado por nuestros notebooks.
RUN python -c "from jupyter_client.kernelspec import KernelSpecManager; KernelSpecManager().get_kernel_spec('scala39')"

# Deja margen para Jupyter y el sistema dentro de la memoria de Binder.
ENV JAVA_TOOL_OPTIONS="-Xmx768m"

COPY --chown=1000:100 notebooks/ /home/jovyan/notebooks/
COPY --chown=1000:100 docs/ /home/jovyan/docs/
COPY --chown=1000:100 scripts/ /home/jovyan/scripts/
COPY --chown=1000:100 ejemplos/ /home/jovyan/ejemplos/
COPY --chown=1000:100 README.md /home/jovyan/README.md

# Valida y ejecuta la prueba durante la construccion. Si falla, Binder no
# publica silenciosamente una imagen con un kernel inutilizable.
# El resultado ejecutado va a /tmp; el notebook inicial queda sin salidas.
RUN python scripts/validar_notebooks.py && \
    jupyter nbconvert --to notebook --execute \
      --ExecutePreprocessor.timeout=300 \
      --ExecutePreprocessor.startup_timeout=180 \
      --ExecutePreprocessor.kernel_name=scala39 \
      --output=prueba_ejecutada --output-dir=/tmp \
      notebooks/00_prueba_binder.ipynb

# Binder proporciona su puerto y token al arrancar.
ENTRYPOINT []
CMD ["jupyter", "lab", "--ip=0.0.0.0", "--no-browser"]
