package hu.ibsolutions.ibsolutions.ui.transform;


import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

import hu.ibsolutions.ibsolutions.ui.UIHelper;

/**
 * Scale to image width for Picasso
 * @see com.squareup.picasso.Picasso
 */
public class ScaleToWidthTransform implements Transformation {

    private static final String KEY = "scaleToWidth";
    private Context context;
    private double percent;

    /**
     * Scale image to screen width and define the scale percentage
     * @param context target context
     * @param percent target percentage
     */
    public ScaleToWidthTransform(Context context, double percent) {
        this.context = context;
        this.percent = percent;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int targetWidth     = ((Double)((double) UIHelper.getScreenWidth(context) * (percent / 100))).intValue();
        double aspectRatio  = (double)source.getHeight() / (double)source.getWidth();
        int targetHeight    = (int) (targetWidth * aspectRatio);
        Bitmap result       = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, true);

        // If source and result not equals, recycle source
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return KEY;
    }
}
