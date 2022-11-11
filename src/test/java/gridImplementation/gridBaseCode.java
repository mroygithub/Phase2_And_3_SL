package gridImplementation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class gridBaseCode {

    public WebDriver initializeBrowser(String browserName) throws MalformedURLException {

        WebDriver driver = null;

        DesiredCapabilities dc = new DesiredCapabilities();

        if(browserName.equalsIgnoreCase("chrome")) {

            dc.setBrowserName("chrome");

        }else if(browserName.equalsIgnoreCase("firefox")) {

            dc.setBrowserName("firefox");

        }else if(browserName.equalsIgnoreCase("edge")) {

            dc.setBrowserName("MicrosoftEdge");

        }else if(browserName.equalsIgnoreCase("opera")) {

            dc.setBrowserName("opera");

        }else if(browserName.equalsIgnoreCase("ie")){

            dc.setBrowserName("internet explorer");

        }else if(browserName.equalsIgnoreCase("safari")) {

            dc.setBrowserName("safari");
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);

        return driver;

    }
}
