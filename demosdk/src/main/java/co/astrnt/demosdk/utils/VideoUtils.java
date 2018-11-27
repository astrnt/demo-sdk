package co.astrnt.demosdk.utils;

import android.media.MediaMetadataRetriever;

import java.io.File;

import co.astrnt.demosdk.dao.VideoInfo;

public class VideoUtils {

    static final int COMPRESS_QUALITY_MEDIUM = 2;
    static final int COMPRESS_QUALITY_LOW = 3;
    private final static int MAX_VIDEO_SIZE = 600;

    public VideoInfo getVideoInfo(String srcPath, String destPath) {

        File file = new File(srcPath);
        int fileSize = Integer.parseInt(String.valueOf(file.length() / 1024)) / 1000;
        int quality;
        if (fileSize <= 30) {
            quality = COMPRESS_QUALITY_MEDIUM;
        } else {
            quality = COMPRESS_QUALITY_LOW;
        }

        if (srcPath == null) {
            return null;
        }

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        if (retriever == null) {
            return null;
        }

        retriever.setDataSource(destPath);

        String width = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
        String height = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);

        if (width == null) {
            width = "480";
        }

        if (height == null) {
            height = "720";
        }
        int originalWidth = Integer.valueOf(width);
        int originalHeight = Integer.valueOf(height);

        int resultWidth;
        int resultHeight;
        int bitrate;

        switch (quality) {
            default:
            case COMPRESS_QUALITY_MEDIUM:
                resultWidth = originalWidth / 2;
                resultHeight = originalHeight / 2;
                bitrate = resultWidth * resultHeight * 10;
                break;
            case COMPRESS_QUALITY_LOW:
                int tempWidth = originalWidth / 2;
                int tempHeight = originalHeight / 2;

                if (tempWidth > MAX_VIDEO_SIZE || tempHeight > MAX_VIDEO_SIZE) {
                    tempWidth = originalWidth / 3;
                    tempHeight = originalHeight / 3;
                }

                resultWidth = tempWidth;
                resultHeight = tempHeight;

                bitrate = resultWidth * resultHeight * 4;
                break;
        }

        return new VideoInfo(resultWidth, resultHeight, bitrate);
    }

}
