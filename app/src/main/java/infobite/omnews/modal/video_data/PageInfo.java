package infobite.omnews.modal.video_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageInfo implements Parcelable {

    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("resultsPerPage")
    @Expose
    private Integer resultsPerPage;
    public final static Parcelable.Creator<PageInfo> CREATOR = new Creator<PageInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PageInfo createFromParcel(Parcel in) {
            return new PageInfo(in);
        }

        public PageInfo[] newArray(int size) {
            return (new PageInfo[size]);
        }

    };

    protected PageInfo(Parcel in) {
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.resultsPerPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public PageInfo() {
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalResults);
        dest.writeValue(resultsPerPage);
    }

    public int describeContents() {
        return 0;
    }

}