
package infobite.omnews.modal.banner_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerDatum implements Parcelable {

    @SerializedName("banner_id")
    @Expose
    private String id;
    @SerializedName("banner_title")
    @Expose
    private String offerName;
    @SerializedName("banner_image")
    @Expose
    private String offerPicture;
    public final static Creator<BannerDatum> CREATOR = new Creator<BannerDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BannerDatum createFromParcel(Parcel in) {
            return new BannerDatum(in);
        }

        public BannerDatum[] newArray(int size) {
            return (new BannerDatum[size]);
        }

    };

    protected BannerDatum(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.offerName = ((String) in.readValue((String.class.getClassLoader())));
        this.offerPicture = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BannerDatum() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BannerDatum withId(String id) {
        this.id = id;
        return this;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public BannerDatum withOfferName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public String getOfferPicture() {
        return offerPicture;
    }

    public void setOfferPicture(String offerPicture) {
        this.offerPicture = offerPicture;
    }

    public BannerDatum withOfferPicture(String offerPicture) {
        this.offerPicture = offerPicture;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(offerName);
        dest.writeValue(offerPicture);
    }

    public int describeContents() {
        return 0;
    }

}
