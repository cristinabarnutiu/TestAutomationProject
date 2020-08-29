package com.FindBrokenLink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetLinkStatus {

    static int invalidLink; //to count broken links

    public static void verifyLink(String linkString) {
        try {
            URL url = new URL(linkString);
            //Open HTTP Connection
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

            //Define TimeOut
            //urlConnect.setConnectTimeout(50000);

            //Hit URL
            urlConnect.connect();

            if (urlConnect.getResponseCode() == 200) {
            } else {
                System.out.println(linkString + ": Response code: " + urlConnect.getResponseCode());
                invalidLink++;
            }

        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getInvalidLinkCount(){
        System.out.println("Total invalid links: "+invalidLink);
        //return invalidLink;
    }
}
