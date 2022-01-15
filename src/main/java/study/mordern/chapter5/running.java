package study.mordern.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class running {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> a1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println("a1 = " + a1);

        List<String> a2 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("a2 = " + a2);

        List<Trader> a3 = transactions.stream()
                .map(t -> t.getTrader())
                .filter(m -> m.getCity() == "Cambridge")
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("a3 = " + a3);

        List<String> a4 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("a4 = " + a4);

        boolean a5 = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity() == "Milan");
        System.out.println("a5 = " + a5);

        List<Integer> a6 = transactions.stream()
                .filter(t -> t.getTrader().getCity() == "Cambridge")
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println("a5 = " + a6);

        Integer a7 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);

        System.out.println("a7 = " + a7);

        Optional<Integer> a8 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println("a8 = " + a8.get());

    }


}
