package study.mordern.chapter11;

import java.util.Optional;

public class main {

    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.getWallet()); // 여기는 null값이 들어있으므로 ok
//        System.out.println(person.getWallet().getMoney()); // 여기는 null값 안에서 money를 찾으려했기에 nullPointException
        Optional<Wallet> wallet = Optional.of(person.getWallet()); // of 안에 들어오는 값이 null이면 즉시 에러발생
    }

    static class Person {
        private Wallet wallet;
        public Wallet getWallet()
        {
            return wallet;
        }
    }

    static class Wallet{
        private int money;
        public int getMoney()
        {
            return money;
        }
    }


}
