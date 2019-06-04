package infobite.omnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import infobite.omnews.R;
import infobite.omnews.ui.VideoActivity;
import infobite.omnews.ui.VideoDetails;
import infobite.omnews.utils.Alerts;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.MyViewHolder> {

    private List<VideoDetails> reviewModelList;
    private Context context;
    private String from;

    public CustomListAdapter(List<VideoDetails> reviewModelList, Context context, String from) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        this.from = from;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.videolist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        final VideoDetails singleton = reviewModelList.get(i);
        //holder.tvVideoID.setText(singleton.getVideoId());
        holder.imgtitle.setText(singleton.getVideoName());
        holder.imgdesc.setText(singleton.getVideoDesc());
        String dateStr = singleton.getTime();

        holder.tvTime.setText(dateTimeFormat(dateStr));

        Glide.with(context)
                .load(singleton.getURL())
                .into(holder.imgNews);

        if (i == 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (from.isEmpty()) {
                params.setMargins(16, 16, 16, 16);
            } else {
                params.setMargins(16, 56, 16, 16);
            }
            holder.llMain.setLayoutParams(params);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(16, 16, 16, 16);
            holder.llMain.setLayoutParams(params);
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoId = singleton.getVideoId();

                if (videoId == null) {
                    Alerts.show(context, "This video can't be played right now...!!!");
                } else {
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("videoId", singleton.getVideoId());
                    intent.putExtra("title", singleton.getVideoName());
                    intent.putExtra("desc", singleton.getVideoDesc());
                    context.startActivity(intent);
                }
            }
        });
    }

    private String dateTimeFormat(String date) {
        String timeAgo;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        Date past = null;
        try {
            past = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
        long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
        long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());
        if (seconds < 60) {
            timeAgo = (seconds + " seconds ago");
        } else if (minutes < 60) {
            timeAgo = (minutes + " minutes ago");
        } else if (hours < 24) {
            timeAgo = (hours + " hours ago");
        } else {
            timeAgo = (days + " days ago");
        }

        return timeAgo;
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgNews;
        private TextView imgtitle;
        private TextView imgdesc;
        private TextView tvTime;
        private LinearLayout llMain;

        public MyViewHolder(View view) {
            super(view);
            imgNews = view.findViewById(R.id.imgNews);
            imgtitle = view.findViewById(R.id.video_title);
            imgdesc = view.findViewById(R.id.video_descriptio);
            tvTime = view.findViewById(R.id.tvTime);
            llMain = view.findViewById(R.id.llMain);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
