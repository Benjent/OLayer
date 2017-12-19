package fr.enssat.charpentiermorvan.o_layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by caillou on 12/14/17.
 */

public class TaskDownloadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public TaskDownloadImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String pathToFile = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(pathToFile).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
