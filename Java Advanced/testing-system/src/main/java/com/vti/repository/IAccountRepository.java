package com.vti.repository;

import com.vti.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Account findByUsername(String username);

    // tim theo username  nhưng ko muốn dùng findByUsername
    @Query("FROM Account a where username = :username")
    Account sByUsername(String username);

    @Query(value = "select * from account where username = :username", nativeQuery = true)
    Account seByUsername(String username);




    boolean existsByUsernameAndIdNot(String username, Integer id);
    boolean existsByEmailAndIdNot(String email, Integer id);
}