package study.mordern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class example {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork",false,800,Type.MEAT),
                new Dish("beef",false,700,Type.MEAT),
                new Dish("chicken",false,400,Type.MEAT),
                new Dish("french fries",true,530,Type.OTHER),
                new Dish("rice",true,350,Type.OTHER),
                new Dish("season",true,120,Type.OTHER),
                new Dish("pizza",true,550,Type.OTHER),
                new Dish("prawns",false,300,Type.FISH),
                new Dish("salmon",false,450,Type.FISH)
        );
        List<String> threeHighCaloricDishNames =  menu.stream()
                                                        .filter(dish -> dish.getCalories() > 300)
                                                        .map(Dish::getName)
                                                        .limit(3)
                                                        .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);

    }
}
