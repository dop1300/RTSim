package com.rtsim.ui.plaintext.inquirer;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public abstract class Inquirer {
 
    public abstract void display(String text);
    public abstract String readLine(String prompt);
    
    private Object inquire(String prompt, Predicate<Object> resultValidator, Predicate<String> inputValidator) {
        Object answer;
        do {
            display(prompt);
            String response = inquireString(prompt, inputValidator);
            answer = Integer.parseInt(response);
        } while(!resultValidator.test(answer));
        return answer;
    }

    public String inquireString(String prompt, Predicate<String> validator) {
        return (String) inquire(prompt, o -> validator.test((String) o), s -> true);
    }

    public float inquireFloat(String prompt, Predicate<Float> validator) {
        return (Float) inquire(prompt, o -> validator.test((Float) o), s -> Pattern.matches("\\d+\\.?\\d*", s));
    }

    public float inquireFloat(String prompt) {
        return inquireFloat(prompt, f -> true);
    }
    
    public int inquireInt(String prompt, Predicate<Integer> validator) {
        return (int) inquire(prompt, o -> validator.test((Integer) o), s -> Pattern.matches("\\d+", s));
    }

    public int inquireInt(String prompt) {
        return inquireInt(prompt, i -> true);
    }

}