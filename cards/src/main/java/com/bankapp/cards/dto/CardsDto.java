package com.bankapp.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsDto {

    @NotEmpty(message = "Card mobile number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Card mobile number must be 10 digits.")
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Card number must be 12 digits.")
    private String cardNumber;

    @NotEmpty(message = "Card type cannot be null or empty.")
    private String cardType;

    @Positive(message = "Total limit should be greater than zero.")
    private Long totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero.")
    private Long amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero.")
    private Long availableAmount;
}
