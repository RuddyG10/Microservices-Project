package com.bankapp.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and account information."
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = "Ruddy Gomez"
    )
    @NotEmpty(message = "Name can not be null or empty.")
    @Size(min = 5,max = 30,message = "The length of the customer name should be between 5 and 30 characters.")
    private String name;

    @Schema(
            description = "Email of the customer",
            example = "ruddy@email.com"
    )
    @NotEmpty(message = "Email Address can not be null or empty.")
    @Email(message = "Email address should be a valid email.")
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;

}
