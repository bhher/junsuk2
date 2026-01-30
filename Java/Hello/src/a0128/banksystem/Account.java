package a0128.banksystem;

public class Account {
    private String accountNumber; //계좌번호
    private String ownerName;      // 예금주명
    private int balance;           // 잔액
    public Account(String accountNumber, String ownerName, int balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    //파일 저장용 문자열
    public String toFileString(){
        return accountNumber + "|" + ownerName + "|" + balance;
    }
    //123-456|홍길동|10000

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "계좌번호: " + accountNumber + ", 예금주 : " + ownerName + ", 잔액 : " + balance + "원";
    }

    public void deposit(int depositAmount) {
        this.balance += depositAmount;
    }

    public boolean withdraw(int amount) {
       if(balance >= amount){
            this.balance -= amount;
            return true;
       }else{
         return false;
       }

    }

    public static Account fromFileString(String line) {
     try {
        String[] parts = line.split("\\|"); 
         //parts[0] = "123-456"; parts[1]="허준석";  parts[2]="10000"
       if(parts.length == 3){
          String accountNumber = parts[0].trim(); //공백제거후 123-456
          String ownerName = parts[1].trim(); //공백제거후 허준석
          int balance = Integer.parseInt(parts[3].trim());  //10000
          return new Account(accountNumber, ownerName, balance);
       }

    } catch (NumberFormatException e) {
       return null;
    }
    return null;
    }
    
    

}
