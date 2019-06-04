package infobite.omnews.modal.video_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoDetailItem implements Parcelable {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("snippet")
    @Expose
    private VideoDetailSnippet snippet;
    public final static Parcelable.Creator<VideoDetailItem> CREATOR = new Creator<VideoDetailItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoDetailItem createFromParcel(Parcel in) {
            return new VideoDetailItem(in);
        }

        public VideoDetailItem[] newArray(int size) {
            return (new VideoDetailItem[size]);
        }

    };

    protected VideoDetailItem(Parcel in) {
        this.kind = ((String) in.readValue((String.class.getClassLoader())));
        this.etag = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.snippet = ((VideoDetailSnippet) in.readValue((VideoDetailSnippet.class.getClassLoader())));
    }

    public VideoDetailItem() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VideoDetailSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(VideoDetailSnippet snippet) {
        this.snippet = snippet;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(kind);
        dest.writeValue(etag);
        dest.writeValue(id);
        dest.writeValue(snippet);
    }

    public int describeContents() {
        return 0;
    }

}