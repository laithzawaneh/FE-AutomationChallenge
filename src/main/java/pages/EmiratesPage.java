package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EmiratesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//button[contains(.,'Accept') or contains(., 'موافق')]")
    private WebElement acceptCookiesBtn;

    @FindBy(xpath = "//div[@class='grid__item']//div[@class='grid__item medium--one-half' and .//label[contains(text(),'Departure airport')]]")
    private WebElement departureAirport;

    @FindBy(xpath = "//div[@class='grid__item']//div[@class='grid__item medium--one-half' and .//label[contains(text(),'Departure airport')]]//input[@name='Departure airport']")
    private WebElement inputDepartureAirport;


    @FindBy(xpath = "//div[@class='grid__item medium--one-half' and .//label[contains(text(),'Departure airport')]]//span[contains(text(),'Riyadh')]")
    private WebElement riyadhDepartureAirport;

    @FindBy(xpath = "//div[@class='grid__item']//div[@class='grid__item medium--one-half' and .//label[contains(text(),'Departure airport')]]//li")
    private List<WebElement> listDepartureAirport;


    @FindBy(xpath = "//div[@class='grid__item']//div[@class='grid__item medium--one-half' and .//label[contains(text(),'Arrival airport')]]")
    private WebElement arrivalAirport;

    @FindBy(xpath = "//div[@class='grid__item']//div[@class='grid__item medium--one-half' and .//label[contains(text(),'Arrival airport')]]//input[@name='Arrival airport']")
    private WebElement inputArrivalAirport;

    @FindBy(xpath = "//div[@class='grid__item medium--one-half' and .//label[contains(text(),'Arrival airport')]]//span[contains(text(),'Amman')]")
    private WebElement ammanArrivalAirport;

    @FindBy(xpath = "//div[@class='grid__item']//div[@class='grid__item medium--one-half' and .//label[contains(text(),'Departure airport')]]//li")
    private List<WebElement> listArrivalAirport;

    @FindBy(xpath = "//div[@class='grid__item']//button[@name='clear Departure airport']")
    private WebElement clearBtn;

    @FindBy(xpath = "//div[@class='textfield__date textfield__date--first textfield__date--active' and .//label[contains(text(),'Departing')]]")
    private WebElement departing;

    @FindBy(xpath = "//div[@class='textfield__date textfield__date--second' and .//span[contains(text(),'Returning')]]")
    private WebElement returning;

    @FindBy(xpath = "//div[@class='dropdown__link js-select-body js-location-link']")
    private List<WebElement> cities;

    @FindBy(xpath = "//td[@data-month='7'and @data-date='18']")
    private WebElement departureDateInput;

    @FindBy(xpath = "//td[@data-month='7'and @data-date='29']")
    private WebElement returnDateInput;

    @FindBy(xpath = "//*[@id='search-flight-control']//button[@class='ek-datepicker__button ek-datepicker__button--next icon-arrow-right']")
    private WebElement nextButton;

    @FindBy(xpath = "//div[@class='increment-field js-increment-field increment-field--subtract-disabled']//button[@class='js-increment-increase increment-field__button increment-field__increase']")
    private WebElement adultsButton;

    @FindBy(xpath = "//section[@class='location passenger-container']//div[@class='increment-field increment-field--subtract-disabled js-increment-field' and @data-type='children']//button[@class='js-increment-increase increment-field__button increment-field__increase']")
    private WebElement childButton;

    @FindBy(xpath = "//*[@id=\"search-flight-control\"]//section[@class='location class-dropdown']//a")
    private List<WebElement> listOfClass;

    @FindBy(xpath = "//div[@class='grid__item']//div[@class='grid__item medium--one-third search-flight__class' and .//label[contains(text(),'Class')]]")
    private WebElement clickOnClass;

    @FindBy(xpath = "//div[@class='grid__item medium--one-third']//button[@type='submit' and .//span[contains(text(),'Search flights')]]")
    private WebElement searchButton;


    public EmiratesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 50);
        PageFactory.initElements(driver, this);
    }

    public void clickOnAcceptCookies() {
        wait.until(ExpectedConditions.visibilityOf(acceptCookiesBtn));
        acceptCookiesBtn.click();
    }

    public void clickOnClearBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(clearBtn));
        clearBtn.click();
    }

    public void clickOnDepartureAirport() {
        wait.until(ExpectedConditions.elementToBeClickable(departureAirport));
        departureAirport.click();
    }

    public void sendKeysDepartureAirport(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(inputDepartureAirport));
        inputDepartureAirport.sendKeys(city);
        ;
    }

    public void clickOnRiyadhDepartureAirport() {
        wait.until(ExpectedConditions.elementToBeClickable(riyadhDepartureAirport));
        riyadhDepartureAirport.click();
    }

    public void clickOnArrivalAirport() {
        wait.until(ExpectedConditions.visibilityOf(arrivalAirport));
        arrivalAirport.click();
    }

    public void sendKeysArrivalAirport(String city) {
        wait.until(ExpectedConditions.visibilityOf(inputArrivalAirport));
        inputArrivalAirport.sendKeys(city);
    }

    public void clickOnAmmanDepartureAirport() {
        retryingFindClick(ammanArrivalAirport);
    }

    private void retryingFindClick(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
//                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 3) {
                    throw e;  // rethrow the exception after max attempts
                }
            }
        }
    }

    public void clickOnDepartureDateInput() {
        wait.until(ExpectedConditions.visibilityOf(departureDateInput));
        departureDateInput.click();
    }

    public void clickOnReturnDateInput() {
        wait.until(ExpectedConditions.visibilityOf(returnDateInput));
        returnDateInput.click();
    }

    public void clickOnCalenderNextBtn() {
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();
    }

    public void clickOnIncAdultsBtn() {
        wait.until(ExpectedConditions.visibilityOf(adultsButton));
        adultsButton.click();
    }

    public void clickOnIncChildBtn() {
        wait.until(ExpectedConditions.visibilityOf(childButton));
        childButton.click();
    }

    public void clickOnClass() {
        wait.until(ExpectedConditions.visibilityOf(clickOnClass));
        clickOnClass.click();
    }

    public void selectClass(String option) {
        for (WebElement webElement : listOfClass) {
            if (webElement.getText().contains(option)) {
                webElement.click();
//               System.out.println("clicked");
                break;
            }
        }
    }

    public void clickOnSearchBtn() {
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }
//    public void selectDepartureAirport(String option) {
////        wait.until(ExpectedConditions.visibilityOfAllElements(listDepartureAirport));
//        for (WebElement webElement : listDepartureAirport) {
//            if (webElement.getText().contains(option)) {
//                wait.until(ExpectedConditions.elementToBeClickable(webElement));
//                webElement.click();
//               System.out.println("clicked");
//                break;
//            }
//        }
//    }


//    public void selectOneArrivalAirport(String option) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(listArrivalAirport));
//        for (WebElement webElement : listArrivalAirport) {
//            if (webElement.getText().contains(option)) {
//                webElement.click();
////               System.out.println("clicked");
//                break;
//            }
//        }
//    }


}
