package infobite.omnews.retrofit_provider;

import android.app.Dialog;

import java.util.concurrent.TimeUnit;

import infobite.omnews.BuildConfig;
import infobite.omnews.constant.Constant;
import infobite.omnews.modal.video_data.VideoMainModal;
import infobite.omnews.modal.video_detail.VideoDetailMainModal;
import infobite.omnews.utils.AppProgressDialog;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceNews {

    public static RetrofitApiClient client;

    public RetrofitServiceNews() {

        HttpLoggingInterceptor mHttpLoginInterceptor = new HttpLoggingInterceptor();

        mHttpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            mOkClient.addInterceptor(mHttpLoginInterceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_NEWS)
                .client(mOkClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        client = retrofit.create(RetrofitApiClient.class);
    }

    public static RetrofitApiClient getRetrofit() {

        if (client == null)
            new RetrofitServiceNews();
        return client;
    }

    public static void getVideoData(final Dialog dialog, final Call<VideoMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<VideoMainModal>() {
            @Override
            public void onResponse(Call<VideoMainModal> call, Response<VideoMainModal> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<VideoMainModal> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }

    public static void getVideoDetail(final Dialog dialog, final Call<VideoDetailMainModal> method, final WebResponse webResponse) {
        if (dialog != null)
            AppProgressDialog.show(dialog);

        method.enqueue(new Callback<VideoDetailMainModal>() {
            @Override
            public void onResponse(Call<VideoDetailMainModal> call, Response<VideoDetailMainModal> response) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                WebServiceResponse.handleResponse(response, webResponse);
            }

            @Override
            public void onFailure(Call<VideoDetailMainModal> call, Throwable throwable) {
                if (dialog != null)
                    AppProgressDialog.hide(dialog);
                webResponse.onResponseFailed(throwable.getMessage());
            }
        });
    }
}