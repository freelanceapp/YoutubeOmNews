package infobite.omnews.modal.video_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id implements Parcelable {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("videoId")
    @Expose
    private String videoId;
    public final static Parcelable.Creator<Id> CREATOR = new Creator<Id>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Id createFromParcel(Parcel in) {
            return new Id(in);
        }

        public Id[] newArray(int size) {
            return (new Id[size]);
        }

    };

    protected Id(Parcel in) {
        this.kind = ((String) in.readValue((String.class.getClassLoader())));
        this.videoId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Id() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(kind);
        dest.writeValue(videoId);
    }

    public int describeContents() {
        return 0;
    }

}