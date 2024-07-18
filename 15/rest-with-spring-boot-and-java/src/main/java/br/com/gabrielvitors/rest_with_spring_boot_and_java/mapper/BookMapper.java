package br.com.gabrielvitors.rest_with_spring_boot_and_java.mapper;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.BookVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.PersonVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Book;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookVO bookToBookVo(Book book);

    Book bookVoToBook(BookVO bookVo);

    List<BookVO> listBooksToBookVo(List<Book> books);
}
