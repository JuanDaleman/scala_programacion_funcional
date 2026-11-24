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

  def cotizarImperativo(catalogo: List[Producto], descuento: Int)
      : (List[(String, BigDecimal)], BigDecimal) =
    var totalImperativo = BigDecimal(0)
    val lineasImperativas = scala.collection.mutable.ListBuffer.empty[(String, BigDecimal)]

    for (producto <- catalogo) {
      if (producto.disponible) {
        val precioFinal = aplicarDescuento(producto.precio, descuento)
        lineasImperativas += ((producto.nombre, precioFinal))
        totalImperativo += precioFinal
      }
    }

    (lineasImperativas.toList, totalImperativo)

  val (detalleImperativo, totalImperativo) =
    cotizarImperativo(productos, porcentajeDescuento)

  detalleImperativo.foreach(linea => println(s"${linea._1}: ${linea._2} COP"))
  println(s"Total imperativo: $totalImperativo COP")

  val disponibles = productos.filter(producto => producto.disponible)
  assert(disponibles.map(producto => producto.id) == List(1, 2, 4))
  println(s"Disponibles: ${disponibles.map(producto => producto.nombre)}")

  type LineaCotizacion = (String, BigDecimal)

  def crearLinea(descuento: Int)(producto: Producto): LineaCotizacion =
    (producto.nombre, aplicarDescuento(producto.precio, descuento))

  def crearLineas(descuento: Int)(seleccion: List[Producto]): List[LineaCotizacion] =
    seleccion.map(crearLinea(descuento))

  val cotizarCampana: List[Producto] => List[LineaCotizacion] =
    crearLineas(porcentajeDescuento)

  val lineas = cotizarCampana(disponibles)
  lineas.foreach(linea => println(s"${linea._1}: ${linea._2} COP"))

  def sumarLineas(detalle: List[LineaCotizacion]): BigDecimal =
    detalle.foldLeft(BigDecimal(0)) { (acumulado, linea) =>
      acumulado + linea._2
    }

  val total = sumarLineas(lineas)
  val factorEsperado = (BigDecimal(100) - BigDecimal(porcentajeDescuento)) / BigDecimal(100)
  assert(total == BigDecimal("350000") * factorEsperado)
  assert(productos.head.precio == BigDecimal("100000"))
  println(s"Total con $porcentajeDescuento % de descuento: $total COP")

  assert(detalleImperativo == lineas)
  assert(totalImperativo == total)
  assert(cotizarImperativo(List.empty[Producto], porcentajeDescuento) == (Nil, BigDecimal(0)))
  println(s"Bucle y cadena funcional coinciden en detalle y total ($totalImperativo COP).")

  def totalDesdeVista(catalogo: List[Producto], descuento: Int): BigDecimal =
    catalogo.view
      .filter(producto => producto.disponible)
      .map(crearLinea(descuento))
      .foldLeft(BigDecimal(0)) { (acumulado, linea) =>
        acumulado + linea._2
      }

  val totalConVista = totalDesdeVista(productos, porcentajeDescuento)
  assert(totalConVista == total)
  assert(totalDesdeVista(List.empty[Producto], porcentajeDescuento) == BigDecimal(0))
  println(s"Lista estricta y vista producen el mismo total ($totalConVista COP).")

  val catalogoVacio = List.empty[Producto]
  val totalVacio = sumarLineas(cotizarCampana(catalogoVacio))
  assert(totalVacio == BigDecimal(0))
  println("CATALOGO VACIO: OK (total 0)")

  def seleccionarConTope(detalle: List[LineaCotizacion], tope: BigDecimal)
      : List[LineaCotizacion] =
    detalle // TODO: devuelve solo las lineas cuyo precio final cumple el tope.

  val lineasReto = crearLineas(10)(disponibles)
  val casosTope = List(
    (BigDecimal("90000"), List("Teclado", "Soporte"), BigDecimal("135000")),
    (BigDecimal("89999"), List("Soporte"), BigDecimal("45000")),
    (BigDecimal("40000"), List.empty[String], BigDecimal(0))
  )

  casosTope.foreach { caso =>
    val (tope, nombresEsperados, totalEsperado) = caso
    val seleccion = seleccionarConTope(lineasReto, tope)
    val nombres = seleccion.map(linea => linea._1)
    val importe = sumarLineas(seleccion)
    val pasa = nombres == nombresEsperados && importe == totalEsperado
    val estado = if pasa then "OK" else "Por revisar"
    println(s"$estado | Tope: $tope COP | Productos: $nombres | Total: $importe COP")
  }

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
