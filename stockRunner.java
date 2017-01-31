import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Program allows user to enter any stock ticker and returns information about that stock's current status.
 * If the user enters the wrong stock ticker the program prints a message to console and terminates process.
 * 
 * Jason Navi
 * 1/12/2017
 */
public class stockRunner
{
    /**handles the input and output of program*/
    public static void main(String[] args){
        //Creates a scanner and takes input of stock ticker(s) entered by user. Converts to upper case to avoid any typing errors and stores it in an arraylist of strings
        Scanner response = new Scanner(System.in);
        System.out.println("Please enter the stock ticker you want the information for. If you want to enter more than one ticker, please seperate the input by a comma followed by a space or enter STOP + to exit the program: ");
        ArrayList<String> tickers = tokenizeStockList(response.nextLine().toUpperCase());

        //keep processing stock ticker until user types in "STOP"
        while(!tickers.get(0).equals("STOP")){
            //Loop through the list of stock tickers
            for(int i = 0; i < tickers.size(); i++){
                //adds stock ticker to preset URL that gives unique information about stocks and reads the rest of the line to avoid any blankspace errors and closes the Scanner
                String ticker = "http://finance.yahoo.com/d/quotes.csv?s=" + tickers.get(i) + "&f=naopmkja2ef6";

                //Gets all the stock information from the CSV file and gets ride of the commas in between them and stores it in a string to allow for easier reading
                String line = getCSV(ticker);

                //returns an arraylist of strings and in each index of the list a preset unique piece of stock information is put inside it
                ArrayList<String> companyData = tokenizeStockInfo(line);

                //gets rid of any quotation marks and prints the company information inside the list
                removeQuotationMarks(companyData);
                printCompanyInfo(companyData);
            }
            //asks user for another input(s) and converts it to upper case and stores it in an arraylist
            System.out.println("Please enter the stock ticker you want the information for. If you want to enter more than one ticker, please seperate the input by a comma followed by a space or enter STOP to exit the program: ");
            tickers = tokenizeStockList(response.next().toUpperCase());
        }

        //close the scanner and thank the user
        response.close();
        System.out.println("\n" + "Thank you for using this stock application and have a nice day! ");
    }

    /**splits up the companies seperated by commas into individual strings and puts them inside the ArrayList and returns it*/
    public static ArrayList<String> tokenizeStockList(String input){
        //use a stringtokenizer to seperate the data by commas and add the first token to your array list
        StringTokenizer myTokens = new StringTokenizer(input, ",");
        ArrayList<String> data = new ArrayList<String>();
        data.add(myTokens.nextToken());
        
        //while you still have more data inside the tokenizer, add it to the arraylist and parse the string so you remove the whitespace located at the beginning of the string
        while(myTokens.countTokens() != 0){
            data.add(myTokens.nextToken().substring(1));
        }
        
        //return the list of companies
        return data;
    }

    /**Sets up a connection to the URL of the stock ticker entered by the user and reads it using Scanner. */
    public static String getCSV(String ticker){
        //put in a try catch block in case of an input output error
        try{
            //sets up a connection to the URL on yahoo finance
            URL yahooFinance = new URL(ticker);
            URLConnection link = yahooFinance.openConnection();

            //connects scanner to the URLConnection object so it can read it 
            Scanner sc = new Scanner(yahooFinance.openStream());
            String line = sc.nextLine();

            //close scanner when finished and return String of data
            sc.close();
            return line;
        }
        catch(IOException e){
            System.out.println(e);
        }
        //used to avoid error
        return "";
    }

    /**splits up the data seperated by commas into individual strings and puts them inside the ArrayList and returns it*/
    public static ArrayList<String> tokenizeStockInfo(String d){
        //use a stringtokenizer to seperate the data by commas and add the first token to your array list
        StringTokenizer myTokens = new StringTokenizer(d, ",");
        ArrayList<String> data = new ArrayList<String>();
        data.add(myTokens.nextToken());

        //if the first entry is N/A that means the user entered in an incorrect stock ticker so print an error and thank you statement to the console and terminate the program
        if(data.get(0).equals("N/A")){
            System.out.println("\n" + "The stock ticker you entered in was incorrect! " + "\n" + "The program will now terminate, thank you for using this stock application and have a nice day!");
            System.exit(0);
        }

        /*if the second token has a letter in it then it is part of the company name and you should set the index 0 equal to itself (first part of company name) plus the second part of the company name
        remove index 1 of ArrayList since you just combined index 0 and 1 and stored it inisde index 0*/
        data.add(myTokens.nextToken());
        if(Character.isLetter(data.get(1).charAt(1))){
            data.set(0, data.get(0) + data.get(1));
            data.remove(1);
        }

        //while you still have more data inside the tokenizer, add it to the arraylist
        while(myTokens.countTokens() != 0){
            data.add(myTokens.nextToken());
        }
        
        //return the list of information
        return data;
    }

    /**prints out information about the company stored inside the arrayList */
    public static void printCompanyInfo(ArrayList<String> d){
        System.out.println("\n" + "Company Name: " + d.get(0) + "\n" + "Cost Per Share: $" + d.get(1) + "\n" + "Opening price: $" + d.get(2) + "\n" + "Previous Closing Price: $" + d.get(3) + "\n" + "Day's Range: $" + d.get(4) + "\n" + "52 Week High: $" + d.get(5) + "\n" + "52 Week Low: $" + d.get(6) + "\n" + "Average Volume: " + d.get(7) + " Shares" + "\n" + "Earnings per Share (EPS): $" + d.get(8) + "\n" + "Float Shares: " + d.get(9) + "\n");
    }

    /**loops through the arraylist and replaces every quotation mark found in each index with a null string. Uses a regex pattern for quotation mark*/
    public static void removeQuotationMarks(ArrayList<String> data){
        for(int i = 0; i < data.size(); i++){
            data.set(i, data.get(i).replaceAll("^\"|\"$", ""));
        }
    }
}

