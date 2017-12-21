package fr.enssat.charpentiermorvan.o_layer;

import android.os.Handler;
import android.webkit.WebView;
import android.widget.VideoView;

/**
 * Check the video current time and update necessary elements
 */

public class CheckVideoThread extends Thread {
    private VideoView videoView;
    private VideoMetadata videoMetadata;
    private WebView wikiView;
    private Handler handler;
    private int currentTagIndex;
    private String currentTagUrl;

    /**
     * @param videoView the VideoView to check
     * @param videoMetadata the video metadata
     * @param wikiView the WebView to update
     * @param handler the handler used to update the UI
     */
    public CheckVideoThread(VideoView videoView, VideoMetadata videoMetadata, WebView wikiView, Handler handler) {
        this.videoView = videoView;
        this.videoMetadata = videoMetadata;
        this.wikiView = wikiView;
        this.handler = handler;
        this.currentTagIndex = -1;
        this.currentTagUrl = videoMetadata.getPageUrl();
    }

    @Override
    public void run() {
        try {
            while(true) {
                // Run this every 100ms
                sleep(100);

                // Get current position in the video
                int currentTime = this.videoView.getCurrentPosition();

                int i = -1;
                for(Tag tag : this.videoMetadata.getTags()) {
                    if (tag.getTimeStamp() - 1 >= currentTime / 1000) {
                        break;
                    }

                    i++;
                }

                // Check if new computed tag has already been loaded in the WebView
                if (currentTagIndex != i) {
                    currentTagIndex = i;

                    // Set the url to either the video url or the tag url base on the index of the tag
                    if (i >= 0) {
                        currentTagUrl = videoMetadata.getTags().get(i).getUrl();
                    } else {
                        currentTagUrl = videoMetadata.getPageUrl();
                    }

                    // Update the WebView using the Handler
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            wikiView.loadUrl(currentTagUrl);
                        }
                    });
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
