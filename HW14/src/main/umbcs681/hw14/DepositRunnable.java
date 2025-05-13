package umbcs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;

public class DepositRunnable implements Runnable {
    private BankAccount account;
    private AtomicBoolean done = new AtomicBoolean(false);

    public void setDone() {
        this.done.set(true);
    }

    public DepositRunnable(BankAccount account) {
        this.account = account;
    }

    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                if (done.get()) {
                    System.out.println("(d) set done is called.");
                    break;
                }
                account.deposit(100);

                Thread.sleep(200);
            }
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
