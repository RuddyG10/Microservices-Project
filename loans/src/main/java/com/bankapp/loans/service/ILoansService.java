package com.bankapp.loans.service;

import com.bankapp.loans.dto.LoansDto;

import java.util.List;

public interface ILoansService {

    /**
     *
     * @param mobileNumber of the client that request a Loan
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber of the client that has the loan that is requested
     * @return Loan details
     */
    LoansDto fetchLoan(String mobileNumber);


    /**
     *
     * @param loansDto the loans detail to be updated
     * @return a boolean indicating if the loan was updated or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param mobileNumber of the client that has the loan that we want to delete
     * @return a boolean indicating if the loan was deleted or not.
     */
    boolean deletedLoan(String mobileNumber);
}
