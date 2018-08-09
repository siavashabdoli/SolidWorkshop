package xyz.siavash.solidworkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopListActivity extends AppCompatActivity implements ShopListInterface {
  GoodsAdapter goodsAdapter;
  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  private int currentPage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    goodsAdapter = new GoodsAdapter(this);
    LinearLayoutManager ll = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(ll);
    recyclerView.setAdapter(goodsAdapter);
    recyclerView.setOnScrollListener(new ShopListScrollListener(ll, this) {

    });

  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @OnClick(R.id.loadButton)
  public void onLoadCLicked() {
    findViewById(R.id.loadButton).setVisibility(View.GONE);
    loadNewData();
  }

  @Override
  public void onLoadMore(int currentPage) {
    ShopListActivity.this.currentPage = currentPage;
    loadNewData();
  }


  public void loadNewData() {
    DataProvider dataProvider = new DataProvider(getApplicationContext());
    dataProvider.getGoods(new DataProvider.Callback() {
      @Override
      public void onResponse(ArrayList<GoodsItem> goodsItems) {
        if (currentPage == 1) {
          goodsAdapter.updateData(goodsItems);
        } else {
          goodsAdapter.addData(goodsItems);
        }
      }
    }, currentPage);
  }
}
