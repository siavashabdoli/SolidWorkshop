package xyz.siavash.solidworkshop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;

class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  LinkedList<GoodsItem> goodsItems = new LinkedList<>();
  private Context context;

  public GoodsAdapter(Context context) {
    this.context = context;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == 1) {
      View view = LayoutInflater.from(context).inflate(R.layout.goods_row_mobile, parent, false);
      return new MobileViewHolder(view);
    } else {
      View view = LayoutInflater.from(context).inflate(R.layout.goods_row_laptop, parent, false);
      return new LaptopViewHolder(view);
    }
  }


  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    GoodsItem goodsItem = goodsItems.get(position);
    switch (getItemViewType(position)) {
      case 1:
        MobileViewHolder mobileViewHolder = (MobileViewHolder) holder;
        mobileViewHolder.tvTitle.setText(goodsItem.getTitle());
        mobileViewHolder.tvOs.setText(goodsItem.getOsName());
        break;
      case 2:
        LaptopViewHolder laptopViewHolder = (LaptopViewHolder) holder;
        laptopViewHolder.tvTitle.setText(goodsItem.getTitle());
        laptopViewHolder.tvCPU.setText(goodsItem.getCpuModel());
        laptopViewHolder.tvGraphic.setText(goodsItem.getGraphicCardModel());
        break;
      default:
        throw new IllegalArgumentException("Type is undefined");
    }
  }

  @Override
  public int getItemViewType(int position) {
    return goodsItems.get(position).getType();
  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   *
   * @return The total number of items in this adapter.
   */

  @Override
  public int getItemCount() {
    return goodsItems.size();
  }

  public void updateData(ArrayList<GoodsItem> goodsItems) {
    this.goodsItems.clear();
    this.goodsItems.addAll(goodsItems);
    notifyDataSetChanged();
  }
}
