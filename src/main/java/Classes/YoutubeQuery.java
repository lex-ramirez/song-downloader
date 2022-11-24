package Classes;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import com.sapher.youtubedl.mapper.VideoInfo;
import javafx.stage.FileChooser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class YoutubeQuery {

    private String vidTitle = null;

    public YoutubeQuery() {}

    /**
     * Formats a title to the correct format for URL
     * @param title title of the video
     * @return the formatted title of the video
     */
    public String formatTitle(String title) {
        return title.replace(" ", "%20");
    }

    public String findURL(String title) throws IOException, JSONException, YoutubeDLException {

        // Create the API URL
        String vidTitle = formatTitle(title);
        String apiUrl = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&order=relevance&q=" + vidTitle + "&videoDefinition=any&key=AIzaSyAhdQ4tG2ChLrzOHVHbGpr15Il1GFJ0wkA";

        // Establish connection with URL
        URL url = new URL(apiUrl);
        URLConnection conn = url.openConnection();
        conn.connect();

        // We now have the data
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        JSONObject jsonObj = new JSONObject(sb.toString());
        JSONObject jsonObject = jsonObj.getJSONArray("items").getJSONObject(0);

        // The ID of the video
        String vidId = jsonObject.getJSONObject("id").get("videoId").toString();
        // The Channel of the video
        String vidChannel = jsonObject.getJSONObject("snippet").get("channelTitle").toString();

        String properUrl = "https://www.youtube.com/watch?v=" + vidId + "&ab_channel=" + formatTitle(vidChannel);

        return properUrl;
    }

    public void downloadSong(String url) throws YoutubeDLException {

        // Specifies Where YoutubeDL.exe is
        File file = new File("./youtubedl-ffm/youtube-dl.exe");
        String ytDLPath = file.getAbsolutePath();
        YoutubeDL.setExecutablePath(ytDLPath);

        // Gets directory destination directory for installation, get this from user
        String directory = System.getProperty("C:/Users/lexra/Downloads");

        // Creates request with url and directory
        YoutubeDLRequest request = new YoutubeDLRequest(url, directory);
        request.setDirectory("C:/Users/lexra/Downloads");

        // Options for command
        request.setOption("ignore-errors");		// --ignore-errors
        request.setOption("output \"%(title)s.%(ext)s\"");	// --output "%(id)s"
        //request.setOption("retries", 10);		// --retries 10
        request.setOption("extract-audio");
        request.setOption("audio-format \"mp3\"");

        // Gets the video info of the youtube video
        VideoInfo newVideo = YoutubeDL.getVideoInfo(url);
        String vidTitle = newVideo.title;
        this.vidTitle = vidTitle;

        // Response from the YoutubeDL Query
        YoutubeDLResponse response = YoutubeDL.execute(request);
        // Print the response code(s)
        System.out.println(response.getOut());
    }

    public String getVidTitle() {
        return this.vidTitle;
    }

    /**
     * Changes the filename of output to the title of the video (youtubedl can't do this)
     * @param title The title of the video
     */
    /*public void changeFileName(String title) {

        File init = new File("C:/Users/lexra/Downloads" + "/" + title + ".mp4");
        File change = new File("C:/Users/lexra/Downloads" + "/" + title);

        init.renameTo(change);
    }*/

}
