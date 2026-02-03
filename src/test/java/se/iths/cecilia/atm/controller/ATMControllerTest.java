package se.iths.cecilia.atm.controller;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

class ATMControllerTest {

    // TODO: Läs på om playwright
    // TODO: Skriv testerna
    private static Page page;
    private static Browser browser;
    private static BrowserContext context;

    @BeforeAll
    static void launchBrowser() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
    }

    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = browser.newPage();
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    @DisplayName("The page can be reached")
    void pageCanBeReached() {
    }

    @Test
    @DisplayName("The page is loaded correctly")
    void pageIsLoadedCorrectly() {
        page.navigate("http://localhost:8080/");

    }

    @Test
    @DisplayName("The amount displayed is correct")
    void amountDisplayedIsCorrect() {

    }
}