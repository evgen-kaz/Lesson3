import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Lesson3 {
    public String email = "AnnaIvanova@test.ru";
    public String phone = "1234567890";
    public String address = "USA";
    public String firstName = "Anna";
    public String lastName = "Ivanova";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").$(byText("December")).click();
        $(".react-datepicker__year-select").$(byText("1900")).click();
        $(".react-datepicker__day--003").click();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("cat.jfif");
        $("#currentAddress").setValue(address);
        $(byText("Select State")).click();
        $(byText("Rajasthan")).click();
        $(byText("Select City")).click();
        $(byText("Jaipur")).click();
        $("#submit").click();
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));

        $(".table").shouldHave(text(firstName + " " + lastName));
        $(".table").shouldHave(text(email));
        $(".table").shouldHave(text("Female"));
        $(".table").shouldHave(text(phone));
        $(".table").shouldHave(text("03 December,1900"));
        $(".table").shouldHave(text("Physics"));
        $(".table").shouldHave(text("Reading"));
        $(".table").shouldHave(text("cat.jfif"));
        $(".table").shouldHave(text(address));
        $(".table").shouldHave(text("Rajasthan Jaipur"));
    }
}