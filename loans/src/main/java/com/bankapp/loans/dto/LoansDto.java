package com.bankapp.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "Mobile number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits.")
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Loan number must be 12 digits.")
    private String loanNumber;

    @NotEmpty(message = "Loan type cannot be null or empty.")
    private String loanType;

    @Positive(message = "Total loan should be greater than zero.")
    private Integer totalLoan;

    @PositiveOrZero(message = "Amount paid should be greater or equal than zero.")
    private Integer amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be greater or equal than zero.")
    private Integer outstandingAmount;

}
