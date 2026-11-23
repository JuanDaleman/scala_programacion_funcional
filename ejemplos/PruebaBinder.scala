//> using scala "3.9.0"
// Respaldo del notebook. Ejecutar: scala run ejemplos/PruebaBinder.scala
// En Scastie, desactivar Worksheet para ejecutar este @main.

@main def pruebaBinder(): Unit =
  val mensaje = "Scala funciona"
  val numeroDePrueba = 21

  def duplicar(n: Int): Int =
    n * 2

  println(mensaje)

  assert(duplicar(numeroDePrueba) == 42)
  println("ESTADO ENTRE CELDAS: OK")

  val porcentajeDescuento = 10

  def aplicarDescuento(precio: BigDecimal, porcentaje: Int): BigDecimal =
    precio * (BigDecimal(100) - BigDecimal(porcentaje)) / BigDecimal(100)

  assert(porcentajeDescuento >= 0 && porcentajeDescuento <= 100)
  assert(aplicarDescuento(BigDecimal("100000"), 10) == BigDecimal("90000"))
  assert(aplicarDescuento(BigDecimal("100000"), 0) == BigDecimal("100000"))
  assert(aplicarDescuento(BigDecimal("100000"), 100) == BigDecimal(0))
  println("FUNCION DE DESCUENTO: OK")

  case class Producto(id: Int, nombre: String, precio: BigDecimal, disponible: Boolean)

  val productos = List(
    Producto(1, "Teclado", BigDecimal("100000"), true),
    Producto(2, "Audifonos", BigDecimal("200000"), true),
    Producto(3, "Mouse", BigDecimal("80000"), false),
    Producto(4, "Soporte", BigDecimal("50000"), true)
  )

  assert(productos.size == 4)
  productos.foreach(println)

  val tecladoOriginal = productos.head
  val tecladoEnOferta = tecladoOriginal.copy(
    precio = aplicarDescuento(tecladoOriginal.precio, porcentajeDescuento)
  )

  assert(tecladoOriginal.precio == BigDecimal("100000"))
  assert(tecladoEnOferta.precio == aplicarDescuento(BigDecimal("100000"), porcentajeDescuento))
  println(s"Original: ${tecladoOriginal.precio}; copia: ${tecladoEnOferta.precio}")

  val disponibles = productos.filter(producto => producto.disponible)
  assert(disponibles.map(producto => producto.id) == List(1, 2, 4))
  println(s"Disponibles: ${disponibles.map(producto => producto.nombre)}")

  val preciosConDescuento = disponibles.map(
    producto => aplicarDescuento(producto.precio, porcentajeDescuento)
  )
  val factorEsperado = (BigDecimal(100) - BigDecimal(porcentajeDescuento)) / BigDecimal(100)
  val preciosEsperados = List("100000", "200000", "50000").map(
    texto => BigDecimal(texto) * factorEsperado
  )
  assert(preciosConDescuento == preciosEsperados)
  println(s"Precios con descuento: $preciosConDescuento")

  val total = preciosConDescuento.foldLeft(BigDecimal(0))(
    (acumulado, precio) => acumulado + precio
  )
  assert(total == BigDecimal("350000") * factorEsperado)
  assert(productos.head.precio == BigDecimal("100000"))
  println(s"Total con $porcentajeDescuento % de descuento: $total COP")

  val catalogoVacio = List.empty[Producto]
  val totalVacio = catalogoVacio
    .filter(producto => producto.disponible)
    .map(producto => aplicarDescuento(producto.precio, porcentajeDescuento))
    .foldLeft(BigDecimal(0))((acumulado, precio) => acumulado + precio)

  assert(totalVacio == BigDecimal(0))
  println("CATALOGO VACIO: OK (total 0)")

  def buscarProducto(id: Int): Option[Producto] =
    productos.find(producto => producto.id == id)

  def describirResultado(resultado: Option[Producto]): String =
    resultado match
      case Some(producto) => s"Encontrado: ${producto.nombre}"
      case None => "Producto no encontrado"

  assert(buscarProducto(1).contains(tecladoOriginal))
  assert(buscarProducto(999).isEmpty)
  assert(describirResultado(buscarProducto(999)) == "Producto no encontrado")
  println(describirResultado(buscarProducto(1)))
  println(describirResultado(buscarProducto(999)))

  println("PRUEBA COMPLETA: OK")
