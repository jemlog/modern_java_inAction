package study.mordern.chapter6;

import study.mordern.Type;
import study.mordern.chapter4.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class running {

    public static void main(String[] args) {
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
        Long howManyDishes = menu.stream().collect(counting());

        /*
        특정 컬럼을 기준으로 값들을 그룹화 해서 분류 할 수 있다.
         */

        // 중요! 숫자 비교를 하고싶을때는 comparator라는걸 전달해줘야 한다!
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = menu.stream()
                .collect(maxBy(dishComparator));
        System.out.println(collect.get());

        IntSummaryStatistics collect1 = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("collect1 = " + collect1);

        String collect2 = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println("collect2 = " + collect2);

        // 내가 groupingBy 내부에 설정한 기준대로 grouping 가능하다!
        Map<Type, List<Dish>> results = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
        System.out.println("results = " + results);

        // 내가 원하는 인덱스로 분류를 할 수 있다.
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
        System.out.println(dishesByCaloricLevel);


    }
}
