package study.mordern.chapter5;

import study.mordern.Type;
import study.mordern.chapter4.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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


        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 350, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );


        /*
        mapToInt라는 기본형 특화 스트림을 사용해야 reduce없이 sum 사용 가능
        다시 stream으로 복원하기 위해서는 boxed() 사용하면 된다.
         */
        Stream<Integer> boxed = menu.stream()
                .mapToInt(Dish::getCalories).boxed();

        OptionalInt optionalint = menu.stream()
                .mapToInt(Dish::getCalories).max();

        // 값이 null이면 디폴트로 1 나감
        int result = optionalint.orElse(1);

        // 내가 원하는 range의 숫자 리스트를 만드는 법
        // range는 양끝 미포함
        // rangeClosed는 양끝 포함
        IntStream istream = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(istream.boxed().collect(Collectors.toList()));

        // Stream.of 로 스트림 직접 만들기
        Stream.of("Modern", "Java", "In", "Action").map(String::toUpperCase)
                .forEach(System.out::println);
        Stream<Object> empty = Stream.empty(); // 빈 스트림
        int[] arr = {2,3,4,5};
        int sum = Arrays.stream(arr).sum();

        // 끝날때까지 계속 반복해서 돌린다!! 무한 스트림이라고 함
        Stream.iterate(0,n -> n < 50, n-> n+4)
                .forEach(System.out::println);

        // iterate는 연속해서 더해준다, generate는 비연속적으로 계산해줌
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }



}
