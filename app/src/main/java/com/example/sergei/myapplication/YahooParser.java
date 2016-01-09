package com.example.sergei.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Sergei on 08.01.2016.
 */
public class YahooParser {
    public Weather weather;
    private Document doc;
    public YahooParser(){
        weather = new Weather();
    }

    public void getData(String stringUrl,Context context) {

        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(stringUrl);
        } else {
            //textView.setText("No network connection available.");
        }

    }
    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            //textView.setText(result);
        }
    }
    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;
        String temperature=null;
        try {
            myurl = "http://weather.yahooapis.com/forecastrss?w=12718298&u=c";
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            //Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            //horrible
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(is);

            String lowTemp = getElementAttributeByTagName("yweather:forecast", "low");
            String highTemp = getElementAttributeByTagName("yweather:forecast", "high");
            String condition = getElementAttributeByTagName("yweather:condition", "text");
            String temperature1 = getElementAttributeByTagName("yweather:condition", "temp");
            String humidity = getElementAttributeByTagName("yweather:atmosphere", "humidity");
            String windSpeed = getElementAttributeByTagName("yweather:wind", "speed");
            String sunrise = getElementAttributeByTagName("yweather:astronomy", "sunrise");
            String sunset = getElementAttributeByTagName("yweather:astronomy", "sunset");
            String cityName = getElementAttributeByTagName("yweather:location", "city");
            weather.setLowTemperature(lowTemp);
            weather.setHighTemperature(highTemp);
            weather.setCondition(condition);
            weather.setTemperature(temperature1);
            weather.setHumidity(humidity);
            weather.setWindSpeed(windSpeed);
            weather.setSunrise(sunrise);
            weather.setSunrise(sunset);
            weather.setCityName(cityName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return temperature;
    }

    public String getElementAttributeByTagName(String tagName,String attribute){
        String result = null;
        NodeList nods = doc.getElementsByTagName(tagName);
        if (nods.getLength() > 0) {
            Element nodo = (Element) nods.item(0);
            result =  nodo.getAttribute(attribute);
        }
        return result;
    }

//    public boolean isOnline(Context context) {
//        ConnectivityManager cm =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }

    public static void main(String args[]){
        YahooParser parser = new YahooParser();
        try {
            parser.downloadUrl("dad");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
