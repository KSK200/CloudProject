package com.THBS.cloudkitchenapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.THBS.cloudkitchenapplication.entity.AccountDetails;


@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {

    AccountDetails findByCatererId(Long catererId);

}

