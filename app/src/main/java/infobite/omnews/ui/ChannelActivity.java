package infobite.omnews.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import infobite.omnews.R;
import infobite.omnews.adapter.BannerPagerAdapter;
import infobite.omnews.adapter.CustomListAdapter;
import infobite.omnews.modal.banner_model.BannerDatum;
import infobite.omnews.modal.banner_model.BannerModel;
import infobite.omnews.modal.video_data.VideoMainModal;
import infobite.omnews.retrofit_provider.RetrofitService;
import infobite.omnews.retrofit_provider.RetrofitServiceNews;
import infobite.omnews.retrofit_provider.WebResponse;
import infobite.omnews.utils.Alerts;
import infobite.omnews.utils.BaseActivity;
import retrofit2.Response;

public class ChannelActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView lvVideo;
    private ArrayList<VideoDetails> videoDetailsArrayList;
    private CustomListAdapter customListAdapter;

    private Handler imageHandler;
    private Runnable imageRunnable;
    private ViewPager pagerSuccess;
    private BannerPagerAdapter adapter;
    private List<BannerDatum> successImagesList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        lvVideo = findViewById(R.id.videoList);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        lvVideo.setLayoutManager(new LinearLayoutManager(mContext));
        lvVideo.setItemAnimator(new DefaultItemAnimator());

        videoDetailsArrayList = new ArrayList<>();
        customListAdapter = new CustomListAdapter(videoDetailsArrayList, mContext, "");
        lvVideo.setAdapter(customListAdapter);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showVideo();
                swipeRefresh.setRefreshing(false);
            }
        });

        init();
        initPager();
        showVideo();
    }

    private void initPager() {
        pagerSuccess = findViewById(R.id.pagerSuccess);
        adapter = new BannerPagerAdapter(mContext, successImagesList, this);
        imagesApi();
        imageHandler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                marriageSlide();
            }
        };
        imageHandler.postDelayed(imageRunnable, 3000);
    }

    public void marriageSlide() {
        if (pagerSuccess == null)
            return;
        int successPos = pagerSuccess.getCurrentItem();
        successPos++;
        if (successPos != successImagesList.size()) {
            pagerSuccess.setCurrentItem(successPos);
            imageHandler.postDelayed(imageRunnable, 3000);
        } else {
            pagerSuccess.setCurrentItem(0);
            imageHandler.postDelayed(imageRunnable, 3000);
        }
    }

    private void imagesApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getBannerData(new Dialog(mContext), retrofitApiClient.banner(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    BannerModel imagesModal = (BannerModel) result.body();
                    successImagesList.clear();
                    if (imagesModal == null)
                        return;
                    successImagesList.addAll(imagesModal.getData());
                    pagerSuccess.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void init() {
        findViewById(R.id.txtSearch).setOnClickListener(this);
        findViewById(R.id.imgSearch).setOnClickListener(this);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String monthName = getMonthName(mMonth);
        String dateInCorrectFormat = (dayOfTheWeek + ", " + mDay + " " + monthName + " " + mYear);

        ((TextView) findViewById(R.id.txtDate)).setText(dateInCorrectFormat);
    }

    public static String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    private void showVideo() {
        if (cd.isNetworkAvailable()) {
            RetrofitServiceNews.getVideoData(new Dialog(mContext), retrofitApiClientNews.getVideoList(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VideoMainModal mainModal = (VideoMainModal) result.body();
                    if (mainModal != null) {
                        videoDetailsArrayList.clear();
                        if (mainModal.getItems().size() > 0) {
                            for (int i = 0; i < mainModal.getItems().size(); i++) {
                                VideoDetails videoDetails = new VideoDetails();
                                String videoid = mainModal.getItems().get(i).getId().getVideoId();
                                videoDetails.setURL(mainModal.getItems().get(i).getSnippet().getThumbnails().getMedium().getUrl());
                                videoDetails.setTime(mainModal.getItems().get(i).getSnippet().getPublishedAt());
                                videoDetails.setVideoName(mainModal.getItems().get(i).getSnippet().getTitle());
                                videoDetails.setVideoDesc(mainModal.getItems().get(i).getSnippet().getDescription());
                                videoDetails.setVideoId(videoid);
                                videoDetailsArrayList.add(videoDetails);
                            }
                            lvVideo.setAdapter(customListAdapter);
                        }
                    }
                    customListAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, SearchActivity.class);
        startActivity(intent);
    }
}
