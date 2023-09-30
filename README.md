# 4chan Web Scraper

This is a simple Java application for scraping and downloading content from 4chan boards. It allows you to either download all threads from a board or download images from a specific thread.

## Prerequisites

Before you begin, ensure you have the following:

- Java Development Kit (JDK) installed.
- [Chrome WebDriver](https://sites.google.com/chromium.org/driver/) installed and the path to the executable set. (One is included in the repo)
- Gradle build tool installed ([Gradle Official Website](https://gradle.org/)).


## Getting Started

1. Clone or download this repository to your local machine.

2. Compile the Java code. You can use your favorite Java IDE or compile it from the command line. Make sure to include the necessary dependencies.

   ```bash
   git clone [https://github.com/jakojanes/webscraper-groceryprices.git](https://github.com/jakojanes/4chan-image-downloader.git)
   cd 4chan-image-downloader
   gradle shadowJar
   java -jar <jar-file-name>.jar

## Usage

**Option 1: Entire Board Download**

Choose this option to download all threads from a 4chan board.

1. Select option 1 from the menu.

2. Enter the board link you want to scrape. For example: `https://boards.4chan.org/boardname/catalog`.

3. The application will scrape all thread links from the board and then download images from each thread.

**Option 2: Single Thread Download**

Choose this option to download images from a specific thread.

1. Select option 2 from the menu.

2. Enter the thread link you want to scrape. For example: `https://boards.4chan.org/boardname/thread/1234567`.

3. The application will download images from the specified thread.

**Option 3: Exit**

Choose this option to exit the application.

**Error Handling**

The application includes error handling for various scenarios. If you encounter any issues during scraping or downloading, you will see an error message with details.

**Important Notes**

- The application uses Chrome WebDriver in headless mode. Ensure you have it installed and configured correctly.

- Make sure the Chrome WebDriver executable is in the system's PATH.

- You can modify the user agent in the code according to your needs.

- The application runs in an endless loop until you choose to exit (Option 3).

Feel free to explore the code and customize it as per your requirements.

Happy scraping!
