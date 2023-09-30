package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Entire Board Download");
            System.out.println("2. Single Thread Download");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Enter the board link you want to scrape (e.g., https://boards.4chan.org/boardname/catalog):");
                String boardLink = scanner.next();
                try {
                    Links.runScraper(boardLink, driver);
                    ImageDownloader.runScraper(driver);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (choice == 2) {
                System.out.println("Enter the thread link you want to scrape (e.g., https://boards.4chan.org/boardname/thread/1234567):");
                String threadLink = scanner.next();
                try {
                    ImageDownloader.downloadFromThread(driver, threadLink);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (choice == 3) {
                System.out.println("Exiting the program.");
                driver.quit();
                break;
            } else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }

            System.out.println("\n".repeat(5));
        }
    }
}
