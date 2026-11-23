"""Valida formato, kernel y ausencia de salidas; no ejecuta Scala.

Uso desde la raiz: python scripts/validar_notebooks.py
Dependencia: nbformat (incluida en el entorno Jupyter).
"""
from pathlib import Path
import nbformat

root = Path(__file__).resolve().parents[1]
paths = sorted((root / "notebooks").glob("*.ipynb"))
assert paths, "No se encontraron notebooks"
for path in paths:
    nb = nbformat.read(path, as_version=4)
    nbformat.validate(nb)
    assert nb.metadata.kernelspec.name == "scala39", path
    assert nb.metadata.kernelspec.language == "scala", path
    ids = [cell.id for cell in nb.cells]
    assert len(ids) == len(set(ids)), f"IDs repetidos: {path}"
    for cell in nb.cells:
        if cell.cell_type == "code":
            assert cell.execution_count is None, f"Limpiar ejecucion: {path}"
            assert not cell.outputs, f"Limpiar salidas: {path}"
    print(f"OK: {path.name} ({len(nb.cells)} celdas)")
print("Formato validado. Esto NO confirma la ejecucion de Scala ni de Binder.")
