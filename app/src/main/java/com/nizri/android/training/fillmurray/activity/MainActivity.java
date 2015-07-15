package com.nizri.android.training.fillmurray.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.nizri.android.training.fillmurray.R;
import com.nizri.android.training.fillmurray.api.model.Image;
import com.nizri.android.training.fillmurray.service.ImageService;
import com.nizri.android.training.fillmurray.service.ImageType;
import com.squareup.picasso.Picasso;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.imageView) ImageView imageView;

    ImageService imageService = new ImageService();

    @OnClick(R.id.random)
    public void onRandomButtonClicked() {
        Intent intent = new Intent(this, ImageListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        loadRandomImage();
    }

    private void loadRandomImage() {
        imageService.getImage(ImageType.RANDOM, new Callback<Image>() {
            @Override
            public void success(Image image, Response response) {
                String url = image.getUrl();

                Picasso.with(MainActivity.this)
                        .load(url)
                        .fit()
                        .centerInside()
                        .into(imageView);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
