package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Links {

    public static void runScraper(String boardLink, WebDriver driver) {
        if (!isValidBoardLink(boardLink)) {
            System.out.println("Invalid board link format. It should be like: https://boards.4channel.org/boardname/catalog");
            return;
        }


        String url = boardLink;

        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("thread")));

        String pageSource = driver.getPageSource();

        Document doc = Jsoup.parse(pageSource);

        Elements threadDivs = doc.select("div.thread");

        Set<String> uniqueLinks = new HashSet<>();

        for (Element div : threadDivs) {
            Element link = div.select("a[href]").first();
            if (link != null) {
                String href = link.attr("href");
                if (href.startsWith("//")) {
                    href = "https:" + href;
                }
                uniqueLinks.add(href);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("4chan_links.txt"))) {
            for (String link : uniqueLinks) {
                writer.write(link + "\n");
            }
            System.out.println("Links gathered.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static boolean isValidBoardLink(String boardLink) {
        return boardLink.matches("^https://boards\\.4channel\\.org/\\w+/catalog$");
    }
}
