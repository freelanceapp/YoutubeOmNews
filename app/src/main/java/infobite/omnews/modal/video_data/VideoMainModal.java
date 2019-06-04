package infobite.omnews.modal.video_data;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoMainModal implements Parcelable {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;
    @SerializedName("regionCode")
    @Expose
    private String regionCode;
    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    public final static Parcelable.Creator<VideoMainModal> CREATOR = new Creator<VideoMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoMainModal createFromParcel(Parcel in) {
            return new VideoMainModal(in);
        }

        public VideoMainModal[] newArray(int size) {
            return (new VideoMainModal[size]);
        }

    };

    protected VideoMainModal(Parcel in) {
        this.kind = ((String) in.readValue((String.class.getClassLoader())));
        this.etag = ((String) in.readValue((String.class.getClassLoader())));
        this.nextPageToken = ((String) in.readValue((String.class.getClassLoader())));
        this.regionCode = ((String) in.readValue((String.class.getClassLoader())));
        this.pageInfo = ((PageInfo) in.readValue((PageInfo.class.getClassLoader())));
        in.readList(this.items, (infobite.omnews.modal.video_data.Item.class.getClassLoader()));
    }

    public VideoMainModal() {
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

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(kind);
        dest.writeValue(etag);
        dest.writeValue(nextPageToken);
        dest.writeValue(regionCode);
        dest.writeValue(pageInfo);
        dest.writeList(items);
    }

    public int describeContents() {
        return 0;
    }

}