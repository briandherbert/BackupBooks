package com.burningaltar.backupbooks;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by bherbert on 5/16/16.
 */
public class ImageLoaderLib {

    private enum ImageLib {
        picasso(true),
        glide(false),
        ion(true),
        fresca(true);

        final boolean usesImageView;

        ImageLib(boolean usesImageView) {
            this.usesImageView = usesImageView;
        }
    }

    static ImageLib mImageLib = ImageLib.picasso;

    public static void loadImage(ViewGroup parent, Context context, String url, int width, int height) {
        ImageView img = null;

        if (mImageLib.usesImageView) {
            if (parent.getChildAt(0) == null || !(parent.getChildAt(0) instanceof ImageView)) {
                img = new ImageView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                img.setLayoutParams(lp);
            } else {
                img = (ImageView) parent.getChildAt(0);
            }
        }

        switch (mImageLib) {
            case picasso:
                Picasso.with(context).load(url)
                        .resize(width, height)
                        .centerInside()
                        .onlyScaleDown()
                        .error(new ColorDrawable(Color.RED))
                        .into(img);

                break;
        }
    }
}
