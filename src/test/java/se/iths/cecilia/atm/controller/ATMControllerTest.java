package se.iths.cecilia.atm.controller;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import se.iths.cecilia.atm.models.AccountComponent;
import se.iths.cecilia.atm.services.ATMService;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


//NOTE: Server must be open in order for the tests to be run
class ATMControllerTest {

    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chromium"));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }


    @Test
    @DisplayName("The page can be reached")
    void pageCanBeReached() {
        page.navigate("http://localhost:8080/");
        assertThat(page).hasTitle(Pattern.compile("Balance"));
    }

    @Test
    @DisplayName("The table is loaded correctly")
    void pageTableIsLoadedCorrectly() {
        page.navigate("http://localhost:8080/");
        Locator tableLocator = page.locator("#balance-table");
        assertThat(tableLocator).isVisible();
    }


    @Test
    @DisplayName("The amount displayed is correct")
    void amountDisplayedIsCorrect() {
        ATMService service = new ATMService(new AccountComponent());
        int expectedAmount = service.getBalance();
        page.navigate("http://localhost:8080/");

        Locator balanceLocator = page.locator("#balance");
        String balanceAsText = balanceLocator.textContent().trim();

        int balanceAsInt = Integer.parseInt(balanceAsText);

        Assertions.assertEquals(expectedAmount, balanceAsInt);

    }
}