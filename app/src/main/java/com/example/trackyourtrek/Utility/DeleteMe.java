package com.example.trackyourtrek.Utility;

import android.os.AsyncTask;

import com.example.trackyourtrek.BuildConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DeleteMe extends AsyncTask<String, String, String> {
    public DeleteMe() {
    }

    private static final String ip = Executioner.ipAddress;

    @Override
    protected String doInBackground(String... strings) {
        String link = ip + "removeSomething.php";

        try {
            URL url = new URL(link);

            String data = URLEncoder.encode("type", "UTF-8") + "=" +
                    URLEncoder.encode(strings[0], "UTF-8");
            data += "&" + URLEncoder.encode("ID", "UTF-8") + "=" +
                    URLEncoder.encode(strings[1], "UTF-8");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            wr.close();
            if (BuildConfig.DEBUG) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Insert successful")) {
                    }
                }
                reader.close();
            }
            return "";
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String s) {
    }
}
