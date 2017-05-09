/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclockclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
//https://developer.mozilla.org/fr/docs/HTTP/Headers
public class HttpClockClient {

   private void sendGet(String acceptFormat) {

      try {

         URL url = new URL("");
         HttpURLConnection co = (HttpURLConnection) url.openConnection();
         co.setRequestMethod("GET");

         co.setRequestProperty("Content-Type", acceptFormat);

         int responseCode = co.getResponseCode();
         System.out.println("Sending 'GET' request to " + url);
         System.out.println("Response code: " + responseCode);

         BufferedReader in = new BufferedReader(new InputStreamReader(co.getInputStream()));
         String inputLine;
         StringBuffer response = new StringBuffer();

         while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
         }
         in.close();

         System.out.println(response.toString());

      } catch (MalformedURLException m) {
         System.err.println("Bad URL");
      } catch (IOException e) {
         System.err.println("problem with openConnection");
      }
   }

   private void sendPost(String format) throws Exception {
      URL url = new URL("");
      HttpURLConnection co = (HttpURLConnection) url.openConnection();

      co.setRequestMethod("POST");
      co.setRequestProperty("Content-Type", format);

      String urlParameters = "NONE";

      // Send post request
      co.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(co.getOutputStream());
      wr.writeBytes(urlParameters);
      wr.flush();
      wr.close();

      int responseCode = co.getResponseCode();
      System.out.println("\nSending 'POST' request to URL : " + url);
      System.out.println("Post parameters : " + urlParameters);
      System.out.println("Response Code : " + responseCode);

      BufferedReader in = new BufferedReader(new InputStreamReader(co.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
      }
      in.close();

      System.out.println(response.toString());

   }

   public static void main(String[] args) {
      final String acceptHTML = "text/html; charset=utf-8";//Content-Type: text/html; charset=utf-8
      final String acceptJson = "application/json; charset=utf-8";//'Content-Type: application/json'
      final String acceptXML = "text/xml; charset=utf-8";

      HttpClockClient hc = new HttpClockClient();

      try {
         System.out.println("Sending get request html");
         hc.sendGet(acceptHTML);

         System.out.println("Sending Http POST request");
         hc.sendPost(acceptHTML);

         System.out.println("Sending get request json");
         hc.sendGet(acceptJson);

         System.out.println("Sending Http POST request");
         hc.sendPost(acceptJson);

         System.out.println("Sending get request XML");
         hc.sendGet(acceptXML);

         System.out.println("Sending Http POST request");
         hc.sendPost(acceptXML);
      } catch (Exception e) {
         System.err.println("Loupe!");
      }

   }

}
