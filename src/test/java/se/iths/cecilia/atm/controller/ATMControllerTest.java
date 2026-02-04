package se.iths.cecilia.atm.controller;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import se.iths.cecilia.atm.models.AccountComponent;
import se.iths.cecilia.atm.services.ATMService;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class ATMControllerTest {


    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    // TODO: Läs på om playwright
    // TODO: Skriv testerna


    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
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
    @DisplayName("The root-page can be reached")
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