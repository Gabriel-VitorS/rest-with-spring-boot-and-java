package br.com.gabrielvitors.rest_with_spring_boot_and_java.controllers;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.BookVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController {

    @Autowired
    BookServices services;

    @GetMapping(value = "/{id}")
    public BookVO findById(
            @PathVariable(value = "id") Long id
    ){
        return services.findById(id);
    }

    @GetMapping()
    public List<BookVO> findAll(){
        return services.findAll();
    }
}
