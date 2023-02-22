package hw4;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class examplExplainingTheDifferenceOfLocators {
    //to play: put a breakpoint on System.out.println("This"; then turn on the test in debug mode.
    //Result: the second element will give: NoSuchElementException: no such element: Unable to locate element:
    // {"method":"css selector","selector":"h1"}.
    @Test
    void checkLocs(){
        open("https://ru.selenide.org/2018/01/12/selenide-4.10");
        SelenideElement el1 = $("div h1");
        SelenideElement el2 = $("div").$("h1");
        System.out.println("This");
    }
}
