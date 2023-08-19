package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch,Integer> {
    @Query(value = "select branch from Branch branch where branch_name=:branchName")
    Optional<Branch> findByBranchName(String branchName);
}
