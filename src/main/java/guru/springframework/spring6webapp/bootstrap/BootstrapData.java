package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //está dizendo ao Spring que isso é um componente e entao fará uma spring bean
public class BootstrapData implements CommandLineRunner { //commandliner inicializará o metodo run

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author clive = new Author();
        clive.setFirstName("Clive");
        clive.setLastName("Lewis");
        Author cliveSaved = authorRepository.save(clive); //salvando no repositorio

        Book asCronicasDeNarnia = new Book();
        asCronicasDeNarnia.setTitle("As Crônicas Narnia");
        asCronicasDeNarnia.setIsbn("6555114746");
        Book narniaSaved = bookRepository.save(asCronicasDeNarnia); //salvando no repositorio

        cliveSaved.getBooks().add(narniaSaved); //relacionando o livro ao autor

        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main");
        Publisher savedPublisher = publisherRepository.save(publisher);

        narniaSaved.setPublisher(savedPublisher); //relacionando publisher com o livro

        authorRepository.save(cliveSaved);
        bookRepository.save(narniaSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.printf("Publisher count: " + publisherRepository.count());
    }
}
