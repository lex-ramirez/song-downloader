package Classes;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import com.sapher.youtubedl.mapper.VideoInfo;

import java.io.File;

public class YoutubeQuery {

    public YoutubeQuery() {}

    public VideoInfo getVidInfo(String url) throws YoutubeDLException {

        VideoInfo vid = YoutubeDL.getVideoInfo(url);
        return vid;
    }

    public String getSong(String url) throws YoutubeDLException {

        YoutubeDL.setExecutablePath("C:/Users/lexra/Downloads/youtube-dl");
        String directory = System.getProperty("C:/Users/lexra/Downloads");

        YoutubeDLRequest request = new YoutubeDLRequest(url, directory);
        request.setDirectory("C:/Users/lexra/Downloads");
        System.out.println("Downloading...");

        request.setOption("ignore-errors");		// --ignore-errors
        request.setOption("output", "%(id)s");	// --output "%(id)s"
        request.setOption("retries", 10);		// --retries 10
        request.setOption("audio-format \"mp3\"");


        VideoInfo newVideo = YoutubeDL.getVideoInfo(url);
        String vidId = newVideo.id;

        YoutubeDLResponse response = YoutubeDL.execute(request);
        changeFileName(vidId);
        System.out.println(response.getOut());

        // Response
        String stdOut = response.getOut(); // Executable output

        return stdOut;
    }

    public void changeFileName(String id) {

        File change = new File("C:/Users/lexra/Downloads" + "/" + id);
        // CHANGE ITS NAME HERE TO DO NEXT
        System.out.println(change.getName());
        System.out.println(change.getPath());
    }

}
