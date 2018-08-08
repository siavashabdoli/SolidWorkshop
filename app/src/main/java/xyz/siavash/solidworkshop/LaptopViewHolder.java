package xyz.siavash.solidworkshop;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

class LaptopViewHolder extends RecyclerView.ViewHolder{
  @BindView(R.id.tv_title)
  public TextView tvTitle;
  @BindView(R.id.tv_cpu)
  public TextView tvCPU;
  @BindView(R.id.tv_graphic)
  public TextView tvGraphic;

  public LaptopViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this,itemView);
  }
}
