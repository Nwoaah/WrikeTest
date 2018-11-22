package util.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TwitterIconMatching {


    public static final String Img = "<path d=\"M14.2 3.2v.42A9.23 9.23 0 0 1-.01 11.39a6.66 6.66 0 0 0 .78 0 6.5 6.5 0 0 0 4-1.38 3.25 3.25 0 0 1-3-2.25 4.21 4.21 0 0 0 .61 0 3.42 3.42 0 0 0 .85-.11 3.24 3.24 0 0 1-2.6-3.18 3.27 3.27 0 0 0 1.47.41 3.25 3.25 0 0 1-1-4.34 9.22 9.22 0 0 0 6.69 3.39 3.66 3.66 0 0 1-.08-.74A3.25 3.25 0 0 1 13.32.97a6.39 6.39 0 0 0 2.06-.78 3.23 3.23 0 0 1-1.43 1.79 6.5 6.5 0 0 0 1.87-.5A7 7 0 0 1 14.2 3.2z\" fill=\"#3b7ed5\"/>";
    public static final String URL = "https://d3tvpxjako9ywy.cloudfront.net";



        // HTTP GET request
        public static String doGet(String url) throws Exception {



            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
          //  con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'GET' request to URL : " + url);
//            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
           // System.out.println(response.toString());



            return response.toString();
        }

        public static boolean isItTwitterIcon(String path) {
            String response="";
            try {
                // "/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v1#twitter"
             response = TwitterIconMatching.doGet(URL + path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String tmp = response.substring( response.indexOf("<title>sb_twitter</title>"));
            tmp = tmp.replaceFirst("<title>sb_twitter</title>","");
            tmp = tmp.substring(0,tmp.indexOf("</symbol>"));

            return tmp.equals(Img);
        }
}
