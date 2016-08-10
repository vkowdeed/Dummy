package pardot;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestPardot {

	public static void main(String[] args) {
		// System.setProperty("webdriver.gecko.driver",
		// "C:\\Work\\2011\\geckodriver-v0.10.0-win64\\geckodriver.exe");
		//
		// WebDriver driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver",
				"C:\\Work\\2011\\chromedriver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		String url = "https://pi.pardot.com";

		driver.get(url);

		WebElement email = driver.findElement(By.id("email_address"));
		email.clear();
		email.sendKeys("pardot.applicant@pardot.com");

		WebElement password = driver.findElement(By.name("password"));
		password.clear();
		password.sendKeys("Applicant2012");

		WebElement submit = driver.findElement(By.name("commit"));
		submit.click();

		driver.get("https://pi.pardot.com/list");
		WebElement addList = driver.findElement(By.id("listxistx_link_create"));
		addList.click();

		WebElement name = driver.findElement(By.id("name"));
		name.clear();
		name.sendKeys(RandomStringUtils.randomAlphanumeric(15));

		WebElement createList = driver.findElement(By.id("save_information"));
		createList.click();

		driver.get("https://pi.pardot.com/list");
		driver.findElement(By.id("listxistx_link_create"));
		addList.click();

		driver.findElement(By.id("name"));
		name.clear();
		name.sendKeys("testTest123");

		driver.findElement(By.id("save_information"));
		createList.click();

		WebElement errorMessage = driver.findElement(By.id("error_for_name"));
		String style = errorMessage.getAttribute("style");

		if (style.length() == 0) {
			System.out.println("System Validated the error message correctly");
		}

		driver.get("https://pi.pardot.com/prospect");
		WebElement prospect = driver.findElement(By.id("pr_link_create"));
		prospect.click();

		WebElement pemail = driver.findElement(By.id("email"));
		pemail.clear();
		pemail.sendKeys(RandomStringUtils.randomAlphanumeric(10) + "@"
				+ RandomStringUtils.randomAlphanumeric(10) + ".com");

		Select campaign = new Select(driver.findElement(By.id("campaign_id")));

		campaign.selectByIndex(3);

		Select dropdownProfile = new Select(driver.findElement(By.id("profile_id")));
		dropdownProfile
				.selectByVisibleText(dropdownProfile.getOptions().get(2).getText());

		Select assign = new Select(driver.findElement(By.id("to")));
		assign.getOptions().get(1).click();

		WebElement listToggle = driver.findElement(By.id("toggle-inputs-lists-"));
		listToggle.click();

		WebElement dropdownList = driver.findElement(By.id("pr_fields_lists_wrapper_"));
		WebElement spanTag = dropdownList.findElement(By.tagName("span"));
		spanTag.click();

		WebElement listDropdownTextbox = dropdownList.findElement(By.tagName("input"));
		listDropdownTextbox.sendKeys("test1234");
		listDropdownTextbox.sendKeys(Keys.ENTER);

		WebElement createProspect = driver.findElement(By.name("commit"));
		createProspect.click();

		String savedProspect = driver.getCurrentUrl();
		if (savedProspect.startsWith("https://pi.pardot.com/prospect/read/id/")) {
			System.out.println("Prospect Saved Successfully");

		}
		else {
			System.out.println("Error saving new prospect");
		}

		driver.get("https://pi.pardot.com/email/draft/edit");
		WebElement eName = driver.findElement(By.id("name"));
		eName.sendKeys(RandomStringUtils.randomAlphabetic(10));

		driver.findElement(By.className("asset-chooser")).click();

		List<WebElement> div = driver.findElements(By
				.xpath("//*[starts-with(@id,'ember')]"));

		div.get(0).findElement(By.tagName("div")).click();

		WebElement eChooseSelected = driver.findElement(By.id("select-asset"));
		eChooseSelected.click();

		WebElement eText = driver.findElement(By.id("email_type_text_only"));
		eText.click();

		WebElement eTemplate = driver.findElement(By.id("from_template"));
		eTemplate.click();

		WebElement emailSave = driver.findElement(By.id("save_information"));
		emailSave.click();

		driver.get("https://pi.pardot.com/user/logout");

		driver.close();
		driver.quit();

		// TODO Auto-generated method stub

	}

}
