package infobite.omnews.modal.video_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoDetailSnippet implements Parcelable {

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("channelId")
    @Expose
    private String channelId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<VideoDetailSnippet> CREATOR = new Creator<VideoDetailSnippet>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoDetailSnippet createFromParcel(Parcel in) {
            return new VideoDetailSnippet(in);
        }

        public VideoDetailSnippet[] newArray(int size) {
            return (new VideoDetailSnippet[size]);
        }

    };

    protected VideoDetailSnippet(Parcel in) {
        this.publishedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.channelId = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VideoDetailSnippet() {
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(publishedAt);
        dest.writeValue(channelId);
        dest.writeValue(title);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}