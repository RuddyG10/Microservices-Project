package com.bankapp.loans.repository;

import com.bankapp.loans.LoansApplication;
import com.bankapp.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
}
