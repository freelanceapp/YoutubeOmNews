package infobite.omnews.modal.video_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails implements Parcelable {

    @SerializedName("default")
    @Expose
    private Default _default;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("high")
    @Expose
    private High high;
    public final static Parcelable.Creator<Thumbnails> CREATOR = new Creator<Thumbnails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Thumbnails createFromParcel(Parcel in) {
            return new Thumbnails(in);
        }

        public Thumbnails[] newArray(int size) {
            return (new Thumbnails[size]);
        }

    };

    protected Thumbnails(Parcel in) {
        this._default = ((Default) in.readValue((Default.class.getClassLoader())));
        this.medium = ((Medium) in.readValue((Medium.class.getClassLoader())));
        this.high = ((High) in.readValue((High.class.getClassLoader())));
    }

    public Thumbnails() {
    }

    public Default getDefault() {
        return _default;
    }

    public void setDefault(Default _default) {
        this._default = _default;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_default);
        dest.writeValue(medium);
        dest.writeValue(high);
    }

    public int describeContents() {
        return 0;
    }

}