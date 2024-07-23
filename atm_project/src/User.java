class User{
    protected String id;
    protected int pin;
    protected double balance;

    protected User(){}
    protected User(String id, int pin, double balance){
        this.id = id;
        this.pin = pin;
        this.balance = balance;
    }

    protected String getId() {
        return id;
    }

    protected int getPin() {
        return pin;
    }

    protected double getBalance() {
        return balance;
    }

}