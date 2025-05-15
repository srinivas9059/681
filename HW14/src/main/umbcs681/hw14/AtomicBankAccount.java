package umbcs681.hw14;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicBankAccount implements BankAccount {
    private AtomicReference<Double> balance;

    public AtomicBankAccount() {
        this.balance = new AtomicReference<>(0.0);
    }

    public void deposit(double amount) {
        System.out.print("Current balance (d): " + this.balance.get());
        this.balance.accumulateAndGet(amount, (prevBalance, withdrawAmount) -> prevBalance + withdrawAmount);
        System.out.println(", New balance (d): " + this.balance.get());
    }

    public void withdraw(double amount) {
        System.out.print("Current balance (w): " + this.balance.get());
        this.balance.accumulateAndGet(amount, (prevBalance, withdrawAmount) -> prevBalance - withdrawAmount);
        System.out.println(", New balance (w): " + this.balance.get());
    }

    public double getBalance() {
        return this.balance.get();
    }
}
