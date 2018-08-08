package xyz.siavash.solidworkshop;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

class MobileViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.tv_title)
  TextView tvTitle;
  @BindView(R.id.tv_os)
  TextView tvOs;
  public MobileViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this,itemView);
  }
}
