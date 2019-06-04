package infobite.omnews.retrofit_provider;

import infobite.omnews.constant.Constant;
import infobite.omnews.modal.banner_model.BannerModel;
import infobite.omnews.modal.video_data.VideoMainModal;
import infobite.omnews.modal.video_detail.VideoDetailMainModal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitApiClient {

    @GET(Constant.LIST_API)
    Call<VideoMainModal> getVideoList();

    @GET(Constant.BANNER_DATA)
    Call<BannerModel> banner();

    /*@FormUrlEncoded
    @POST(Constant.SIGNUP_API)
    Call<ResponseBody> signUp(@Field("name") String name,
                              @Field("email") String email,
                              @Field("contact") String contact,
                              @Field("password") String password);
*/

    @GET
    Call<VideoMainModal> channelData(@Url String url);

    @GET
    Call<VideoDetailMainModal> videoDetail(@Url String url);
}