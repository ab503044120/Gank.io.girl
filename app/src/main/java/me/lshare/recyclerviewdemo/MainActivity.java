package me.lshare.recyclerviewdemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 0. 加载更多问题
 * 1. 做一个本地缓存
 * 2. Glide：图片变形了，禁用加载动画，解决
 * 3. 进入时空白页面，没有显示加载提示
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private List<ImgItem> itemList = new ArrayList<>();
    private int page = 1;
    private final int COUNT_PER_LOAD = 8;
    private String BASE_URL = "http://gank.io/api/search/query/listview/category/%E7%A6%8F%E5%88%A9/count/" + COUNT_PER_LOAD + "/page/";
    private MyRecyclerAdapter mRecyclerAdapter;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        initView();
        onRefresh();
    }

    private void loadData(final int page) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(BASE_URL + page).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.getInstance().showToast("网络开小差啦(T_T)~");
//                        Toast.makeText(MainActivity.this, "网络开小差啦(T_T)~", Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ResponseItem responseItem = gson.fromJson(response.body().string(), ResponseItem.class);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //每次加载失败都吐司，很烦，能不能先清除上次吐司
                if (responseItem.isError()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.getInstance().showToast("网络开小差啦(T_T)~");
//                            Toast.makeText(MainActivity.this, "网络开小差啦(T_T)~", Toast.LENGTH_SHORT).show();
                            refreshLayout.setRefreshing(false);
                        }
                    });
                    return;
                }

                List<ResponseItem.ResultsBean> results = responseItem.getResults();

                if (results.size() < 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //这里有问题
//                            Toast.makeText(MainActivity.this, "客官，没有更多了~", Toast.LENGTH_SHORT).show();
//                            refreshLayout.setRefreshing(false);
                        }
                    });
                    return;
                }
                for (int i = 0; i < results.size(); i++) {
                    ImgItem imgItem = new ImgItem();
                    imgItem.imgUrl = results.get(i).getUrl();
                    imgItem.intro = results.get(i).getWho();
                    itemList.add(imgItem);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (page == 1) {
                            Toast.makeText(MainActivity.this, "刷新成功( •̀ ω •́ )y", Toast.LENGTH_SHORT).show();
                        } else {
//                            Toast.makeText(MainActivity.this, "加载成功( •̀ ω •́ )y", Toast.LENGTH_SHORT).show();
                        }
                        refreshLayout.setRefreshing(false);
                        notifyDataSetChanged();

                    }
                });
            }
        });
    }

    private void notifyDataSetChanged() {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));
        refreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerAdapter = new MyRecyclerAdapter(itemList);
        recyclerView.setAdapter(mRecyclerAdapter);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //判断是否拉到底部
//                int visibleChildCount = recyclerView.getChildCount();
//                int totalChildCount = mLinearLayoutManager.getChildCount();
                int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

//                Log.e("C","visibleChildCount:"+visibleChildCount
//                        +"\ntotalChildCount:"+totalChildCount
//                        +"\nlastVisibleItemPosition:"+lastVisibleItemPosition);

                if ((lastVisibleItemPosition + 1) % COUNT_PER_LOAD == 0) {
                    loadMore();
                }
            }
        });
    }

    /**
     * 刷新数据
     */
    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        itemList.clear();
        page = 1;
        refreshLayout.setRefreshing(true);
        loadData(page);
    }

    /**
     * 加载更多数据
     */
    private void loadMore() {
        refreshLayout.setRefreshing(false);
        page = page + 1;
        loadData(page);
    }
}
