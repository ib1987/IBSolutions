package hu.ibsolutions.ibsolutions.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Application Util class
 */
public class AppUtil {

    /**
     * Check network connection
     * @param context tartget Context
     * @return connection is available
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info    = cm.getActiveNetworkInfo();
        if (info != null) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param input Date for format
     * @param pattern date pattern e.g. "yyyy.MM.dd"
     * @return
     */
    public static String formateDate(Date input, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("hu"));
        sdf.applyPattern(pattern);
        return sdf.format(input);
    }

    /**
     * Print application SHA finger print
     * @param context
     */
    public static void printHash(Context context) {
        try {
            PackageInfo info =  context.getPackageManager().getPackageInfo("hu.denhaag.denhaagpizzeria", PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign=Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("HASH", sign);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
