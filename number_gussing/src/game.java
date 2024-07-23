import java.util.*;

public class game {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to number guessing game\n" +
                "Rules: 1) Guess a number between 1 to 100\n" +
                "       2) Number of attempts is 6\n" +
                "       3) The lesser attempt you took then more number of points you earn\n" +
                "       4) As soon as you guess the random number correctly within the available amount of attempts you will win!!");
        int ran = (int)(Math.random()* 100 +1);
        float score = 100f;
        int attempts = 6;
        while (true){
            System.out.print("Guess a number: ");
            int num = sc.nextInt();
            if(num == ran){
                System.out.println("You win!!");
                System.out.println("Your score: "+score/(7-attempts)+" out of 100");
                System.out.println("Wanna play again\n" +
                        "Type y or n: ");
                String ch = sc.next();
                if (ch.equalsIgnoreCase("n")){
                    System.out.println("Thank you for playing our game");
                    System.exit(0);
                }
                else{
                    ran = (int)(Math.random()* 100 +1);
                    score = 100f;
                    attempts = 6;
                    continue;
                }
            }
            else if(num > ran){
                System.out.println("Try guessing a smaller number next time");
                System.out.println("Number of attempts left: "+(attempts-1));
            }
            else if(num < ran){
                System.out.println("Try guessing a larger number next time");
                System.out.println("Number of attempts left: "+(attempts-1));
            }
            attempts-=1;
            if(attempts == 0){
                System.out.println("You lose");
                System.out.println("Wanna play again\n" +
                        "Type y or n: ");
                String ch = sc.next();
                if (ch.equalsIgnoreCase("n")){
                    System.out.println("Thank you for playing our game");
                    System.exit(0);
                }
                else {
                    ran = (int)(Math.random()* 100 +1);
                    score = 100f;
                    attempts = 6;
                }
            }
        }

    }
}
