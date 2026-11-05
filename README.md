# Taller_2_venta_boletas

Contexto 6: Venta de boletas para eventos

Equipo:
- Andres Jaimes: AJ-34-SO
- Simon Ramirez: limositri8-creator
- Martin Jaramillo: martinjaramillosalinas-eng
- Santiago Lara: SantiLara08


Tabla de Trazabilidad:

1a. Abstraccion

  Archivos: ParteEscenario.java, Pago.java, Registro.java, Contenedor.java, Evento.java

  Clases: ParteEscenario, Pago, Registro, Contenedor, Evento


  1b. Encapsulamiento

  Archivos: todos los archivos de clases

  Clases: todas las clases. Por ejemplo, Silla usa vender y liberar en vez de setters


  1c. Herencia

  Archivos: Localidad.java, Zona.java, Escenario.java, Concierto.java, Partido.java, ObraTeatro.java

  Clases: Localidad, Zona, Escenario, Concierto, Partido, ObraTeatro


  1d. Polimorfismo

  Archivos: Evento.java, Concierto.java, Partido.java, ObraTeatro.java, Silla.java, Contenedor.java

  Clases: Evento y sus hijas (metodo tipo), Silla y Contenedor (metodo capacidad)


  2a. Uso de try/catch/finally

  Archivos: Main.java

  Clases: Main


  2b. Excepciones personalizadas

  Archivos: SillaOcupadaException.java, LimiteBoletasException.java, CancelacionFueraDeTiempoException.java

  Clases: SillaOcupadaException, LimiteBoletasException, CancelacionFueraDeTiempoException


  2c. Excepcion base del dominio

  Archivos: BoletasException.java

  Clases: BoletasException


  3a. Comparable

  Archivos: Evento.java

  Clases: Evento


  3b. Criterios con Comparator

  Archivos: ComparadorPorPrecio.java, Main.java, ReporteServicio.java

  Clases: ComparadorPorPrecio (como clase), Main (lambda), ReporteServicio (compuesto, metodo eventosPorTipoYNombre)


  3c. Uso de List.sort

  Archivos: Main.java

  Clases: Main


  3d. Algoritmo de ordenamiento a mano

  Archivos: Utilidades.java, UtilidadesTest.java

  Clases: Utilidades (metodo ordenar), UtilidadesTest


  4a. Interfaz generica

  Archivos: Registro.java

  Clases: Registro


  4b. Clases genericas

  Archivos: RegistroLista.java, Contenedor.java

  Clases: RegistroLista, Contenedor


  4c. Metodos genericos estaticos

  Archivos: Utilidades.java

  Clases: Utilidades (metodos ordenar, agregarTodos y recorrer)


  5a. Metodo que solo lee o cuenta

  Archivos: Utilidades.java

  Clases: Utilidades (metodo contar)


  5b. Metodo productor

  Archivos: Utilidades.java

  Clases: Utilidades (metodo sumarIngresos)


  5c. Metodo consumidor

  Archivos: Utilidades.java

  Clases: Utilidades (metodo agregarTodos)


  6a. SOLID

  Archivos: README.md

  Clases: ver la seccion SOLID


  7a. Interfaces funcionales

  Archivos: Registro.java, RegistroLista.java, ReporteServicio.java, Utilidades.java, Main.java

  Clases: Registro y RegistroLista (Predicate), ReporteServicio (Function), Utilidades (Consumer), Main (Supplier)


  7b. Interfaz funcional propia

  Archivos: Pago.java

  Clases: Pago


  7c. Operaciones con Streams

  Archivos: VentaServicio.java, ReporteServicio.java

  Clases: VentaServicio (filter), ReporteServicio (filter, map, sorted, reduce y average)


  7d. Optional en busquedas

  Archivos: RegistroLista.java, ReporteServicio.java

  Clases: RegistroLista (metodo buscar), ReporteServicio (metodo buscarEvento)


  8a. Patrones de diseño

  Archivos: ParteEscenario.java, Silla.java, Contenedor.java, Localidad.java, Zona.java, Escenario.java, ReporteServicio.java, VentaServicio.java,
  Pago.java, AdaptadorA.java, AdaptadorB.java, ProveedorA.java, ProveedorB.java

  Clases: Composite con ParteEscenario, Silla, Contenedor, Localidad, Zona, Escenario y ReporteServicio. Adapter con VentaServicio, Pago, AdaptadorA,
  AdaptadorB, ProveedorA y ProveedorB


  8b. Diagrama UML

  Archivos: UMLtaller2.drawio.png

  Clases: las de los dos patrones


  9a. JUnit 5

  Archivos: pom.xml

  Clases: no aplica, es una dependencia


  9b. Anotaciones y asserts

  Archivos: ContenedorTest.java, VentaServicioTest.java, ReporteServicioTest.java

  Clases: ContenedorTest, ReporteServicioTest, VentaServicioTest (tambien assertThrows)


  9c. Que se prueba

  Archivos: VentaServicioTest.java, ReporteServicioTest.java, UtilidadesTest.java, RegistroListaTest.java, ContenedorTest.java, SillaTest.java,
  AdaptadorTest.java

  Clases: VentaServicioTest y ReporteServicioTest (servicios), UtilidadesTest (ordenamiento), RegistroListaTest y ContenedorTest (clases genericas),
  ContenedorTest y SillaTest (Composite), AdaptadorTest (Adapter)


  10a. Logger por servicio

  Archivos: VentaServicio.java, ReporteServicio.java

  Clases: VentaServicio, ReporteServicio


  10b. INFO, WARNING y ERROR

  Archivos: VentaServicio.java

  Clases: VentaServicio (ERROR es el nivel severe)


  11. println solo para el menu

  Archivos: Main.java

  Clases: Main


Justificacion SOLID:

S: Responsabilidad unica

Cada clase tiene una sola razon para cambiar. VentaServicio solo vende y cancela boletas, y ReporteServicio solo hace consultas. Si el limite de boletas
pasa de 4 a 6, solo se cambia VentaServicio. Si piden un reporte nuevo, solo se cambia ReporteServicio. Si todo estuviera en una sola clase, cada cambio
podria dañar algo que ya funcionaba.


O: Abierto/cerrado

Se puede agregar algo nuevo sin cambiar lo que ya existe. Si la empresa contrata un tercer proveedor de pago, se crea un AdaptadorC que implemente Pago y
VentaServicio queda igual. Si VentaServicio llamara directo a los proveedores, habria que agregarle un if por cada proveedor nuevo.


L: Sustitucion de Liskov

Una clase hija o una implementacion debe poder reemplazar a su tipo padre sin dañar el programa. VentaServicio trabaja con un Pago, y le podemos pasar
AdaptadorA o AdaptadorB sin cambiar nada: en los dos casos llama a pagar y recibe si el cobro se hizo. Lo mismo pasa con Evento: ReporteServicio llama a
tipo() sin importar si es Concierto, Partido u ObraTeatro.


I: Segregacion de interfaces

Ninguna clase debe estar obligada a tener metodos que no usa. ParteEscenario solo tiene capacidad, ingresos y ocupadas, porque eso lo responden todas las
partes. El metodo agregar esta en Contenedor y no en la interfaz, porque si estuviera ahi, Silla tendria que implementarlo aunque a una silla no se le
pueda agregar nada.


D: Inversion de dependencias

Las clases importantes deben depender de una interfaz y no de una clase concreta. VentaServicio recibe un Pago en el constructor y no sabe si por dentro
es AdaptadorA o AdaptadorB. Para cambiar de proveedor solo se cambia lo que se le pasa al crearlo en el Main. VentaServicio no se toca.


