package br.com.gabrielvitors.rest_with_spring_boot_and_java.services;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.BookVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.exceptions.ResourceNotFoundException;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper.BookMapper;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Book;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    public BookRepository repository;

    public BookVO findById(Long id){

        Book book = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        return BookMapper.INSTANCE.bookToBookVo(book);
    }

    public List<BookVO> findAll(){

        List<Book> books = repository.findAll();

        return BookMapper.INSTANCE.listBooksToBookVo(books);
    }

    public BookVO create(BookVO bookVo){

        Book book = BookMapper.INSTANCE.bookVoToBook(bookVo);

        return BookMapper.INSTANCE.bookToBookVo(repository.save(book));
    }

    public BookVO update(BookVO bookVo){

        Book book = repository.findById(bookVo.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        book.setAuthor(book.getAuthor());
        book.setLaunchDate(book.getLaunchDate());
        book.setPrice(bookVo.getPrice());
        book.setTitle(bookVo.getTitle());

        return BookMapper.INSTANCE.bookToBookVo(repository.save(book));
    }

    public void delete(Long id){
        Book book = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(book);
    }
}
