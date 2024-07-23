import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Transaction extends TransactionHistory{
    protected Transaction() {}
    protected void deposit(Scanner sc, User u, File t) throws IOException {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        u.balance += amount;
        System.out.println(amount+"rs deposited successfully");
        addMoney(amount,u.balance,t);
    }
    protected void withdraw_transfer(Scanner sc, User u, String ch, File t) throws IOException {
        System.out.print("Enter amount to "+ch+" : ");
        double amount = sc.nextDouble();
        if(u.balance<amount){
            System.out.println("Insufficient balance!!");
        }
        else {
            if (ch.equals("withdraw")){
                System.out.println(amount+"rs withdrawn successfully");
                u.balance -= amount;
                withdrawMoney(amount,u.balance,t);
            }
            else{
                System.out.print("Enter the person's bank id to transfer: ");
                String id2 = sc.next();
                System.out.println(amount+"rs transferred to bank id:"+id2+" successfully");
                u.balance -= amount;
                withdrawMoney(amount,u.balance,t);
            }
        }
    }
}
