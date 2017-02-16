# YahooFinanceStocks

Author: Jason Navi

Email: jnavi1002@gmail.com

Program description: Self project I created for fun to retrieve stock information from Yahoo Finance using it's API offered. The program prints out the company name, in real time cost per share, opening price, previous closing price, day's range, 52 week high and low, average volume, earnings per share, and float shares. Program will keep running until "STOP" is entered and user can enter as many stocks at one time as he/she wants. Program uses a jar file I downloaded called: "jSoup-webScraping.jar" which allows me to use the URL and URLConnection objects to connect to the API which is online. 

Yahoo Finance API Guide: https://greenido.wordpress.com/2009/12/22/work-like-a-pro-with-yahoo-finance-hidden-api/

JSoup Web Scraping JAR documentation and download: https://jsoup.org/

IDE used: BlueJ

Libraries used: java.util.*, java.io.*, java.util.Scanner, java.net.URL, java.net.URLConnection, java.IOException, java.util.ArrayList

Classes: stockRunner

How to Run: Type in any stock ticker (not case sensitive) and click enter. If you want to print out more than one stock, put a comma and a space in between each stock ticker. If you want to stop the program, then type in "STOP" (not case sensitive) and the program will exit. Do not type in stop when trying to print out more than one stock. These are both acceptable inputs: "GooG" or "msFt, aaPl". Typing in "Goog" would print: 

Company Name: Alphabet Inc.

Cost Per Share: $819.95

Opening price: $819.36

Previous Closing Price: $820.45

Day's Range: $818.47 - 823.00

52 Week High: $841.95

52 Week Low: $663.28

Average Volume: 1551420 Shares

Earnings per Share (EPS): $27.88

Float Shares: 598707000


