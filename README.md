# LiterAlura - Proyecto Java

Una aplicación de consola que permite a los usuarios obtener y almacenar libros desde la API de Gutendex.com, y buscar sus libros guardados según varios criterios.

## Descripción general

LiterAlura es una aplicación de consola Java desarrollada con Spring Boot que interactúa con la API de Gutendex.com para acceder a una amplia colección de información de libros. Los usuarios pueden obtener libros de la API y almacenarlos en una base de datos PostgreSQL para su recuperación posterior. La aplicación también admite la búsqueda de libros guardados por título, género, idioma y autores, así como la recuperación de autores que estaban vivos después de una fecha especificada por el usuario.

## Características

- Integración de API: obtiene datos de libros desde la API de Gutendex.com.
- Interacción con la base de datos: almacena y recupera libros de una base de datos PostgreSQL.
- Funcionalidad de búsqueda: permite a los usuarios buscar libros guardados por título, género, idioma y autores.
- Filtrado de autores: admite el filtrado de autores que estaban vivos después de una fecha especificada por el usuario.

## Tecnologías utilizadas

- Java: el lenguaje de programación principal utilizado para el desarrollo de la aplicación.
- Spring Boot 3.2: un marco robusto para la creación de aplicaciones Java.
- API de Gutendex.com: una API externa que proporciona acceso a los datos de los libros.
- PostgreSQL: un potente sistema de base de datos relacional de código abierto.

## Cómo empezar

Para ejecutar la aplicación LiterAlura localmente, siga estos pasos:

1. Clone este repositorio en su máquina local.
2. Abra el proyecto en su IDE Java preferido.
3. Asegúrese de tener PostgreSQL instalado y en ejecución en su máquina.
4. Actualice el archivo `application.properties` con sus credenciales de base de datos PostgreSQL.
5. Ejecute la aplicación.

## Uso

Al iniciar la aplicación LiterAlura, se le presentará un menú de opciones para interactuar con la aplicación. Puede:

- Obtener y almacenar libros desde la API de Gutendex.com.
- Buscar libros guardados por título, género, idioma y autores.
- Recuperar autores que estaban vivos después de una fecha especificada.
- Ver una lista de todos los libros guardados.
- Salir de la aplicación.

Elija una opción del menú y siga las indicaciones para interactuar con la aplicación. La aplicación mostrará los resultados de su consulta o acción.

Autor
------------

Fernando Jorge Marson

[GitHub](https://github.com/fjmarson)

[LinkedIn](https://www.linkedin.com/in/fernando-marson-dev)
