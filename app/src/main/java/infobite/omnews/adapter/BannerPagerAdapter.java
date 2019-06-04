package infobite.omnews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import infobite.omnews.R;
import infobite.omnews.modal.banner_model.BannerDatum;

public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<BannerDatum> searchArrayList;
    private View.OnClickListener onClickListener;

    public BannerPagerAdapter(Context context, List<BannerDatum> searchArrayList, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.searchArrayList = searchArrayList;
        this.onClickListener = onClickListener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.slide_show_pager_item, container, false);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        String strUrl = searchArrayList.get(position).getOfferPicture();
        Glide.with(mContext).load(strUrl).placeholder(R.drawable.ic_default_photo_b).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
