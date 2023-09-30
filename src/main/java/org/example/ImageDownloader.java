package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImageDownloader {


    public static void runScraper(WebDriver driver) throws IOException, InterruptedException {
        Set<String> links = readUrlsFromFile("4chan_links.txt");
        for (String link : links) {
            downloadFromThread(driver, link);
        }
        driver.quit();
    }

    public static void downloadFromThread(WebDriver driver, String link) throws IOException, InterruptedException {
        String threadId = parseThreadId(link);
        if (threadId == null) {
            System.out.println("Failed to parse thread ID from link: " + link);
            return;
        }
        driver.get(link);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        List<WebElement> fileThumbs = driver.findElements(By.cssSelector("a.fileThumb"));
        String downloadFolder = "downloaded_files/" + threadId;
        new File(downloadFolder).mkdirs();
        Set<String> downloadedFiles = new HashSet<>(Files.list(Path.of(downloadFolder))
                .map(Path::getFileName)
                .map(Path::toString)
                .toList());

        for (WebElement fileThumb : fileThumbs) {
            String fileURL = fileThumb.getAttribute("href");

            if (fileURL.isEmpty()) {
                continue;
            }


            fileURL = fileURL.replace("https://is2.4chan.org", "https://i.4cdn.org");

            String fileName = downloadFolder + "/" + fileURL.substring(fileURL.lastIndexOf('/') + 1);
            if (downloadedFiles.contains(new File(fileName).getName())) {
                continue;
            }
            try {
                URL url = new URL(fileURL);
                try (InputStream in = url.openStream()) {
                    Files.copy(in, Paths.get(fileName));

                    downloadedFiles.add(new File(fileName).getName());
                    System.out.println("File downloaded successfully: " + fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String parseThreadId(String link) {
        String[] parts = link.split("/");
        if (parts.length >= 6) {
            return parts[5];
        }
        return null;
    }

    public static Set<String> readUrlsFromFile(String filePath) {
        Set<String> urls = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                urls.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
