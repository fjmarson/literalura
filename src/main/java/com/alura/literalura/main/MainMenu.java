package com.alura.literalura.main;

import com.alura.literalura.model.AuthorInfo;
import com.alura.literalura.model.Book;
import com.alura.literalura.model.Data;
import com.alura.literalura.model.Languages;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.services.DataConvert;
import com.alura.literalura.services.RequestAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class MainMenu {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private Scanner scanner = new Scanner(System.in);
    private RequestAPI requestAPI = new RequestAPI();
    private DataConvert dataConvert = new DataConvert();
    private BookRepository repository;
    private List<Book> books;
    private String bookSelected;

    public MainMenu(BookRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("""
                    --------------------------------
                       "Bienvenido a LiterAlura"                  
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    --------------------------------
                    """);
            option = scanner.nextInt();
            scanner.nextLine();

            if (option < 0 || option > 5) {
                System.out.println("Opción incorrecta. Por favor, ingrese una opción válida.");
            } else {
                switch (option) {
                    case 1 -> getBookData();
                    case 2 -> showStoredBooks();
                    case 3 -> authorsListStored();
                    case 4 -> getAuthorYear();
                    case 5 -> findBooksByLanguages();
                }
            }
        } while (option != 0);
    }

    private String getDataFromUser() {
        System.out.println("Introduzca el nombre del libro que desea buscar");
        return scanner.nextLine();
    }

    private Data getBookDataFromAPI(String bookTitle) {
        var json = requestAPI.getData(BASE_URL + "?search=%20" + bookTitle.replace(" ", "+"));
        return dataConvert.getData(json, Data.class);
    }

    private Optional<Book> getBookInfo(Data bookData, String bookTitle) {
        return bookData.results().stream()
                .filter(l -> l.title().toLowerCase().contains(bookTitle.toLowerCase()))
                .map(b -> new Book(b.title(), b.languages(), b.downloads(), b.authors()))
                .findFirst();
    }

    private Optional<Book> getBookData() {
        String bookTitle = getDataFromUser();
        Data bookInfo = getBookDataFromAPI(bookTitle);
        Optional<Book> book = getBookInfo(bookInfo, bookTitle);

        if (book.isPresent()) {
            var b = book.get();
            repository.save(b);
            System.out.println(b);
        } else {
            System.out.println("\nLibro no encontrado\n");
        }

        return book;
    }

    private void showStoredBooks() {
        books = repository.findAll();
        books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    private void authorsListStored() {
        List<AuthorInfo> authors = repository.getAuthorsInfo();
        authors.stream()
                .sorted(Comparator.comparing(AuthorInfo::getName))
                .forEach(a -> System.out.printf("Autor: %s; Nacimiento: %s; Muerte: %s\n",
                        a.getName(), a.getBirthYear(), a.getDeathYear()));
    }

    public void getAuthorYear() {
        System.out.println("Introduzca el año a partir del cual desea saber si un autor estaba vivo");
        int date = scanner.nextInt();
        scanner.nextLine();

        List<AuthorInfo> authorInfos = repository.getAuthorLiveAfter(date);

        authorInfos.stream()
                .sorted(Comparator.comparing(AuthorInfo::getName))
                .forEach(a -> System.out.printf("Autor: %s; Nacimiento: %s; Muerte: %s\n",
                        a.getName(), a.getBirthYear(), a.getDeathYear()));
    }

    public void findBooksByLanguages() {
        String languagesList = """
                Elija las opciones del idioma del libro que desea buscar
                
                en - Inglés
                es - Español
                fr - Francés
                it - Italiano
                pt - Portugués
                
                """;
        System.out.println(languagesList);
        String text = scanner.nextLine();

        var language = Languages.fromString(text);

        List<Book> bookLanguage = repository.findByLanguages(language);

        bookLanguage.stream()
                .forEach(System.out::println);
    }
}
