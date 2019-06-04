package infobite.omnews.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import infobite.omnews.R;
import infobite.omnews.adapter.CustomListAdapter;
import infobite.omnews.modal.video_data.VideoMainModal;
import infobite.omnews.retrofit_provider.RetrofitServiceNews;
import infobite.omnews.retrofit_provider.WebResponse;
import infobite.omnews.utils.Alerts;
import infobite.omnews.utils.BaseActivity;
import retrofit2.Response;

public class SearchActivity extends BaseActivity {

    EditText etSearch;
    RecyclerView lvVideo;
    ArrayList<VideoDetails> videoDetailsArrayList;
    CustomListAdapter customListAdapter;
    String searchName;
    String TAG = "SearchActivity";
    String strFrom = "search";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etSearch = findViewById(R.id.edt_search);
        lvVideo = findViewById(R.id.videoList);
        lvVideo.setLayoutManager(new LinearLayoutManager(mContext));
        lvVideo.setItemAnimator(new DefaultItemAnimator());

        videoDetailsArrayList = new ArrayList<>();
        customListAdapter = new CustomListAdapter(videoDetailsArrayList, mContext, strFrom);
        lvVideo.setAdapter(customListAdapter);

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchName = etSearch.getText().toString();
                showVideo();
            }
        });
    }

    private void showVideo() {
        /*maxResults=10&*/
        //String URL = "youtube/v3/search?part=snippet&maxResults=10&q=" + searchName + "&key=AIzaSyDo1PhoE8cNp2rH6VL7YCWCWz7801YZ4Hs";
        String URL = "youtube/v3/search?part=snippet&channelId=UCuua4iafV54GTzwLhcQaTaw&q=" + searchName + "&key=AIzaSyDo1PhoE8cNp2rH6VL7YCWCWz7801YZ4Hs";
        if (cd.isNetworkAvailable()) {
            RetrofitServiceNews.getVideoData(new Dialog(mContext), retrofitApiClientNews.channelData(URL), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VideoMainModal mainModal = (VideoMainModal) result.body();
                    if (mainModal != null) {
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
}
