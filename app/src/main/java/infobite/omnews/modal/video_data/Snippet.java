package infobite.omnews.modal.video_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snippet implements Parcelable {

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
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;
    @SerializedName("liveBroadcastContent")
    @Expose
    private String liveBroadcastContent;
    public final static Parcelable.Creator<Snippet> CREATOR = new Creator<Snippet>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Snippet createFromParcel(Parcel in) {
            return new Snippet(in);
        }

        public Snippet[] newArray(int size) {
            return (new Snippet[size]);
        }

    };

    protected Snippet(Parcel in) {
        this.publishedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.channelId = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnails = ((Thumbnails) in.readValue((Thumbnails.class.getClassLoader())));
        this.channelTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.liveBroadcastContent = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Snippet() {
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

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(publishedAt);
        dest.writeValue(channelId);
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(thumbnails);
        dest.writeValue(channelTitle);
        dest.writeValue(liveBroadcastContent);
    }

    public int describeContents() {
        return 0;
    }

}