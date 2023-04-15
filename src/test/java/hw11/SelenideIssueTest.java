package hw11;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Feature("Issue в репозитории")
@Story("Создание Issue")
@Owner("vasenkoea")
@Severity(SeverityLevel.BLOCKER)
public class SelenideIssueTest {
    private static final String REPOSITORY = "paranoidburnout/Github_Pages_Auto";
    private static final String ISSUE = "Allure";

    @Test
    @DisplayName("Поверка отображения Issue с помощью Listener")
    public void testIssueSearchWithListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE)).should(Condition.exist);
    }

    @Test
    @DisplayName("Поверка отображения Issue с помощью Lambda Steps")
    public void testIssueSearchWithLambdaStep() {
        step("Открываем главную страницу", () -> open("https://github.com"));
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> $(linkText(REPOSITORY)).click());
        step("Открываем таб Issues", () -> $("#issues-tab").click());
        step("Проверяем наличие Issue" + ISSUE, () -> {
            $(withText(ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Поверка отображения Issue с помощью Annotated Steps")
    public void testIssueSearchAnnotatedStep() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssue(ISSUE);
    }
}