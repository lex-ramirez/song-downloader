package Classes;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import com.sapher.youtubedl.mapper.VideoInfo;

import java.io.File;

public class YoutubeQuery {

    public YoutubeQuery() {}

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
        request.setOption("retries", 10);		// --retries 10
        request.setOption("extract-audio");
        request.setOption("audio-format \"mp3\"");

        // Gets the video info of the youtube video
        /*VideoInfo newVideo = YoutubeDL.getVideoInfo(url);
        String vidTitle = newVideo.title;*/

        // Response from the YoutubeDL Query
        YoutubeDLResponse response = YoutubeDL.execute(request);
        // Print the response code(s)
        System.out.println(response.getOut());
        System.out.println(response.getCommand());
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
