package co.astrnt.demosdk.dao;

public class VideoInfo {

    private int width;
    private int height;
    private int bitrate;

    public VideoInfo(int width, int height, int bitrate) {
        this.width = width;
        this.height = height;
        this.bitrate = bitrate;
    }

    public VideoInfo() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }
}
