package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //está dizendo ao Spring que isso é um componente e entao fará uma spring bean
public class BootstrapData implements CommandLineRunner { //commandliner inicializará o metodo run

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author clive = new Author();
        clive.setFirstName("Clive");
        clive.setLastName("Lewis");

        Book asCronicasDeNarnia = new Book();
        asCronicasDeNarnia.setTitle("As Crônicas Narnia");
        asCronicasDeNarnia.setIsbn("6555114746");

        Author cliveSaved = new Author();
        Book narniaSaved = new Book();

        cliveSaved.getBooks().add(narniaSaved);

        authorRepository.save(clive);
        bookRepository.save(asCronicasDeNarnia);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
    }
}
