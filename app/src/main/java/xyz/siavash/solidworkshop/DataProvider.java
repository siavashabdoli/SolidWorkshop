package xyz.siavash.solidworkshop;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;

class DataProvider {
  private Callback callback;
  private Context context;

  public DataProvider(Context context) {
    this.context = context;
  }

  public void getGoods(final Callback callback, int pageNumber) {
    if(pageNumber > 4) {
      callback.onResponse(new ArrayList<GoodsItem>());
      return;
    }
    this.callback = callback;
    final Handler handler = new Handler();
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        String result = loadJSONFromAsset();
        final GoodsRequestResponse response = new Gson().fromJson(result, GoodsRequestResponse.class);
        handler.post(new Runnable() {
          @Override
          public void run() {
            callback.onResponse(response.items);
          }
        });
      }
    });
    thread.start();
  }

  public interface Callback {
    void onResponse(ArrayList<GoodsItem> goodsItems);
  }
  public  String loadJSONFromAsset() {
    String json;
    try {
      InputStream is = context.getAssets().open("test_data.json");

      int size = is.available();

      byte[] buffer = new byte[size];

      is.read(buffer);

      is.close();

      json = new String(buffer, "UTF-8");


    } catch (Exception ex) {
      ex.printStackTrace();
      return "";
    }
    return json;

  }
}
