package hw4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class locationDesiredCodeOnTheSoftAssertionPage {

    String textToCheck = """
            @ExtendWith({SoftAssertsExtension.class})
             class Tests {
               @Test
               void test() {
                 Configuration.assertionMode = SOFT;
                 open("page.html");
             
                 $("#first").should(visible).click();
                 $("#second").should(visible).click();
               }
             }
            """;

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void junit5ShouldBeInsideSoftAssertionPage() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").click();
        $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();
        $(".filterable-active").shouldHave(text("SoftAssertions"));
        $(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:")
                        , text(textToCheck))
                .shouldBe(visible);
    }
}
