package com.nizri.android.training.fillmurray.service;

import java.util.List;

import com.nizri.android.training.fillmurray.api.TrainingApi;
import com.nizri.android.training.fillmurray.api.model.Image;
import retrofit.Callback;
import retrofit.RestAdapter;

public class ImageService {

    private static final String BASE_API_URL = "http://niz-training.ngrok.io";
    private static final String BASE_API_URL_GENYMOTION = "http://10.0.3.2:8080";

    private final TrainingApi trainingApi = new RestAdapter.Builder()
            .setEndpoint(BASE_API_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build()
            .create(TrainingApi.class);

    public void getImage(ImageType imageType, Callback<Image> callback) {
        trainingApi.getImage(imageType.getType(), callback);
    }


    public void getImages(ImageType imageType, Integer count, Callback<List<Image>> callback) {
        trainingApi.getImages(imageType.getType(), count, callback);
    }

    /*
     * Example of non Retrofit HTTP code
     */
//    private void makeNonBlockingApiCall() {
//        new AsyncTask<String, Void, String>() {
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                return makeBlockingApiCall(params[0]);
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                // do something with result (update UI)
//            }
//        }.execute(BASE_API_URL);
//    }
//
//    private String makeBlockingApiCall(String url) {
//        StringBuilder response = new StringBuilder();
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        try {
//            urlConnection = (HttpURLConnection) new URL(url).openConnection();
//            if (urlConnection.getResponseCode() == 200) {
//                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                IOUtils.closeQuietly(reader);
//            }
//        }
//        return response.toString();
//    }
}
