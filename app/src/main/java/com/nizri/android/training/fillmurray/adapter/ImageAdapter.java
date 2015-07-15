package com.nizri.android.training.fillmurray.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.nizri.android.training.fillmurray.ImageLoaderUtils;
import com.nizri.android.training.fillmurray.R;
import com.nizri.android.training.fillmurray.api.model.Image;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Image> imageList;
    private ImageLoaderUtils imageLoaderUtils;

    public ImageAdapter(Context context) {
        this.context = context;
        this.imageList = new ArrayList<>();
        this.imageLoaderUtils = new ImageLoaderUtils();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_list_item, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Image image = imageList.get(position);

        ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;
        imageLoaderUtils.loadImage(context, image, imageViewHolder.imageView, imageViewHolder.title);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void addImages(Collection<Image> imagesToAdd) {
        int initialSize = imageList.size();
        imageList.addAll(imagesToAdd);
        notifyItemInserted(initialSize);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.imageView) ImageView imageView;
        @InjectView(R.id.title) TextView title;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
