package com.bankapp.accounts.service;

import com.bankapp.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;


public interface IAccountsService {
    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);
}
