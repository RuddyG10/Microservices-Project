package com.bankapp.loans.service.impl;

import com.bankapp.loans.constants.LoansConstants;
import com.bankapp.loans.dto.LoansDto;
import com.bankapp.loans.entity.Loans;
import com.bankapp.loans.exception.LoanAlreadyExistsException;
import com.bankapp.loans.exception.ResourceNotFoundException;
import com.bankapp.loans.mapper.LoansMapper;
import com.bankapp.loans.repository.LoansRepository;
import com.bankapp.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber of the client that request a Loan
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with the given Mobile Number "+mobileNumber);
        }
        Loans saveLoans = loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoans = new Loans();
        newLoans.setMobileNumber(mobileNumber);

        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoans.setLoanNumber(Long.toString(randomLoanNumber));
        newLoans.setLoanType(LoansConstants.HOME_LOAN);
        newLoans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoans.setAmountPaid(0);
        newLoans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return newLoans;
    }

    /**
     * @param mobileNumber of the client that has the loan that is requested
     * @return Loan details
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );

        return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    /**
     * @param loansDto the loans detail to be updated
     * @return a boolean indicating if the loan was updated or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan","loanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);

        return true;
    }

    /**
     * @param mobileNumber of the client that has the loan that we want to delete
     * @return a boolean indicating if the loan was deleted or not.
     */
    @Override
    public boolean deletedLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );
        loansRepository.delete(loans);

        return true;
    }


}
