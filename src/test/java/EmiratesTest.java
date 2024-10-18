import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.EmiratesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.FlightsPage;
import pages.PassengersInformationPage;

public class EmiratesTest {

    private WebDriver driver;
    private EmiratesPage emiratesPage;
    private PassengersInformationPage passengersInformationPage;
    FlightsPage flightsPage;
    SoftAssert softAssert;
    JavascriptExecutor js;


    @BeforeTest
    public void setUp() {
        softAssert = new SoftAssert();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.get("https://www.emirates.com");
        emiratesPage = new EmiratesPage(driver);
        passengersInformationPage = new PassengersInformationPage(driver);
        flightsPage = new FlightsPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void testBookingProcess() {
        //*************************** Start Emirates page **********************************
        emiratesPage.clickOnAcceptCookies();

        emiratesPage.clickOnDepartureAirport();
        emiratesPage.clickOnClearBtn();

        emiratesPage.sendKeysDepartureAirport("Riyadh");
        emiratesPage.clickOnRiyadhDepartureAirport();

        emiratesPage.sendKeysArrivalAirport("Amman");
        emiratesPage.clickOnAmmanDepartureAirport();

        emiratesPage.clickOnCalenderNextBtn();
        emiratesPage.clickOnCalenderNextBtn();

        emiratesPage.clickOnDepartureDateInput();

        emiratesPage.clickOnReturnDateInput();

        emiratesPage.clickOnIncAdultsBtn();

        emiratesPage.clickOnIncChildBtn();

        emiratesPage.clickOnClass();

        emiratesPage.selectClass("Business Class");

        emiratesPage.clickOnSearchBtn();

        softAssert.assertEquals(flightsPage.getSiteCountryText(), "You have been taken to our Saudi Arabia site");
        js.executeScript("window.scrollBy(0,350)", "");
        //*************************** End Emirates page **********************************

        //*************************** Start Flights page **********************************
        flightsPage.clickOnPriceFilterFromToBtn();

        js.executeScript("window.scrollBy(0,400)", "");

        flightsPage.clickOnLowestPriceFromToBtn();
        int totalPrice = flightsPage.getPriceFromToSelectedIntegerValue();
        flightsPage.clickOnSelectLowestPriceServicesBtn();

        flightsPage.clickOnPriceFilterToFromBtn();

        flightsPage.clickOnLowestPriceToFromBtn();
        totalPrice += flightsPage.getPriceToFromSelectedIntegerValue();
        flightsPage.clickOnSelectLowestPriceServicesBtn();

        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("Actual : "+ totalPrice);
        System.out.println("Expected : "+flightsPage.getTotalLowestPriceIntegerValue());
        System.out.println("+++++++++++++++++++++++++++++");
        softAssert.assertEquals(flightsPage.getTotalLowestPriceIntegerValue(), totalPrice);

        flightsPage.clickOnContinueToPassengersBtn();

        //*************************** End Flights page **********************************

        //*************************** Start Information page **********************************
        softAssert.assertEquals(passengersInformationPage.getPassengersTabText(), "Passengers");
        int numberOfPassengers = passengersInformationPage.getPassengersNumber();
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("Number Of Passengers : " + numberOfPassengers);
        System.out.println("+++++++++++++++++++++++++++++");

        js.executeScript("window.scrollBy(350,500)", "");

        for (int i = 1; i <= numberOfPassengers; i++) {
            // this data for testing
            passengersInformationPage.fillPassengerDetails(i, "Mr", "Laith", "Zawaneh", "10", "October", "2010", "Emirates & flydubai / Skywards", "369852147");
        }

        passengersInformationPage.sendKeysCountryKey("jo");
        passengersInformationPage.sendKeysMobileNumber("791750307");
        passengersInformationPage.sendKeysEmailAddress("laithza@gmail.com");
        passengersInformationPage.clickOnSummaryButton();

        softAssert.assertEquals(passengersInformationPage.getTotalPriceTextFromPopUpSummary(), passengersInformationPage.getTotalCostPriceTextFromPassengerPage());
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("Price From PopUp : " + passengersInformationPage.getTotalPriceTextFromPopUpSummary());
        System.out.println("Price From Passenger Page : " + passengersInformationPage.getTotalCostPriceTextFromPassengerPage());
        System.out.println("+++++++++++++++++++++++++++++");
        //*************************** End Information page **********************************

        softAssert.assertAll();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}