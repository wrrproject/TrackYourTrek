package com.example.trackyourtrek.Utility;

import android.os.AsyncTask;
import android.util.Log;

import com.example.trackyourtrek.BuildConfig;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.ChallengeParticipation;
import com.example.trackyourtrek.System.Collections.Items.Group;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.Collections.Items.Progress;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;

//'-1' and '\n' are reserved symbols.
//Don't allow users to enter it as username, password etc.
public class Executioner extends AsyncTask<String, String, String> {
    public Executioner() {
    }

    public static final String ipAddress = "http://tissink.ddns.net:8080/";

    @Override
    protected String doInBackground(String... args) {
        try {
            if ("getEverything.php".equals(args[0])) {
                //Gets all data from database tables and puts it into local lists.
                String link = ipAddress + args[0];

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = reader.readLine();

                // Read Server Response
                if (line.equals("Failed")) {
                    return "Failed";
                } else {
                    //Group/////////////////////////////////////////////////////////////////////////
                    line = reader.readLine();                                                     //
                    //Assert                                                                      //
                    if (BuildConfig.DEBUG && !line.equals("Group")) {                             //
                        throw new AssertionError();                                               //
                    }                                                                             //
                    line = reader.readLine();                                                     //
                    while (!line.equals("-1")) {                                                  //
                        String[] info = new String[2];                                            //
                        for (int i = 0; i < info.length; i++) {                                   //
                            info[i] = line;                                                       //
                            line = reader.readLine();                                             //
                        }                                                                         //
                        //Assert                                                                  //
                        if (BuildConfig.DEBUG && !line.equals("-1")) {                            //
                            throw new AssertionError();                                           //
                        }                                                                         //
                        line = reader.readLine();                                                 //
                        //
                        TrackYourTrek.getGroups().add(new Group(Integer.parseInt(info[0]), info[1]));
                    }                                                                             //
                    ////////////////////////////////////////////////////////////////////////////////

                    //User//////////////////////////////////////////////////////////////////////////
                    line = reader.readLine();
                    //Assert
                    if (BuildConfig.DEBUG && !line.equals("User")) {
                        throw new AssertionError();
                    }
                    line = reader.readLine();
                    while (!line.equals("-1")) {
                        String[] info = new String[8];
                        for (int i = 0; i < info.length; i++) {
                            info[i] = line;
                            line = reader.readLine();
                        }
                        //Assert
                        if (BuildConfig.DEBUG && !line.equals("-1")) {
                            throw new AssertionError();
                        }
                        line = reader.readLine();

                        if (info[7].equals("0")) {
                            //Admin user
                            TrackYourTrek.setAdmin(info[0], info[1], info[2], info[3], info[4],
                                    info[6]);
                        } else {
                            //Walker
                            Walker walker;

                            //Check group
                            if (!info[5].equals("")) {
                                walker = new Walker(info[0], info[1], info[2], info[3],
                                        info[4], Integer.parseInt(info[5]));
                            } else {
                                walker = new Walker(info[0], info[1], info[2], info[3], info[4]);
                            }
                            TrackYourTrek.getWalkers().add(walker);
                        }
                    }
                    ////////////////////////////////////////////////////////////////////////////////

                    //Challenge/////////////////////////////////////////////////////////////////////
                    line = reader.readLine();
                    //Assert
                    if (BuildConfig.DEBUG && !line.equals("Challenge")) {
                        throw new AssertionError();
                    }
                    line = reader.readLine();
                    while (!line.equals("-1")) {
                        String[] info = new String[4];
                        for (int i = 0; i < info.length; i++) {
                            info[i] = line;
                            line = reader.readLine();
                        }
                        //Assert
                        if (BuildConfig.DEBUG && !line.equals("-1")) {
                            throw new AssertionError();
                        }
                        line = reader.readLine();

                        TrackYourTrek.getChallenges().add(new Challenge(Integer.parseInt(info[0]),
                                info[1], Integer.parseInt(info[2]), info[3].equals("1")));
                    }
                    ////////////////////////////////////////////////////////////////////////////////

                    //ChallengeParticipation/////////////////////////////////////////////////////////////////////
                    line = reader.readLine();
                    //Assert
                    if (BuildConfig.DEBUG && !line.equals("ChallengeParticipation")) {
                        throw new AssertionError();
                    }
                    line = reader.readLine();
                    while (!line.equals("-1")) {
                        String[] info = new String[6];
                        for (int i = 0; i < info.length; i++) {
                            info[i] = line;
                            line = reader.readLine();
                        }
                        //Assert
                        if (BuildConfig.DEBUG && !line.equals("-1")) {
                            throw new AssertionError();
                        }
                        line = reader.readLine();

                        Calendar startDate = TrackYourTrek.stringToCalendar(info[3]);
                        Calendar endDate = TrackYourTrek.stringToCalendar(info[4]);

                        TrackYourTrek.getChallengeParticipation().add(new ChallengeParticipation(
                                Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2]),
                                startDate, endDate, Boolean.parseBoolean(info[5])
                        ));
                    }
                    ////////////////////////////////////////////////////////////////////////////////

                    //Milestone/////////////////////////////////////////////////////////////////////
                    line = reader.readLine();
                    //Assert
                    if (BuildConfig.DEBUG && !line.equals("Milestone")) {
                        throw new AssertionError();
                    }
                    line = reader.readLine();
                    while (!line.equals("-1")) {
                        String[] info = new String[3];
                        for (int i = 0; i < info.length; i++) {
                            info[i] = line;
                            line = reader.readLine();
                        }
                        //Assert
                        if (BuildConfig.DEBUG && !line.equals("-1")) {
                            throw new AssertionError();
                        }
                        line = reader.readLine();

                        TrackYourTrek.getMilestones().add(new Milestone(Integer.parseInt(info[0]),
                                info[1], info[2]));
                    }
                    ////////////////////////////////////////////////////////////////////////////////

                    //Journey/////////////////////////////////////////////////////////////////////
                    line = reader.readLine();
                    //Assert
                    if (BuildConfig.DEBUG && !line.equals("Journey")) {
                        throw new AssertionError();
                    }
                    line = reader.readLine();
                    while (!line.equals("-1")) {
                        String[] info = new String[3];
                        for (int i = 0; i < info.length; i++) {
                            info[i] = line;
                            line = reader.readLine();
                        }
                        //Assert
                        if (BuildConfig.DEBUG && !line.equals("-1")) {
                            throw new AssertionError();
                        }
                        line = reader.readLine();

                        TrackYourTrek.getJourneys().add(new Journey(Integer.parseInt(info[0]),
                                Integer.parseInt(info[1]), Integer.parseInt(info[2])));
                    }
                    ////////////////////////////////////////////////////////////////////////////////

                    //Progress/////////////////////////////////////////////////////////////////////
                    line = reader.readLine();
                    //Assert
                    if (BuildConfig.DEBUG && !line.equals("Progress")) {
                        throw new AssertionError();
                    }
                    line = reader.readLine();
                    while (!line.equals("-1")) {
                        String[] info = new String[4];
                        for (int i = 0; i < info.length; i++) {
                            info[i] = line;
                            line = reader.readLine();
                        }
                        //Assert
                        if (BuildConfig.DEBUG && !line.equals("-1")) {
                            throw new AssertionError();
                        }
                        line = reader.readLine();

                        Calendar date = TrackYourTrek.stringToCalendar(info[3]);

                        TrackYourTrek.getProgress().add(new Progress(Integer.parseInt(info[0]),
                                Integer.parseInt(info[1]), Integer.parseInt(info[2]), date));
                    }
                    ////////////////////////////////////////////////////////////////////////////////

                    reader.close();
                    return "";
                }
            } else {
                String link = ipAddress + args[0];

                URL url = new URL(link);

                boolean somethingSaved = false;

                String data;
                for (Group g : TrackYourTrek.getGroups()) {
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    data = URLEncoder.encode("type", "UTF-8") + "=" +
                            URLEncoder.encode("Group", "UTF-8");
                    data += "&" + URLEncoder.encode("groupID", "UTF-8") + "=" +
                            URLEncoder.encode("" + g.getGroupID(), "UTF-8");
                    data += "&" + URLEncoder.encode("groupName", "UTF-8") + "=" +
                            URLEncoder.encode(g.getGroupName(), "UTF-8");
                    wr.write(data);
                    wr.flush();
                    wr.close();

                    if (BuildConfig.DEBUG) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        // Read Server Response
                        while ((line = reader.readLine()) != null) {
                            if (line.contains("Insert successful")) {
                                somethingSaved = true;
                            }
                        }
                        reader.close();
                    }
                }

                for (Walker w : TrackYourTrek.getWalkers()) {
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    data = URLEncoder.encode("type", "UTF-8") + "=" +
                            URLEncoder.encode("User", "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" +
                            URLEncoder.encode("" + w.getUsername(), "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                            URLEncoder.encode(w.getPassword(), "UTF-8");
                    data += "&" + URLEncoder.encode("fname", "UTF-8") + "=" +
                            URLEncoder.encode(w.getfName(), "UTF-8");
                    data += "&" + URLEncoder.encode("lname", "UTF-8") + "=" +
                            URLEncoder.encode(w.getlName(), "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" +
                            URLEncoder.encode(w.getEmail(), "UTF-8");
                    data += "&" + URLEncoder.encode("group", "UTF-8") + "=" +
                            URLEncoder.encode("" + w.getGroupID(), "UTF-8");
                    wr.write(data);
                    wr.flush();
                    wr.close();

                    if (BuildConfig.DEBUG) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        // Read Server Response
                        while ((line = reader.readLine()) != null) {
                            if (line.contains("Insert successful")) {
                                somethingSaved = true;
                            }
                        }
                        reader.close();
                    }
                }

                for (Challenge c : TrackYourTrek.getChallenges()) {
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    data = URLEncoder.encode("type", "UTF-8") + "=" +
                            URLEncoder.encode("Challenge", "UTF-8");
                    data += "&" + URLEncoder.encode("challengeID", "UTF-8") + "=" +
                            URLEncoder.encode("" + c.getChallengeID(), "UTF-8");
                    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" +
                            URLEncoder.encode(c.getName(), "UTF-8");
                    data += "&" + URLEncoder.encode("totalDistance", "UTF-8") + "=" +
                            URLEncoder.encode("" + c.getTotalDistance(), "UTF-8");
                    data += "&" + URLEncoder.encode("active", "UTF-8") + "=" +
                            URLEncoder.encode((c.isActive()) ? "1" : "0", "UTF-8");
                    wr.write(data);
                    wr.flush();
                    wr.close();

                    if (BuildConfig.DEBUG) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        // Read Server Response
                        while ((line = reader.readLine()) != null) {
                            if (line.contains("Insert successful")) {
                                somethingSaved = true;
                                break;
                            }
                        }
                        reader.close();
                    }
                }

                for (ChallengeParticipation p : TrackYourTrek.getChallengeParticipation()) {
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    data = URLEncoder.encode("type", "UTF-8") + "=" +
                            URLEncoder.encode("ChallengeParticipation", "UTF-8");
                    data += "&" + URLEncoder.encode("participationID", "UTF-8") + "=" +
                            URLEncoder.encode("" + p.getParticipationID(), "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" +
                            URLEncoder.encode(p.getUsername(), "UTF-8");
                    data += "&" + URLEncoder.encode("challengeID", "UTF-8") + "=" +
                            URLEncoder.encode("" + p.getChallengeID(), "UTF-8");
                    data += "&" + URLEncoder.encode("startDate", "UTF-8") + "=" +
                            URLEncoder.encode(TrackYourTrek.calendarToString(p.getStartDate()), "UTF-8");
                    data += "&" + URLEncoder.encode("endDate", "UTF-8") + "=" +
                            URLEncoder.encode(TrackYourTrek.calendarToString(p.getEndDate()), "UTF-8");
                    data += "&" + URLEncoder.encode("challengeCompleted", "UTF-8") + "=" +
                            URLEncoder.encode(Boolean.toString(p.isChallengeCompleted()), "UTF-8");
                    wr.write(data);
                    wr.flush();
                    wr.close();

                    if (BuildConfig.DEBUG) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        // Read Server Response
                        while ((line = reader.readLine()) != null) {
                            if (line.contains("Insert successful")) {
                                somethingSaved = true;
                            }
                        }
                        reader.close();
                    }
                }

                for (Progress p : TrackYourTrek.getProgress()) {
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    data = URLEncoder.encode("type", "UTF-8") + "=" +
                            URLEncoder.encode("Progress", "UTF-8");
                    data += "&" + URLEncoder.encode("progressID", "UTF-8") + "=" +
                            URLEncoder.encode("" + p.getProgressID(), "UTF-8");
                    data += "&" + URLEncoder.encode("participationID", "UTF-8") + "=" +
                            URLEncoder.encode("" + p.getParticipationID(), "UTF-8");
                    data += "&" + URLEncoder.encode("distance", "UTF-8") + "=" +
                            URLEncoder.encode("" + p.getDistance(), "UTF-8");
                    data += "&" + URLEncoder.encode("date", "UTF-8") + "=" +
                            URLEncoder.encode(TrackYourTrek.calendarToString(p.getDate()), "UTF-8");
                    wr.write(data);
                    wr.flush();
                    wr.close();

                    if (BuildConfig.DEBUG) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        // Read Server Response
                        while ((line = reader.readLine()) != null) {
                            if (line.contains("Insert successful")) {
                                somethingSaved = true;
                            }
                        }
                        reader.close();
                    }
                }

                for (Milestone m : TrackYourTrek.getMilestones()) {
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    data = URLEncoder.encode("type", "UTF-8") + "=" +
                            URLEncoder.encode("Milestone", "UTF-8");
                    data += "&" + URLEncoder.encode("milestoneID", "UTF-8") + "=" +
                            URLEncoder.encode("" + m.getMilestoneID(), "UTF-8");
                    data += "&" + URLEncoder.encode("location", "UTF-8") + "=" +
                            URLEncoder.encode(m.getLocation(), "UTF-8");
                    data += "&" + URLEncoder.encode("fact", "UTF-8") + "=" +
                            URLEncoder.encode(m.getFact(), "UTF-8");
                    wr.write(data);
                    wr.flush();
                    wr.close();

                    if (BuildConfig.DEBUG) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        // Read Server Response
                        while ((line = reader.readLine()) != null) {
                            if (line.contains("Insert successful")) {
                                somethingSaved = true;
                            }
                        }
                        reader.close();
                    }
                }

                for (Journey j : TrackYourTrek.getJourneys()) {
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    data = URLEncoder.encode("type", "UTF-8") + "=" +
                            URLEncoder.encode("Journey", "UTF-8");
                    data += "&" + URLEncoder.encode("challengeID", "UTF-8") + "=" +
                            URLEncoder.encode("" + j.getChallengeID(), "UTF-8");
                    data += "&" + URLEncoder.encode("milestoneID", "UTF-8") + "=" +
                            URLEncoder.encode("" + j.getMilestoneID(), "UTF-8");
                    data += "&" + URLEncoder.encode("distance", "UTF-8") + "=" +
                            URLEncoder.encode("" + j.getDistance(), "UTF-8");
                    wr.write(data);
                    wr.flush();
                    wr.close();

                    if (BuildConfig.DEBUG) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        // Read Server Response
                        while ((line = reader.readLine()) != null) {
                            if (line.contains("Insert successful")) {
                                somethingSaved = true;
                            }
                        }
                        reader.close();
                    }
                }

                if (BuildConfig.DEBUG) {
                    if (somethingSaved) {
                        Log.d("save", "something saved to database");
                    } else Log.d("save", "nothing saved to database");
                }

                return "";
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equals("Failed")) {
        }
    }
}
