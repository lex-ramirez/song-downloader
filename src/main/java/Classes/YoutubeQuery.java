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

/**
 * Youtube Query class that creates queries and holds the
 * functionality for downloading songs
 */
public class YoutubeQuery {

    /**
     * The title of the video
     */
    private String vidTitle = null;

    /**
     * Empty constructor
     */
    public YoutubeQuery() {}

    /**
     * Formats a title to the correct format for URL
     * @param title title of the video
     * @return the formatted title of the video
     */
    public String formatTitle(String title) {
        return title.replace(" ", "%20");
    }

    /**
     * Returns a youtube URL given a title
     * @param title the title of the video
     * @return the url of the video as a string
     * @throws IOException
     * @throws JSONException
     * @throws YoutubeDLException
     */
    public String findURL(String title) throws IOException, JSONException, YoutubeDLException {

        // Create the API URL
        String vidTitle = formatTitle(title);
        String apiUrl = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&order=relevance&q="
                + vidTitle + "&videoDefinition=any&key=AIzaSyAhdQ4tG2ChLrzOHVHbGpr15Il1GFJ0wkA";

        // Establish connection with URL
        URL url = new URL(apiUrl);
        URLConnection conn = url.openConnection();
        conn.connect();

        // We now have the data
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        // Format data into a string
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        // Create a JSON Object to grab the data
        JSONObject jsonObj = new JSONObject(sb.toString());
        JSONObject jsonObject = jsonObj.getJSONArray("items").getJSONObject(0);

        // The ID of the video
        String vidId = jsonObject.getJSONObject("id").get("videoId").toString();
        // The Channel of the video
        String vidChannel = jsonObject.getJSONObject("snippet").get("channelTitle").toString();

        // The properly formatted URL
        String properUrl = "https://www.youtube.com/watch?v=" + vidId + "&ab_channel=" + formatTitle(vidChannel);

        return properUrl;
    }

    /**
     * Downloads a song given a youtube url and a directory path
     * @param url the url of the youtube video
     * @param path the destination path for the downloaded song
     * @throws YoutubeDLException
     */
    public void downloadSong(String url, String path) throws YoutubeDLException {

        // Specifies Where YoutubeDL.exe is within structure
        File file = new File("./youtubedl-ffm/youtube-dl.exe");
        String ytDLPath = file.getAbsolutePath();
        YoutubeDL.setExecutablePath(ytDLPath);

        // Gets destination directory for installation, get this from user (path)
        String directory = System.getProperty(path);

        // Creates a youtubedl request with url and directory
        YoutubeDLRequest request = new YoutubeDLRequest(url, directory);
        request.setDirectory(path);

        // Options for command
        request.setOption("ignore-errors");		// --ignore-errors
        request.setOption("output \"%(title)s.%(ext)s\"");	// --output "%(id)s"
        request.setOption("extract-audio");
        request.setOption("audio-format \"mp3\"");

        // Gets the video info of the youtube video
        VideoInfo newVideo = YoutubeDL.getVideoInfo(url);
        String vidTitle = newVideo.title;
        this.vidTitle = vidTitle;

        // Execute the download and grab the response
        YoutubeDLResponse response = YoutubeDL.execute(request);
        // Print the response code(s)
        System.out.println(response.getOut());
    }

    /**
     * Returns the title of the video
     * @return video title
     */
    public String getVidTitle() {
        return this.vidTitle;
    }
}
