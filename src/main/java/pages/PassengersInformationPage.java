package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class PassengersInformationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//li[@class='ts-pb__step ts-pb__step--current']//span[@class='ts-pb__label']")
    private WebElement passengersTab;

    @FindBy(xpath = "//span[@class='passengerSummary']")
    private WebElement numberOfPassenger;

    @FindBy(xpath = "//div[@class='ts-suggested is-visible']//li")
    private List<WebElement> listOfTitles;

    @FindBy(xpath = "//div[@class='airline-programme-wrapper']//input[@class='width-510']")
    private WebElement countryKey;

    @FindBy(xpath = "//div[@class='airline-programme-wrapper']//input[@class='restrict-number unsetmariginPad width-510']")
    private WebElement mobileNumber;

    @FindBy(xpath = "//div[@class='ts-form padding-top-10 float']//input[@class='width-510 unsetmariginPad']")
    private WebElement emailAddress;

    @FindBy(xpath = "//a[@class='ts-sc__trigger ts-sc__trigger--mxw-ext']")
    private WebElement summaryBtn;

    @FindBy(xpath = "//p[@class='ts-sc__total ts-sc__total--right sbox-flight-total']//strong")
    private WebElement totalPriceFromSummaryPopUp;

    @FindBy(xpath = "//strong[@class='sbox-totalfare']")
    private WebElement totalCostPrice;

    public PassengersInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String getPassengersTabText() {
        wait.until(ExpectedConditions.visibilityOf(passengersTab));
//        System.out.println(passengersTab.getText());
        return passengersTab.getText();
    }

    // ************************************************************
    // Method to generate dynamic XPath for passenger details
    public WebElement getPassengerInput(int index, String inputType) {
        String dynamicXPath = String.format("//div[@class='passenger-details-container passenger-details'][%d]//input[@class='%s']", index, inputType);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));
    }

    public WebElement submitPassenger(int index, String buttonType) {
        String dynamicXPath = String.format("//div[@class='passenger-details-container passenger-details'][%d]//a[@class='%s']", index, buttonType);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));
    }

    public void fillPassengerDetails(int index, String titleDropdown, String firstName, String lastName, String dayOfBirth, String monthOfBirth, String yearOfBirth, String airlineProgram, String frequentFlyerNumber) {
        // Locate the input fields dynamically
        WebElement titleDropdownInput = getPassengerInput(index, "width-232 no-margin texttitle");
        WebElement firstNameInput = getPassengerInput(index, "width-510 unsetmariginPad first-name");
        WebElement lastNameInput = getPassengerInput(index, "width-510 unsetmariginPad margin-left-20 last-name");
        WebElement dayOfBirthInput = getPassengerInput(index, "width-115 no-margin bd-day");
        WebElement monthOfBirthInput = getPassengerInput(index, "width-166 no-margin bd-month");
        WebElement yearOfBirthInput = getPassengerInput(index, "width-228 no-margin bd-year");
        WebElement airlineProgramInput = getPassengerInput(index, "width-510 no-margin");
        WebElement frequentFlyerNumberInput = getPassengerInput(index, "width-510 unsetmariginPad restrict-number' and ./@aria-label='Frequent flyer number");
        WebElement submit = submitPassenger(index, "pax-info-link collapse passenger-details-next ts-pax-btn-edit");


        titleDropdownInput.sendKeys(titleDropdown, Keys.ENTER);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        dayOfBirthInput.sendKeys(dayOfBirth, Keys.ENTER);
        monthOfBirthInput.sendKeys(monthOfBirth, Keys.ENTER);
        yearOfBirthInput.sendKeys(yearOfBirth, Keys.ENTER);
        airlineProgramInput.sendKeys(airlineProgram, Keys.ENTER);
        frequentFlyerNumberInput.sendKeys(frequentFlyerNumber);
        submit.click();
    }
    //***********************************************


    public int getPassengersNumber() {
        wait.until(ExpectedConditions.visibilityOf(numberOfPassenger));
//        System.out.println(numberOfPassenger.getText());
        return Integer.parseInt(numberOfPassenger.getText().replaceAll("[^0-9]", ""));
    }

    public void sendKeysCountryKey(String key) {
        wait.until(ExpectedConditions.visibilityOf(countryKey));
        countryKey.sendKeys(key);
        countryKey.sendKeys(Keys.ENTER);
    }

    public void sendKeysMobileNumber(String number) {
        wait.until(ExpectedConditions.visibilityOf(mobileNumber));
        mobileNumber.sendKeys(number);
    }

    public void sendKeysEmailAddress(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailAddress));
        mobileNumber.sendKeys(email);
    }

    public void clickOnSummaryButton() {
        wait.until(ExpectedConditions.visibilityOf(summaryBtn));
        summaryBtn.click();
    }

    public String getTotalPriceTextFromPopUpSummary() {
        wait.until(ExpectedConditions.visibilityOf(totalPriceFromSummaryPopUp));
//        System.out.println(totalPriceFromSummaryPopUp.getText());
        return totalPriceFromSummaryPopUp.getText();
    }

    public String getTotalCostPriceTextFromPassengerPage() {
        wait.until(ExpectedConditions.visibilityOf(totalCostPrice));
//        System.out.println(totalCostPrice.getText());
        return totalCostPrice.getText();
    }

}