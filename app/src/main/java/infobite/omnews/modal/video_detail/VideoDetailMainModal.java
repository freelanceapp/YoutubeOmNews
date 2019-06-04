package infobite.omnews.modal.video_detail;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoDetailMainModal implements Parcelable {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("items")
    @Expose
    private List<VideoDetailItem> items = new ArrayList<VideoDetailItem>();
    public final static Parcelable.Creator<VideoDetailMainModal> CREATOR = new Creator<VideoDetailMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoDetailMainModal createFromParcel(Parcel in) {
            return new VideoDetailMainModal(in);
        }

        public VideoDetailMainModal[] newArray(int size) {
            return (new VideoDetailMainModal[size]);
        }

    };

    protected VideoDetailMainModal(Parcel in) {
        this.kind = ((String) in.readValue((String.class.getClassLoader())));
        this.etag = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.items, (VideoDetailItem.class.getClassLoader()));
    }

    public VideoDetailMainModal() {
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

    public List<VideoDetailItem> getItems() {
        return items;
    }

    public void setItems(List<VideoDetailItem> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(kind);
        dest.writeValue(etag);
        dest.writeList(items);
    }

    public int describeContents() {
        return 0;
    }

}