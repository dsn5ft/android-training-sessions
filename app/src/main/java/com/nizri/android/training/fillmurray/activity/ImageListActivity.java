package com.nizri.android.training.fillmurray.activity;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.common.collect.Lists;
import com.nizri.android.training.fillmurray.R;
import com.nizri.android.training.fillmurray.adapter.ImageAdapter;
import com.nizri.android.training.fillmurray.api.model.Image;
import com.nizri.android.training.fillmurray.service.ImageService;
import com.nizri.android.training.fillmurray.service.ImageType;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ImageListActivity extends AppCompatActivity {

    private ImageType imageType = ImageType.RANDOM;
    private ImageService imageService;
    private StaggeredGridLayoutManager layoutManager;
    private ImageAdapter imageAdapter;

    @InjectView(R.id.imageRecyclerView) RecyclerView imageRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        ButterKnife.inject(this);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        imageAdapter = new ImageAdapter(this);

        imageRecyclerView.setLayoutManager(layoutManager);
        imageRecyclerView.setAdapter(imageAdapter);
        imageRecyclerView.setHasFixedSize(true);

        imageService = new ImageService();

        imageService.getImages(imageType, 100, new Callback<List<Image>>() {
            @Override
            public void success(List<Image> images, Response response) {
                imageAdapter.addImages(images);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
