package fr.enssat.charpentiermorvan.o_layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * AsyncTask allowing to download an image and apply it to an ImageView background
 */
public class TaskDownloadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    /**
     * @param bmImage an ImageVIew
     */
    public TaskDownloadImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String pathToFile = urls[0];
        Bitmap bitmap = null;

        try {
            // Open image InputStream
            InputStream in = new java.net.URL(pathToFile).openStream();

            // Decode bitmap from InputStream
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        // Set the background image of the ImageView
        bmImage.setImageBitmap(result);
    }
}
