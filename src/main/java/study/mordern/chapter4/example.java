package study.mordern.chapter4;

import study.mordern.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class example {

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

        List<Person> people = Arrays.asList(
                new Person("personA", 24),
                new Person("personB", 26),
                new Person("personC", 28),
                new Person("personD", 30)
        );

        List<String> threeHighCaloricDishNames = menu.stream().filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName) // 스트림 반환
                .limit(3)  // 스트림 반환 -> 체이닝 가능 -> 중간연산
                .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);


        List<String> animals = Arrays.asList("cat", "dog");

        List<String> results = animals.stream().map(animal -> animal.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println("results = " + results);



        /*
        filter 테스트
        칼로리가 400보다 큰 값 필터링
         */
        List<Dish> filterResult = menu.stream()
                .filter(dish -> dish.getCalories() > 400)
                .collect(Collectors.toList());

        /*
        map 테스트
        채식주의자인 사람 이름만 매핑
         */
        List<String> mapResult = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName)
                .collect(Collectors.toList());

        /*
        limit 테스트
        위의 채식주의자 중 3명만 필터링
         */
        List<String> limitResult = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

        /*
        ditinct 테스트
        채소 중 중복 요소 제거
         */
        List<Integer> distinctResult = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getCalories)
                .distinct()
                .collect(Collectors.toList());
        /*
        skip 테스트
        위의 채식주의자 중 처음 3명 빼고 모두 필터링
         */
        List<String> skipResult = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName)
                .skip(3)
                .collect(Collectors.toList());

        /*
        anyMatch 테스트
        menu에서 채소가 하나라도 있으면 true 반환
         */
        boolean anyMatchResult = menu.stream()
                .anyMatch(Dish::isVegetarian);
        System.out.println("anyMatchResult = " + anyMatchResult);

          /*
        allMatch 테스트
        menu에서 전부 다 채소여야 된다.
         */
        boolean allMatchResult = menu.stream()
                .allMatch(Dish::isVegetarian);
        /*
        reduce 테스트
        스트림 내의 모든 요소에 대해서 순차적 연산을 진행해준다.
        채소의 칼로리 중 최대값을 구해보자
         */
        Optional<Integer> reduceResult = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getCalories)
                .reduce(Integer::max);

        Integer result = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("result = " + result);

    }
}
