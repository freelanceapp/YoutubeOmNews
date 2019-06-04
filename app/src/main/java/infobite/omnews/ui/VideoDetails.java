package infobite.omnews.ui;

/**
 * Created by Ansh on 29/10/2017 12:26 AM.
 */

public class VideoDetails {

    String VideoName;
    String VideoDesc;
    String URL;
    String VideoId;
    String time;

    public void setVideoName(String VideoName) {
        this.VideoName = VideoName;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoDesc(String VideoDesc) {
        this.VideoDesc = VideoDesc;
    }

    public String getVideoDesc() {
        return VideoDesc;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setVideoId(String VideoId) {
        this.VideoId = VideoId;
    }

    public String getVideoId() {
        return VideoId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
