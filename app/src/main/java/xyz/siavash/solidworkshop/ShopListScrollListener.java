package xyz.siavash.solidworkshop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ShopListScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = ShopListScrollListener.class.getSimpleName();

private int previousTotal = 0; // The total number of items in the dataset after the last load
private boolean loading = true; // True if we are still waiting for the last set of data to load.
private int visibleThreshold = 2; // The minimum amount of items to have below your current scroll position before loading more.
int firstVisibleItem, visibleItemCount, totalItemCount;

private int current_page = 1;

private LinearLayoutManager mLinearLayoutManager;
  private ShopListInterface callback;

  public ShopListScrollListener(LinearLayoutManager linearLayoutManager, ShopListInterface callback) {
    this.mLinearLayoutManager = linearLayoutManager;
    this.callback = callback;
  }

@Override
public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    visibleItemCount = recyclerView.getChildCount();
    totalItemCount = mLinearLayoutManager.getItemCount();
    firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

    if (loading) {
        if (totalItemCount > previousTotal) {
            loading = false;
            previousTotal = totalItemCount;
        }
    }
    if (!loading && (totalItemCount - visibleItemCount)
            <= (firstVisibleItem + visibleThreshold)) {
        // End has been reached

        // Do something
        current_page++;

        callback.onLoadMore(current_page);

        loading = true;
    }
}
}