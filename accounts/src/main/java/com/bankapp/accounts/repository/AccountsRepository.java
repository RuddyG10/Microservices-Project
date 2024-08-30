package com.bankapp.accounts.repository;

import com.bankapp.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional //if some error happens during the transaction, it will be rolled back
    @Modifying //this method will change the data from teh database
    void deleteByCustomerId(Long customerId);
}
