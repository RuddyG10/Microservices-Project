package com.bankapp.cards.controller;

import com.bankapp.cards.constants.CardsConstants;
import com.bankapp.cards.dto.CardsDto;
import com.bankapp.cards.dto.ErrorResponseDto;
import com.bankapp.cards.dto.ResponseDto;
import com.bankapp.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "CRUD REST API fro Cards in BankApp",
        description = "CRUD REST APIs in BankApp to CREATE, UPDATE, FETCH and DELETE Cards details."
)
@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardsController {
    private ICardsService iCardsService;

    @Operation(
            summary = "Create Card REST API.",
            description = "REST API to create new Card inside BankApp."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED."
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits.")
                                                      String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card REST API.",
            description = "REST API to fetch a Card details based on a given card number."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK."
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                        @Pattern(regexp = "(^$|[0-9]{12})",message = "Card number must be 12 digits.")
                                                        String cardNumber){
        CardsDto cardsDto = iCardsService.fetchCard(cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Fetch List of Card REST API.",
            description = "REST API to fetch a List of Card details based on a given mobile number."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK."
    )
    @GetMapping("/fetchAll")
    public ResponseEntity<List<CardsDto>> fetchAllCardDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits.")
                                                            String mobileNumber){
        List<CardsDto> cardsDto = iCardsService.fetchAllCardsFromMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card details REST API.",
            description = "REST API to update a Card details based on a given card object."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK."
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Expectation Failed."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error.",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto){
        boolean isUpdated = iCardsService.updateCard(cardsDto);
        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card details REST API.",
            description = "REST API to Delete a Card details based on a given mobile number."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK."
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Expectation Failed."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error.",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits.")
                                                            String mobileNumber){
        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE));
        }
    }
}
