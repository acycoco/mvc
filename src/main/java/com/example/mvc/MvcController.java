package com.example.mvc;

import com.example.mvc.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MvcController {
    private int hitCount = 0; //메소드 내부에 변수를 만들면 계속 바뀌어서 의미가 없음

    private List<int[]> historyOfNumbers = new ArrayList<>();


    @RequestMapping("/hits")
    public String hits(Model model) {
        model.addAttribute("hits", ++hitCount);
        return "hits";
    }

    @RequestMapping("/lotto")
    public String lotto(Model model) {
        int[] randomNums = new int[6];
        for (int i = 0; i < randomNums.length; i++) {
            int random = (int) (Math.random() * 45) + 1;
            if (i > 0) {
                boolean diff = false;
                while (!diff) {
                    random = (int) (Math.random() * 45) + 1;
                    for (int j = 0; j < i; j++) {
                        if (randomNums[j] == random) {
                            break;
                        } else if (i - 1 == j) {
                            diff = true;
                        }
                    }
                }
            }
            randomNums[i] = random;
        }
        historyOfNumbers.add(randomNums);
        model.addAttribute("randomNums", randomNums);
        return "lotto";

    }

    @RequestMapping("/history")
    public String history(Model model) {

        model.addAttribute("historyCount", historyOfNumbers.isEmpty());
        model.addAttribute("historyOfNumbers", historyOfNumbers);
        return "history";
    }


    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("message",
                "Hello, Thymeleaf!");
        return "home";
    }

    @RequestMapping("/student")
    public String student(Model model) {//객체를 모델로 전달
        model.addAttribute("object",
                new Student("chaeyeon Ahn", "skjg@naver.com"));
        return "student";
    }

    @RequestMapping("/is-logged-in")
    public String isLoggendIn(Model model) {
        model.addAttribute(
                "isLoggedIn",
//                true
                false
        );
        return "if-unless";
    }

    @RequestMapping("/each")
    public String items(Model model) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("foo");
        listOfStrings.add("bar");
        listOfStrings.add("baz");

        model.addAttribute("listOfStrings",
                listOfStrings);

        List<Student> studentList = Arrays.asList(
                new Student("Alex", "alex@gmail.com"),
                new Student("Brad", "brad@gmail.com"),
                new Student("Chad", "chad@gmail.com")
        );

        model.addAttribute("studentList", studentList);
        return "each";
    }


}
