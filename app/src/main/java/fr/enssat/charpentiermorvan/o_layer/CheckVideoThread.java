package fr.enssat.charpentiermorvan.o_layer;

import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.widget.VideoView;

import java.util.ArrayList;

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

    CheckVideoThread(VideoView videoView, VideoMetadata videoMetadata, WebView wikiView, Handler handler) {
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
                sleep(100);
                int currentTime = this.videoView.getCurrentPosition();

                int i = -1;

                for(Tag tag : this.videoMetadata.getTags()) {
                    if (tag.getTimeStamp() - 1 >= currentTime / 1000) {
                        break;
                    }

                    i++;
                }

                if (currentTagIndex != i) {
                    currentTagIndex = i;
                    if (i >= 0) {
                        currentTagUrl = videoMetadata.getTags().get(i).getUrl();
                    } else {
                        currentTagUrl = videoMetadata.getPageUrl();
                    }

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
