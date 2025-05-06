package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

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

        Author cliveSaved = authorRepository.save(clive);
        Book narniaSaved = bookRepository.save(asCronicasDeNarnia);

        cliveSaved.getBooks().add(narniaSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
    }
}
