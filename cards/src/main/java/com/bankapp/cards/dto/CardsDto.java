package com.bankapp.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
@Schema(
        name = "Cards",
        description = "Schema to hold Card information"
)
@Data
public class CardsDto {

    @Schema(
            description = "Mobile user of the customer",
            example = "1234567890"
    )
    @NotEmpty(message = "Card mobile number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Card mobile number must be 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "Card number of the customer.",
            example = "123456789012"
    )
    @NotEmpty(message = "Card number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Card number must be 12 digits.")
    private String cardNumber;

    @Schema(
            description = "Type of the card.",
            example = "Credit Card"
    )
    @NotEmpty(message = "Card type cannot be null or empty.")
    private String cardType;

    @Schema(
            description = "Total amount limit available against a card.",
            example = "100000"
    )
    @Positive(message = "Total limit should be greater than zero.")
    private Long totalLimit;

    @Schema(
            description = "Total amount used by a customer",
            example = "1000"
    )
    @PositiveOrZero(message = "Total amount used should be equal or greater than zero.")
    private Long amountUsed;

    @Schema(
            description = "Total available amount against a card.",
            example = "90000"
    )
    @PositiveOrZero(message = "Total available amount should be equal or greater than zero.")
    private Long availableAmount;
}
