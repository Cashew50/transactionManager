import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.*;
import java.time.temporal.ChronoUnit;
public class TransactionAnalyzer {
    //testtttttttttttttttttt
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.of(2020, 11, 1), "Morrisons", "card", 10.40, "Groceries"));
        transactions.add(new Transaction(LocalDate.of(2020, 10, 28), "CYBG", "direct debit", 600, "MyMonthlyDD"));
        transactions.add(new Transaction(LocalDate.of(2020, 10, 28), "PureGym", "direct debit", 40, "MyMonthlyDD"));
        transactions.add(new Transaction(LocalDate.of(2020, 10, 1), "M&S", "card", 5.99, "Groceries"));
        transactions.add(new Transaction(LocalDate.of(2020, 9, 30), "McMillan", "internet", 10, "Null"));

        
        allTransactionsForAGivenCategory(transactions,"Groceries");


        totalOutgoingPerCategory(transactions);


        monthlyAverageSpendInAGivenCategory(transactions, "Groceries");


        highestSpendInAGivenCategoryForAGivenYear(transactions, "Groceries", 2020);


        lowestSpendInAGivenCategoryForAGivenYear(transactions, "Groceries", 2020);
    }

    public static class Transaction { 
        private LocalDate date;
        private String merchant;
        private String paymentMethod;
        private double amount;
        private String category;
        public Transaction(LocalDate date, String merchant, String paymentMethod, double amount, String category) {
            this.date = date;
            this.merchant = merchant;
            this.paymentMethod = paymentMethod;
            this.amount = amount;
            this.category = category;
        }
    }


    public static void allTransactionsForAGivenCategory(List<Transaction> transactions, String category){
        List<Transaction> byDate = transactions.stream()
        .filter(Transactionxx -> Transactionxx.category.equals(category))
        .sorted(Comparator.comparing(z -> z.date ))
        .collect(Collectors.toList());

        for (Transaction x : byDate) {
            System.out.println(x.amount);
        }
        System.out.println("===========================================");
    }
    

    public static void totalOutgoingPerCategory(List<Transaction> transactions){
        Map<String, Double> result = new HashMap<>();
        for(Transaction Transaction : transactions){
            String cat = Transaction.category;
            Double amount = Transaction.amount;
            if(result.containsKey(cat)){
                result.merge(cat, amount, Double::sum);
            }
            else{
            result.put(cat, amount);
            }
        }   
        System.out.println(result);
        System.out.println("===========================================");
    }


    public static void monthlyAverageSpendInAGivenCategory(List<Transaction> transactions, String category){
        List<Transaction> dates  = transactions.stream()
        .sorted(Comparator.comparing(z -> z.date ))
        .collect(Collectors.toList());

        Transaction latestDate = dates.get(dates.size() - 1);
        
        Transaction earliestDate = dates.get(0);
        
        long difference = ChronoUnit.MONTHS.between(earliestDate.date, latestDate.date);

        Map<String, Double> result = new HashMap<>();
        for(Transaction Transaction : transactions){
            String cat = Transaction.category;
            Double amount = Transaction.amount;
            if(result.containsKey(cat)){
                result.merge(cat, amount, Double::sum);
            }
            else{
            result.put(cat, amount);
            }
        }   
        System.out.println(result.get(category)/difference);
        System.out.println("===========================================");
    }


    public static void highestSpendInAGivenCategoryForAGivenYear(List<Transaction> transactions, String category, int yearx) {
        Optional<Transaction> highestSpend = transactions.stream()
            .filter(transaction -> transaction.date.getYear() == yearx && transaction.category.equals(category))
            .max(Comparator.comparingDouble(transaction -> transaction.amount));
        
            highestSpend.ifPresent(transaction -> {
            System.out.println(transaction.amount);
        });
        System.out.println("===========================================");
    }


    public static void lowestSpendInAGivenCategoryForAGivenYear(List<Transaction> transactions, String category, int yearx) {
        Optional<Transaction> lowestSpend = transactions.stream()
            .filter(transaction -> transaction.date.getYear() == yearx && transaction.category.equals(category))
            .min(Comparator.comparingDouble(transactionz -> transactionz.amount));
        
            lowestSpend.ifPresent(transaction -> {
                System.out.println(transaction.amount);
            });
    }
}
