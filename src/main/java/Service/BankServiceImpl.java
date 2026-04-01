package Service;

import jakarta.jws.WebService;
import Model.Account;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "Service.BankService")
public class BankServiceImpl implements BankService {

    private List<Account> accounts = new ArrayList<>();

    private Account findAccount(String id) {
        return accounts.stream().filter(acc -> acc.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override

    public synchronized String createAccount(String id, String owner, double initialBalance) {
        if (accounts.stream().anyMatch(acc -> acc.getId().equals(id))) {
            return "Error: Ya existe una cuenta con ese ID";
        }
        accounts.add(new Account(id, owner, initialBalance));
        System.out.println("Account created for " + owner + " with id " + id) ;
        return "Cuenta creada exitosamente";
    }

    @Override
    public synchronized String deposit(String id, double amount) {
        Account acc = findAccount(id);
        acc.setBalance(acc.getBalance() + amount);
        System.out.println("Deposit: " + amount);

        return "Deposito exitoso";
    }

    @Override
    public synchronized String withdraw(String id, double amount) {
        Account acc = findAccount(id);

        if (acc.getBalance() < amount) {
            throw new RuntimeException("Fondos insuficientes");
        }

        acc.setBalance(acc.getBalance() - amount);
        return "Retiro exitoso";
    }

    @Override
    public synchronized double getBalance(String id) {
        return findAccount(id).getBalance();
    }

    @Override
    public synchronized String transfer(String fromId, String toId, double amount) {
        withdraw(fromId, amount);
        deposit(toId, amount);
        System.out.println("Transferencia completada");

        return "Transferencia exitosa";
    }
}