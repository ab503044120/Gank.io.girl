package me.lshare.recyclerviewdemo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Lshare on 2016/6/26.
 */
public class ToastUtil {
    private static Context context;
    private static Toast toast;
    private static ToastUtil singleInstance;

    private ToastUtil() {
        this.context = MyApplication.context;
    }

    public static ToastUtil getInstance() {
        if (singleInstance == null) {
            synchronized (ToastUtil.class) {
                if (singleInstance == null) {
                    singleInstance = new ToastUtil();
                }
            }
        }
        return singleInstance;
    }

    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.cancel();
            toast.setText(text);
        }
        toast.show();
    }

    public static void showToast(int textId) {
        toast.cancel();
        if (toast == null) {
            toast.cancel();
            toast = Toast.makeText(context, textId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(textId);
        }
        toast.show();
    }
}
