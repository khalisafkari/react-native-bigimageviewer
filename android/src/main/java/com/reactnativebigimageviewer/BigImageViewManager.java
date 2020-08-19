package com.reactnativebigimageviewer;

import android.net.Uri;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.indicator.progresspie.ProgressPieIndicator;
import com.github.piasy.biv.loader.ImageLoader;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;

import java.io.File;
import java.util.Map;

public class BigImageViewManager extends SimpleViewManager<BigImageView> {

  public enum Event {
    onCacheHit("onCacheHit"),
    onCacheMiss("onCacheMiss"),
    onStart("onStart"),
    onProgress("onProgress"),
    onFinish("onFinish"),
    onSuccess("onSuccess"),
    onFail("onFail");

    private String string;
    Event(String name) {
      this.string = name;
    }

    public String getString() {
      return string;
    }
  }

  private static String TAG = "BigImageViewer";
  private static String NAME = "RNBigImageViewer";
  private BigImageView bigImageView;
  private ReactApplicationContext mContext;

  @NonNull
  @Override
  public String getName() {
    return NAME;
  }

  public BigImageViewManager(ReactApplicationContext reactApplicationContext) {
    mContext = reactApplicationContext;
    BigImageViewer.initialize(GlideImageLoader.with(reactApplicationContext));
  }

  @NonNull
  @Override
  protected BigImageView createViewInstance(@NonNull ThemedReactContext reactContext) {
    bigImageView = new BigImageView(reactContext);
    return bigImageView;
  }

  private void SendEvent(Event event, WritableMap wm) {
      mContext.getJSModule(RCTEventEmitter.class).receiveEvent(bigImageView.getId(),event.getString(),wm);
  }

  @ReactProp(name = "source")
  public void setSource(BigImageView imageView,String i) {
    imageView.showImage(Uri.parse(i));
        bigImageView.setImageLoaderCallback(new ImageLoader.Callback() {
          @Override
          public void onCacheHit(int imageType, File image) {
             SendEvent(Event.onCacheHit,null);
          }

          @Override
          public void onCacheMiss(int imageType, File image) {
            SendEvent(Event.onCacheMiss,null);
          }

          @Override
          public void onStart() {
            SendEvent(Event.onStart,null);
          }

          @Override
          public void onProgress(int progress) {
            WritableMap map = new WritableNativeMap();
            map.putInt("progress",progress);
            SendEvent(Event.onProgress,map);
          }

          @Override
          public void onFinish() {
            SendEvent(Event.onFinish,null);
          }

          @Override
          public void onSuccess(File image) {
            SendEvent(Event.onSuccess,null);
          }

          @Override
          public void onFail(Exception error) {
            WritableMap map = new WritableNativeMap();
            map.putString("error",error.getMessage());
            SendEvent(Event.onFail,map);
          }
        });
  }

  @ReactProp(name = "loading")
  public void setLoading(BigImageView view,Boolean i) {
    if (i == true) {
      view.setProgressIndicator(new ProgressPieIndicator());
    }
  }

  @ReactProp(name = "resizeMode")
  public void setresizeMode(BigImageView view,String i) {
      switch (i) {
        case "center":
          view.setInitScaleType(BigImageView.INIT_SCALE_TYPE_CENTER);
          break;
        case "centerCrop":
          view.setInitScaleType(BigImageView.INIT_SCALE_TYPE_CENTER_CROP);
          break;
        case "centerInside":
          view.setInitScaleType(BigImageView.INIT_SCALE_TYPE_CENTER_INSIDE);
          break;
        case "fitCenter":
          view.setInitScaleType(BigImageView.INIT_SCALE_TYPE_FIT_CENTER);
          break;
        case "fitEnd":
          view.setInitScaleType(BigImageView.INIT_SCALE_TYPE_FIT_END);
          break;
        case "fitStart":
          view.setInitScaleType(BigImageView.INIT_SCALE_TYPE_FIT_START);
          break;
        case "fitXY":
          view.setInitScaleType(BigImageView.INIT_SCALE_TYPE_FIT_XY);
          break;
        case "start":
          view.setInitScaleType(BigImageView.INIT_SCALE_TYPE_START);
          break;
        default:
          return;
      }
  }



  @Nullable
  @Override
  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
    Event[] events = Event.values();
    String[] event = new String[events.length];
    for (int i = 0;i < events.length;i++) {
      event[i] = events[i].getString();
    }
    return getExportedCustomDirectEventT(event);
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  public static Map<String,Object> getExportedCustomDirectEventT (String[] events) {
     Map<String, Object> obj = new ArrayMap<>();
    for (String event:events) {
      obj.put(event, MapBuilder.of("registrationName",event));
    }
    return obj;
  }
}
