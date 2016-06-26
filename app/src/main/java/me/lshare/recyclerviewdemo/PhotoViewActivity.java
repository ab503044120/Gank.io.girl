package me.lshare.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AppCompatActivity {

    public static final String EXTRA_IMG_ITEM = "extra_img_item";
    public static final String ACTION_MY_VIEW = "android.intent.action.MY_VIEW";
    private ImageView imageView;
    private PhotoViewAttacher mAttacher;
    private Toolbar toolbar;
    private ImgItem mImgItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_view);
        initData();
        initToolbar();
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(this).load(mImgItem.imgUrl)
//                .asBitmap()
                .dontAnimate() //禁用加载动画
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .placeholder(R.drawable.tmp)  //photoview居然使用之前的图片的比例，坑爹啊，图片被毁了
                .into(imageView);
        mAttacher = new PhotoViewAttacher(imageView);
//        mAttacher.setScale(mAttacher.getMinimumScale(),true);//解决图片没有居中的问题
    }

    private void initData() {
        mImgItem = getIntent().getParcelableExtra(EXTRA_IMG_ITEM);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mImgItem.intro);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar); //不添加这句，那么点击导航按钮失效
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewActivity.this.finish();
            }
        });
    }
}
