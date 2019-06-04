package infobite.omnews.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import infobite.omnews.R;
import infobite.omnews.modal.video_detail.VideoDetailMainModal;
import infobite.omnews.retrofit_provider.RetrofitApiClient;
import infobite.omnews.retrofit_provider.RetrofitServiceNews;
import infobite.omnews.retrofit_provider.WebResponse;
import infobite.omnews.utils.Alerts;
import infobite.omnews.utils.ConnectionDetector;
import retrofit2.Response;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView youTubePlayerView;
    String API_KEY = "AIzaSyDo1PhoE8cNp2rH6VL7YCWCWz7801YZ4Hs";
    private static final int RECOVERY_REQUEST = 1;
    String TAG = "VideoActivity";
    String title = "", desc = "";
    private Context mContext;
    public ConnectionDetector cd;
    public RetrofitApiClient retrofitApiClientNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClientNews = RetrofitServiceNews.getRetrofit();
        youTubePlayerView = findViewById(R.id.youtubeview);
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Bundle bundle = getIntent().getExtras();
        String showVideo = bundle.getString("videoId");
        if (showVideo == null) {
            Alerts.show(mContext, "Video Id not found...!!!");
        } else {
            Log.e(TAG, "Video" + showVideo);
            youTubePlayer.cueVideo(showVideo);
        }

        showVideo(showVideo);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            Toast.makeText(this, "Error Intializing Youtube Player", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubePlayerView;
    }

    private void showVideo(String videoId) {
        /*maxResults=10&*/
        String URL = "youtube/v3/videos?part=snippet&id=" + videoId + "&key=AIzaSyDo1PhoE8cNp2rH6VL7YCWCWz7801YZ4Hs";
        if (cd.isNetworkAvailable()) {
            RetrofitServiceNews.getVideoDetail(new Dialog(mContext), retrofitApiClientNews.videoDetail(URL), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VideoDetailMainModal mainModal = (VideoDetailMainModal) result.body();
                    if (mainModal != null) {
                        if (mainModal.getItems().size() > 0) {
                            title = mainModal.getItems().get(0).getSnippet().getTitle();
                            desc = mainModal.getItems().get(0).getSnippet().getDescription();
                            if (desc != null) {
                                ((TextView) findViewById(R.id.txtDescription)).setText(desc);
                            }
                            if (title != null) {
                                ((TextView) findViewById(R.id.txtTitle)).setText(title);
                            }
                        }
                    }
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
