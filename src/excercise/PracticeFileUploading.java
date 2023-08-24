package excercise;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeFileUploading {

	public static void main(String[] args) throws IOException, InterruptedException {
		String DownloadPath = System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Desktop\\Drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", DownloadPath);

		options.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://www.sodapdf.com/pdf-to-jpg/");

		driver.findElement(By.cssSelector("label[class='btn-choose']")).click();
		Thread.sleep(10);
		Runtime.getRuntime().exec("C:\\Users\\hp\\Documents\\practicefileupload.exe");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='btn-base']")));

		driver.findElement(By.cssSelector("a[class='btn-base']")).click();

		File f = new File(DownloadPath + "sodapdf-converted.zip");

		if (f.exists()) {
			System.out.println("file found");
		}
		if (f.delete()) {
			System.out.println("file deleted");
		}
	}

}
