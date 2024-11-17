package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.Account;
import ly.kaizen.lad.repository.AccountRepository;
import ly.kaizen.lad.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> getAccountByIban(String iban) {
        return accountRepository.findAll()
                .stream()
                .filter(account -> account.getIban().equals(iban))
                .findFirst();
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        return accountRepository.findById(id)
                .map(existingAccount -> {
                    existingAccount.setAccountNumber(account.getAccountNumber());
                    existingAccount.setAccountType(account.getAccountType());
                    existingAccount.setIban(account.getIban());
                    return accountRepository.save(existingAccount);
                })
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + id));
    }

    @Override
    public void deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Account not found with id: " + id);
        }
    }
}
