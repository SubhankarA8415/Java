import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        File f = new File("data.txt");
        ArrayList<String> userlist = getUsers(f);
        System.out.println("Welcome to ATM our interface");
        String id; int pin; double bal = 0; User u;
        System.out.print("Press type e to continue \n"+
                "Don't have an account type c to create your account: ");
        String ch = sc.next();
        if( ch.equalsIgnoreCase("e")){
            l1: while (true) {
                System.out.print("Enter your id: ");
                id = sc.next();
                System.out.print("Enter your pin: ");
                pin = sc.nextInt();
                for(int i = 0; i<userlist.size(); i+=3){
                    if((userlist.get(i)).equals(id)){
                        if(userlist.get(i+1).equals(""+pin)){
                            System.out.println("Logged in successfully");
                            bal = Double.parseDouble(userlist.get(i+2));
                            u = createUser(id,pin,bal);
                            break l1;
                        }
                        System.out.println("Invalid pin");
                        continue l1;
                    }
                }
                System.out.println("Account doesn't exists!!");
                System.out.print("Want to create account? \n" +
                        "Type Y or N: ");
                ch = sc.next();
                if (ch.equalsIgnoreCase("Y")){
                    u = createAccount(f);
                    break;
                }
                else{
                    System.out.println("Thank you for using our interface");
                    System.exit(0);
                }
            }
        }
        else if(ch.equalsIgnoreCase("c")) {
            u = createAccount(f);
            id = u.getId();
            pin = u.getPin();
            bal = u.getBalance();
        }
        else {
            id = "null";
            u = null;
            System.out.println("Invalid operation");
            System.out.println("Program terminated");
            System.exit(0);
        }
        File t = new File(id+".txt");
        Transaction tr = new Transaction();
        TransactionHistory h = new TransactionHistory();
        while (true){
            System.out.print("Enter what operation you want to perform \n" +
                    "1: Transaction History\n" +
                    "2: Withdraw\n" +
                    "3: Deposit\n" +
                    "4: Transfer\n" +
                    "5: Check Balance\n" +
                    "6: Exit: ");
            int ch2 = sc.nextInt();
            switch(ch2) {
                case 1:
                    h.display(t);
                    break;
                case 2:
                    tr.withdraw_transfer(sc,u,"withdraw",t);

                    break;
                case 3:
                    tr.deposit(sc,u,t);
                    break;
                case 4:
                    tr.withdraw_transfer(sc,u,"transfer",t);
                    break;
                case 5:
                    System.out.println("Available balance: "+u.getBalance()+"rs");
                    break;
                case 6:
                    BufferedReader reader = new BufferedReader(new FileReader(f));
                    ArrayList<String> update = new ArrayList<>();
                    String l;
                    while ((l = reader.readLine())!=null){
                        update.add(l);
                    }
                    reader.close();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                    int i = 0;
                    while (i<update.size()){
                        if(id.equals(update.get(i))){
                            writer.write(update.get(i));
                            writer.newLine();
                            writer.write(""+u.getPin());
                            writer.newLine();
                            writer.write(""+u.getBalance());
                            writer.newLine();
                            i+=3;
                            continue;
                        }
                        writer.write(update.get(i));
                        writer.newLine();
                        i++;
                    }
                    writer.flush();
                    writer.close();
                    System.out.println("Thank you for using out ATM interface");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    protected static User createUser(String id, int pin, double balance){
        User u = new User();
        u.id = id;
        u.pin = pin;
        u.balance = balance;
        return u;
    }
    protected static ArrayList<String> getUsers(File f) throws IOException {
        ArrayList<String> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String l;
        while((l = reader.readLine())!=null){
            users.add(l);
        }
        return users;
    }
    protected static User createAccount(File f) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(f,true));
        System.out.print("Create one user id: ");
        String id = sc.next();
        System.out.print("Create one 4-digit pin: ");
        int pin = sc.nextInt();
        System.out.print("Deposit account opening balance: ");
        double balance = sc.nextDouble();

        User u = new User(id,pin,balance);
        writer.append(id);
        writer.append("\n"+pin);
        writer.append("\n"+balance+"\n");
        System.out.println("Account created successfully");
        System.out.println("Logged in successfully");
        writer.flush();
        return u;

    }
}