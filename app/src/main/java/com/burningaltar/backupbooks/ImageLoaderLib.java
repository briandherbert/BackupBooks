package com.burningaltar.backupbooks;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bherbert on 5/16/16.
 */
public class ImageLoaderLib {
    static final String TAG = ImageLoaderLib.class.getSimpleName();

    public enum ImageLoaderType {
        picasso,
        fresco;
    }

    private static ImageLoader sImageLoader = null;

    public static void init(Context context) {
        Log.v(TAG, "init with type " + Constants.IMAGE_LOADER_TYPE);

        switch (Constants.IMAGE_LOADER_TYPE) {
            case picasso:
                sImageLoader = new PicassoImageLoader(context);
                break;

            case fresco:
                sImageLoader = new FrescoImageLoader(context);
        }
    }

    public static abstract class ImageLoader {
        final Context mContext;

        public ImageLoader(Context context) {
            mContext = context;
        }

        public abstract void loadImage(ViewGroup parent, String url, int width, int height);

        public abstract ImageLoaderType getType();
    }

    public static class FrescoImageLoader extends ImageLoader {

        public FrescoImageLoader(Context context) {
            super(context);

            Set<RequestListener> requestListeners = new HashSet<>();
            requestListeners.add(new RequestLoggingListener());
            ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                    // other setters
                    .setRequestListeners(requestListeners)
                    .build();
            Fresco.initialize(context, config);
            FLog.setMinimumLoggingLevel(FLog.VERBOSE);

            //Fresco.initialize(context);
        }

        @Override
        public void loadImage(ViewGroup parent, String url, int width, int height) {
            Log.v("blarg", "load fresco image");
            View img = parent.getChildAt(0);

            if (img == null || !(img instanceof SimpleDraweeView)) {
                parent.removeAllViews();
                img = new SimpleDraweeView(mContext);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                img.setLayoutParams(lp);
                parent.addView(img);
            }

            DraweeView imgDV = (SimpleDraweeView) img;

            imgDV.setHierarchy(new GenericDraweeHierarchyBuilder(mContext.getResources())
                    .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());

            ImageRequest requestBuilder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                    .setProgressiveRenderingEnabled(true)
                    .build();

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(requestBuilder)
                    .build();

            imgDV.setController(controller);
        }

        @Override
        public ImageLoaderType getType() {
            return ImageLoaderType.fresco;
        }
    }

    public static class PicassoImageLoader extends ImageLoader {
        Picasso mPicasso;

        public PicassoImageLoader(Context context) {
            super(context);

            mPicasso = Picasso.with(context);
        }

        @Override
        public void loadImage(ViewGroup parent, String url, int width, int height) {
            View img = parent.getChildAt(0);

            if (img == null || !(img instanceof ImageView)) {
                parent.removeAllViews();

                img = new ImageView(mContext);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                img.setLayoutParams(lp);
                parent.addView(img);
            }

            mPicasso.load(url)
                    .resize(width, height)
                    .centerInside()
                    .onlyScaleDown()
                    .error(new ColorDrawable(Color.RED))
                    .into((ImageView) img);
        }

        @Override
        public ImageLoaderType getType() {
            return ImageLoaderType.picasso;
        }
    }

    enum ImgurImageSize {
        normal("", 9999),
        huge("h",1024),
        large("l", 640),
        medium("m", 320),
        small("t", 160);

        public final int size;
        public final String suffix;

        ImgurImageSize(String suffix, int size) {
            this.suffix = suffix;
            this.size = size;
        }

        static ImgurImageSize getEnclosingSize(int maxSide) {
            boolean fits = false;
            ImgurImageSize enclosingSize = normal;

            for (ImgurImageSize size : ImgurImageSize.values()) {
                if (maxSide <= size.size) {
                    enclosingSize = size;
                } else {
                    return enclosingSize;
                }
            }

            return enclosingSize;
        }
    }


    /**
     * On imgur, add the following to the url hash:
     * s = Small Square (90×90) as seen in the example above
     * b = Big Square (160×160)
     * t = Small Thumbnail (160×160)
     * m = Medium Thumbnail (320×320)
     * l = Large Thumbnail (640×640) as seen in the example above
     * h = Huge Thumbnail (1024×1024)
     */

    public static void loadImage(ViewGroup parent, String url, int width, int height) {
        sImageLoader.loadImage(parent, url, width, height);
    }
}
