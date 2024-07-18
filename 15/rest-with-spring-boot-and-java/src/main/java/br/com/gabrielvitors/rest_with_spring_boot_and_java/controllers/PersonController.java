package br.com.gabrielvitors.rest_with_spring_boot_and_java.controllers;

import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v1.PersonVO;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.data.vo.v2.v1.PersonVOV2;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.model.Person;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.services.PersonServices;
import br.com.gabrielvitors.rest_with_spring_boot_and_java.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/person")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {

    @Autowired
    private PersonServices services;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds a person", description = "Finds a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonVO.class)
                            )
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO findById(
            @PathVariable(value = "id") Integer id){

        return services.findById(id);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete a person", description = "Delete a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Adds a new person", description = "Adds a new person passing in JSON",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonVO.class)
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO create(
            @RequestBody PersonVO PersonVO){

        return services.create(PersonVO);
    }

    @PostMapping(
            value = "/v2",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createV2(
            @RequestBody PersonVOV2 personVOV2){

        return services.createV2(personVOV2);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates a person", description = "Updates person passing in JSON",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonVO.class)
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonVO update(
            @RequestBody PersonVO PersonVO){

        return services.update(PersonVO);
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds all people", description = "Finds all people",
            tags = {"People"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                        @Content(
                                mediaType = "application/json"
//                                array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                        )
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<PersonVO> findAll(){

        return services.findAll();
    }

}
