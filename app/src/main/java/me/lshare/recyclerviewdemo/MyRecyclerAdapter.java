package me.lshare.recyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<ImgItem> itemList = new ArrayList<>();
    private Context context;

    public MyRecyclerAdapter(List<ImgItem> itemList) {
        itemList.clear();
        this.itemList = itemList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.recyler_item, parent, false);
        ItemHolder vh = new ItemHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        holder.introView.setText(itemList.get(position).intro);
        Glide.with(context).load(itemList.get(position).imgUrl)
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.tmp)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .into(new MyBitmapImageViewTarget(holder.imgView));
//                .dontAnimate() //禁用加载动画
                .into(holder.imgView);


        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context,PhotoViewActivity.class);
//                intent.putExtra(PhotoViewActivity.EXTRA_IMG_URL,itemList.get(position).imgUrl);
//                context.startActivity(intent);
                try {
                    Intent intent = new Intent();
                    intent.putExtra(PhotoViewActivity.EXTRA_IMG_ITEM, itemList.get(position));
                    intent.setAction(PhotoViewActivity.ACTION_MY_VIEW);
                    intent.setType("image/*");
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

class ItemHolder extends RecyclerView.ViewHolder {
    ImageView imgView;
    TextView introView;

    public ItemHolder(View itemView) {
        super(itemView);
        imgView = (ImageView) itemView.findViewById(R.id.item_img);
        introView = (TextView) itemView.findViewById(R.id.item_intro);
    }
}