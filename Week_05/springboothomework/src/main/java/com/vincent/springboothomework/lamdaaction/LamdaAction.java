package com.vincent.springboothomework.lamdaaction;

import com.vincent.springboothomework.jpapractice.PeopleDao;
import com.vincent.springboothomework.jpapractice.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class LamdaAction {
    @Autowired
    private PeopleDao peopleDao;

    public void lamdaCheck(int age) {
//        List<Userinfo> peopleByAge = peopleDao.findPropleByAge(age);
        List<Userinfo> peopleByAge = peopleDao.findAll();

        Optional<Userinfo> ub = Optional.of(peopleByAge.get(0));
        Optional<List<Userinfo>> ta = Optional.ofNullable(peopleByAge);
//        ta.
        ub.map(a -> {
            System.out.println(a.getPeopleName());
            return a.getPeopleName();
        }).orElse(null);
        System.out.println(ub.get().toString());
        Userinfo bb = ta.map(a -> {
            a.forEach(t -> System.out.println(t.getPeopleName()));
            return a.get(2);
        }).get();
        checkAndExecute(peopleByAge,
                p -> p.getAge() < 124,
                p -> System.out.println(p.toString()));

    }

    public static void checkAndExecute(List<Userinfo> userinfoList,
                                       Predicate<Userinfo> predicate,
                                       Consumer<Userinfo> consumer) {

        List<String> nameGroup = userinfoList.stream()
                .filter(predicate)
                .map(userinfo -> userinfo.getPeopleName())//将Userinfo的stream变成String的stream
                .filter(s->s.length() > 1)//name长度大于1的
                .collect(Collectors.toList());
        nameGroup.forEach(System.out::println);
        /**
         * 使用stream的map生成新的stream
         */
//        userinfoList.stream()
//                .filter(predicate)
//                .map(userinfo -> userinfo.getPeopleName())//将Userinfo的stream变成String的stream
//                .sorted()
//                .forEach(System.out::println);


        /**
         * 使用lambda stream的方式简化
         */
//        userinfoList.stream()
//                .filter(predicate)
//                .limit(2)
//                .skip(1)
//                .forEach(consumer);

        /**
         * 使用foreach lambda表达式的方式
         */
//        userinfoList.forEach(userinfo -> {
//            if (predicate.test(userinfo)) {
//                consumer.accept(userinfo);
//            }
//        });
        /**
         * 使用传统foreach方式
         */
//        for (Userinfo userinfo : userinfoList) {
//            if (predicate.test(userinfo)) {
//                consumer.accept(userinfo);
//            }
//        }
    }

}
