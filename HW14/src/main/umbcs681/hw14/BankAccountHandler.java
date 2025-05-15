package umbcs681.hw14;

public class BankAccountHandler {
    public static void main(String[] args) {
        AtomicBankAccount atomicBankAccount = new AtomicBankAccount();

        DepositRunnable[] depositHandlers = new DepositRunnable[6];
        WithdrawRunnable[] withdrawHandlers = new WithdrawRunnable[6];
        Thread[] depositThreads = new Thread[6];
        Thread[] withdrawThreads = new Thread[6];

        for (int i = 0; i < 6; i++) {
            depositHandlers[i] = new DepositRunnable(atomicBankAccount);
            withdrawHandlers[i] = new WithdrawRunnable(atomicBankAccount);

            depositThreads[i] = new Thread(depositHandlers[i]);
            withdrawThreads[i] = new Thread(withdrawHandlers[i]);

            depositThreads[i].start();
            withdrawThreads[i].start();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < 6; i++) {
            depositHandlers[i].setDone();
            withdrawHandlers[i].setDone();
            System.out.println("Set done for Deposit and Withdraw handlers.");
        }

        for (int i = 0; i < 6; i++) {
            depositThreads[i].interrupt();
            withdrawThreads[i].interrupt();
        }

        try {
            for (int i = 0; i < 6; i++) {
                depositThreads[i].join();
                withdrawThreads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("All Deposit and Withdraw threads are terminated.");
    }
}
