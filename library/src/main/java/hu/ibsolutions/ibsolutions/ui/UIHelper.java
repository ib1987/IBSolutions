package hu.ibsolutions.ibsolutions.ui;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toolbar;

import java.lang.reflect.Field;

/**
 * UI helper class
 */
public class UIHelper {

    // Static variable for logging
    private static final String TAG = UIHelper.class.getName();

    /**
     * Set the specific typeface
     * @param context target context
     * @param target target TextView
     * @param typeFace specific typeface name
     */
    public static void setTypeFace(Context context, TextView target, String typeFace) {
        target.setTypeface(Typeface.createFromAsset(context.getAssets(), typeFace));
    }

    /**
     * Get screen width in px
     * @param context specific context
     * @return screen width in px
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.x;
    }

    /**
     * Get toolbar title TextView
     * @param toolbar target Toolbar
     * @return TextView
     */
    private static TextView getToolbarTitle(Toolbar toolbar) {
        TextView title = null;
        try {
            Field field = toolbar.getClass().getDeclaredField("mTitleTextView");
            field.setAccessible(true);
            title = (TextView) field.get(toolbar);
        } catch (Exception e) {
            Log.e(TAG, "Can't access to Toolbar title", e);
        }
        return title;
    }

}
