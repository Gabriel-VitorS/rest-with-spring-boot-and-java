package br.com.gabrielvitors.rest_with_spring_boot_and_java.repositories;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, Long> {
}
