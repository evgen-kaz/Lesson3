import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

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
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $x("//*[@id='firstName']").setValue(firstName);
        $x("//*[@id='lastName']").setValue(lastName);
        $x("//*[@id='userEmail']").setValue(email);
        $x("//*[@for='gender-radio-2']").click();
        $x("//*[@id='userNumber']").setValue(phone);
        $x("//*[@id='dateOfBirthInput']").click();
        $x("//*[@class='react-datepicker__month-select']").click();
        $x("//*[.='December']").click();
        $x("//*[@class='react-datepicker__year-select']").click();
        $x("//*[.='1900']").click();
        $x("//*[@class='react-datepicker__week']//*[.='3']").click();
        $x("//*[@id='subjectsInput']").setValue("Physics").pressEnter();
        $x("//*[@for='hobbies-checkbox-2']").click();
        $x("//*[@id='uploadPicture']").uploadFromClasspath("cat.jfif");
        $x("//*[@id='currentAddress']").setValue(address);
        $x("//*[.='Select State']").click();
        $x("//*[.='Rajasthan']").click();
        $x("//*[.='Select City']").click();
        $x("//*[.='Jaipur']").click();
        $x("//*[@id='submit']").click();
        $x("//*[@class='modal-content']").shouldHave(text("Thanks for submitting the form"));

        $x("//*[contains(@class, 'table')]").shouldHave(text(firstName + " " + lastName));
        $x("//*[contains(@class, 'table')]").shouldHave(text(email));
        $x("//*[contains(@class, 'table')]").shouldHave(text("Female"));
        $x("//*[contains(@class, 'table')]").shouldHave(text(phone));
        $x("//*[contains(@class, 'table')]").shouldHave(text("03 December,1900"));
        $x("//*[contains(@class, 'table')]").shouldHave(text("Physics"));
        $x("//*[contains(@class, 'table')]").shouldHave(text("Reading"));
        $x("//*[contains(@class, 'table')]").shouldHave(text("cat.jfif"));
        $x("//*[contains(@class, 'table')]").shouldHave(text(address));
        $x("//*[contains(@class, 'table')]").shouldHave(text("Rajasthan Jaipur"));
    }
}