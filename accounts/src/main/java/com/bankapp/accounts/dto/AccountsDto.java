package com.bankapp.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountsDto {

    @Schema(
            description = "Account Number of BankApp Account.",
            example = "1234567890"
    )
    @NotEmpty(message = "Account Number can not be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits.")
    private Long accountNumber;

    @Schema(
            description = "Account type of BankApp account.",
            example = "Savings"
    )
    @NotEmpty(message = "Account type can not be null or empty.")
    private String accountType;

    @Schema(
            description = "BankApp branch address.",
            example = "123 New York"
    )
    @NotEmpty(message = "Account branch address can not be null or empty.")
    private String branchAddress;

}
