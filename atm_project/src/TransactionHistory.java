import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TransactionHistory {

    protected TransactionHistory() {}


    protected File createTransactionHistory(String id){
        File t = new File(""+id + ".txt");
        return t;
    }
    protected void addMoney(double amount, double balance, File t) throws IOException {
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(t, true));
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        writer2.write("Credited: " + amount + "rs Date-Time: "+formattedDateTime);
        writer2.write("\nAvailable Balance: "+balance);
        writer2.newLine();
        writer2.flush();
        writer2.close();
    }

    protected void withdrawMoney(double amount, double balance, File t) throws IOException {
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(t, true));
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        writer2.write("Debited: " + amount + "rs Date-Time: " +formattedDateTime);
        writer2.write("\nAvailable Balance: "+balance);
        writer2.newLine();
        writer2.flush();
        writer2.close();
    }

    protected void display(File t) throws IOException {
        try {
            BufferedReader reader2 = new BufferedReader(new FileReader(t));
            String line;
            while ((line = reader2.readLine()) != null) {
                System.out.println(line);
            }
            reader2.close();
        }
        catch (IOException e){
            System.out.println("No transaction history so far");
        }
    }
}

