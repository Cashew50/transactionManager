import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.time.*;
public class Test2 {
    
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        /* adding to the list above */transactions.add(/*calles the Transaction constructor so that each element that is passed in gets a varable assinged to it*/new Transaction(LocalDate.of(2020, 11, 1), "Morrisons", "card", "£10.40", "Groceries"));
        transactions.add(new Transaction(LocalDate.of(2020, 10, 28), "CYBG", "direct debit", "£600", "MyMonthlyDD"));
        transactions.add(new Transaction(LocalDate.of(2022, 10, 28), "PureGym", "direct debit", "£40", "MyMonthlyDD"));
        transactions.add(new Transaction(LocalDate.of(2023, 10, 1), "M&S", "card", "£5.99", "Groceries"));
        transactions.add(new Transaction(LocalDate.of(2019, 9, 30), "McMillan", "internet", "£10", "Null"));

       
        /* the list that holds all colacted data from the stream */List<Transaction> myMonthlyDDTransactions = transactions.stream()
                .filter(/* random variable name */Transactionxx -> Transactionxx.category.equals("MyMonthlyDD"))
                //returns a list of all found things 
                .collect(Collectors.toList());

        //printing out data from the stream list
        for (Transaction x : myMonthlyDDTransactions) {
            System.out.println(x.merchant + " " + x.amount+ " "+ x.date);
        }

        System.out.println("===========================================");


        //testing sorted function returns dates sorted 
        List<Transaction> byDate = transactions.stream()
        .sorted(Comparator.comparing(z -> z.date ))
        .collect(Collectors.toList());

        for (Transaction x : byDate) {
            System.out.println(x.date);
        }


        


    }

    public static class Transaction {
        //makes the data types as without them it will throw a error 
        private LocalDate date;
        private String merchant;
        private String paymentMethod;
        private String amount;
        private String category;
        //this is the constructor methord that sets all the values to go into the list so they can be easly called later 
        //instead of imposably indexing through each item etc 
        public Transaction(LocalDate date, String merchant, String paymentMethod, String amount, String category) {
            this.date = date;
            this.merchant = merchant;
            this.paymentMethod = paymentMethod;
            this.amount = amount;
            this.category = category;
        }
    }
}