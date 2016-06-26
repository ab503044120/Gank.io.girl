package me.lshare.recyclerviewdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lshare on 2016/6/25.
 */
public class ImgItem implements Parcelable {
    public String imgUrl;
    public String intro;

    public ImgItem(){}

    protected ImgItem(Parcel in) {
        imgUrl = in.readString();
        intro = in.readString();
    }

    public static final Creator<ImgItem> CREATOR = new Creator<ImgItem>() {
        @Override
        public ImgItem createFromParcel(Parcel in) {
            return new ImgItem(in);
        }

        @Override
        public ImgItem[] newArray(int size) {
            return new ImgItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgUrl);
        dest.writeString(intro);
    }
}
