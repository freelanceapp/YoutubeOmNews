package infobite.omnews.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import infobite.omnews.retrofit_provider.RetrofitApiClient;
import infobite.omnews.retrofit_provider.RetrofitService;
import infobite.omnews.retrofit_provider.RetrofitServiceNews;


public class BaseActivity extends AppCompatActivity {

    public Context mContext;
    public ConnectionDetector cd;
    public RetrofitApiClient retrofitApiClient, retrofitApiClientNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        retrofitApiClientNews = RetrofitServiceNews.getRetrofit();
    }
}
