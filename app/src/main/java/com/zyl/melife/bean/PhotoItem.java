package com.zyl.melife.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author tongqian.ni
 *
 */
public class PhotoItem implements Parcelable, Comparable<PhotoItem> {

    private String imageUri;
    private long    date;
    private String dateStr;

    public PhotoItem(String uri, long date) {
        this.imageUri = uri;
        this.date = date;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUri);
        dest.writeLong(date);
    }

    public static final Creator<PhotoItem> CREATOR = new Creator<PhotoItem>() {
                                                                  @Override
                                                                  public PhotoItem[] newArray(int size) {
                                                                      return new PhotoItem[size];
                                                                  }

                                                                  @Override
                                                                  public PhotoItem createFromParcel(Parcel in) {
                                                                      return new PhotoItem(in);
                                                                  }
                                                              };

    public PhotoItem(Parcel in) {
        imageUri = in.readString();
        date = in.readLong();
    }

    @Override
    public int compareTo(PhotoItem another) {
        if (another == null) {
            return 1;
        }
        return (int) ((another.getDate() - date) / 1000);
    }
}
