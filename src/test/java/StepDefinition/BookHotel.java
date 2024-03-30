package StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class BookHotel {
    WebDriver driver;

    @Given("a user is on the Adactin Hotel App login page")
    public void user_is_on_the_Adactin_Hotel_App_login_page() {

        //hey
        driver = new ChromeDriver();
        driver.get("https://adactinhotelapp.com/index.php");
    }

    @When("the user enters a valid username and password")
    public void user_enters_valid_username_and_password() {
        String username = "";
        String password = "";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/Database/hotelDetails.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM hotelDetails WHERE ID=1;");

            while (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

    }

    @And("the user is on the hotel search page")
    public void theUserIsOnTheHotelSearchPage() {
        WebElement searchHotelPage = driver.findElement(By.xpath( "//td[@class='login_title']"));
        //td[@class='login_title']

        Assert.assertTrue(searchHotelPage.isDisplayed());
    }

    @When("the user enters the following search criteria")
    public void theUserEntersTheFollowingSearchCriteria() {

        String location = "", hotels = "", roomType = "", roomNos = "", datepickIn = "", datepickOut = "", adultRoom = "", childRoom = "";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/Database/hotelDetails.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM hotelDetails WHERE ID=1;");

            while (rs.next()) {
                location = rs.getString("Location");
                hotels = rs.getString("Hotel");
                roomType = rs.getString("RoomType");
                roomNos = rs.getString("NumberOfRooms");
                datepickIn = rs.getString("CheckInDate");
                datepickOut = rs.getString("CheckOutDate");
                adultRoom = rs.getString("AdultsPerRoom");
                childRoom = rs.getString("ChildrenPerRoom");
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        driver.findElement(By.id("location")).sendKeys(location);
        driver.findElement(By.id("hotels")).sendKeys(hotels);
        driver.findElement(By.id("room_type")).sendKeys(roomType);
        driver.findElement(By.id("room_nos")).sendKeys(roomNos);
        driver.findElement(By.id("datepick_in")).sendKeys(datepickIn);
        driver.findElement(By.id("datepick_out")).sendKeys(datepickOut);
        driver.findElement(By.id("adult_room")).sendKeys(adultRoom);
        driver.findElement(By.id("child_room")).sendKeys(childRoom);
    }


    @When("clicks on the Search button")
    public void clicks_on_the_Search_button() {
        driver.findElement(By.id("Submit")).click();

    }

    @And("user should be directed to the select  hotel page")
    public void userShouldBeDirectedToTheSelectHotelPage() {

        WebElement searchHotelMessage = driver.findElement(By.xpath( "//td[@class='login_title']"));
        Assert.assertTrue(searchHotelMessage.isDisplayed());
    }

    @When("the user selects the hotel")
    public void user_selects_the_hotel() {
        driver.findElement(By.id("radiobutton_0")).click();

    }

    @Then("user clicks continue button")
    public void user_clicks_continue_button() {
        driver.findElement(By.id("continue")).click();
    }

    @Then("the user should see details of the selected hotel")
    public void user_should_see_details_of_the_selected_hotel() {

        WebElement searchHotel = driver.findElement(By.xpath( "//td[@class='login_title']"));
        Assert.assertTrue(searchHotel.isDisplayed());
    }

    @Then("the user should see details to book a hotel")
    public void user_should_see_details_to_book_a_hotel() {
        // Add assertion here to verify that the details of the selected hotel are displayed
        WebElement bookAhotelPage = driver.findElement(By.xpath( "//td[normalize-space()='Book A Hotel']"));

        Assert.assertTrue(bookAhotelPage.isDisplayed());
    }

    @And("the user enters the following booking details")
    public void theUserEntersTheFollowingBookingDetails() {
        String firstName = "", lastName = "", address = "", ccNum = "", ccType = "", ccExpMonth = "", ccExpYear = "", ccCvv = "";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/Database/hotelDetails.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM hotelDetails WHERE ID=1;");

            while (rs.next()) {
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                address = rs.getString("BillingAddress");
                ccNum = rs.getString("CreditCardNo");
                ccType = rs.getString("CreditCardType");
                ccExpMonth = rs.getString("ExpiryMonth");
                ccExpYear = rs.getString("ExpiryYear");
                ccCvv = rs.getString("CVVNumber");
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        driver.findElement(By.id("first_name")).sendKeys(firstName);
        driver.findElement(By.id("last_name")).sendKeys(lastName);
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("cc_num")).sendKeys(ccNum);
        driver.findElement(By.id("cc_type")).sendKeys(ccType);
        driver.findElement(By.id("cc_exp_month")).sendKeys(ccExpMonth);
        driver.findElement(By.id("cc_exp_year")).sendKeys(ccExpYear);
        driver.findElement(By.id("cc_cvv")).sendKeys(ccCvv);
    }

    @Then("the booking should be successful, and the user receives a confirmation message")
    public void the_booking_should_be_successful_and_the_user_receives_a_confirmation_message() {

        WebElement confirmationMessage = driver.findElement(By.xpath( "//td[@class='login_title']"));
        Assert.assertTrue(confirmationMessage.isDisplayed());
    }

    @And("clicks on the Book Now button")
    public void clicksOnTheBookNowButton() {
        driver.findElement(By.id("book_now")).click();
    }

    @And("the user clicks on the Logout button")
    public void theUserClicksOnTheLogoutButton() {
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/a[4]")).click();
    }

    @Then("the user should be logged out successfully")
    public void the_user_should_be_logged_out_successfully() {

        WebElement logoutMessage = driver.findElement(By.xpath( "//td[@class='reg_success']"));
        Assert.assertTrue(logoutMessage.isDisplayed());
    }

}



