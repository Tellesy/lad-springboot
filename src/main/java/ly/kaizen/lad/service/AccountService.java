package ly.kaizen.lad.service;

import ly.kaizen.lad.model.Account;

import java.util.Optional;
import java.util.List;

public interface AccountService {

    Account createAccount(Account account);

    Optional<Account> getAccountById(Long id);

    Optional<Account> getAccountByIban(String iban);

    List<Account> getAccountsByCustomerId(Long customerId);

    List<Account> getAllAccounts();

    Account updateAccount(Long id, Account account);

    void deleteAccount(Long id);
}
