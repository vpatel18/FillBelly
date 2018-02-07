package com.developer.nikhil.fillbelly.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.nikhil.fillbelly.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;



public class ApplicationHelper {


    private static String TAG = "ApplicationHelper";
    private static Typeface typefaceToWholeView;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    public static void showToast(final Context context, final String message) {
        Log.i(TAG, "showToast()");
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            });
        } else if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    /*public static boolean isUserLogined(Context context) {
        Log.i(TAG, "isUserLogined()");
        try {
            SharedPreferences settings = context.getSharedPreferences(Constants.PREFS_NAME, 0);
            if (null != settings) {
                String rid = settings.getString(Config.KEY_R_ID, null);
                if (ApplicationHelper.isStringValid(rid)) {
                    try {
                        int rid_int = Integer.parseInt(rid);
                        if (rid_int > 0) {
                            return true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/

    /*public static void launchLoginActivity(Context context, boolean want_stack_clear) {
        Log.i(TAG, "launchLoginActivity() want_stack_clear " + want_stack_clear);
        Intent i1 = new Intent(context, Login_SignupActivity.class);
        i1.putExtra(ApplicationConstant.KEY_FLAG_LAUNCH_HOME_SCREEN, want_stack_clear);
        i1.putExtra(ApplicationConstant.KEY_FLAG_ALLOW_SKIP, want_stack_clear);
        if (context instanceof SplashActivity) {
            i1.putExtra(ApplicationConstant.KEY_FLAG_ALLOW_SKIP, true);
            i1.putExtra(ApplicationConstant.KEY_FLAG_LAUNCH_HOME_SCREEN, true);
        }
        if (want_stack_clear) {
            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
        context.startActivity(i1);
    }*/

    /*public static void launchHomeActivity(Context context, boolean want_stack_clear) {
        Log.i(TAG, "launchHomeActivity()");
        Intent i1 = new Intent(context, HomeActivity.class);
        if (want_stack_clear) {
            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
        context.startActivity(i1);
    }*/

    /*public static int getLoginId(Context context) {
        Log.i(TAG, "getLoginId()");
        int user_id = -1;
        if (ApplicationHelper.isUserLogined(context)) {
            SharedPreferences settings = context.getSharedPreferences(ApplicationConstant.PREFS_NAME, 0);
            if (null != settings) {
                String rid = settings.getString(Config.KEY_R_ID, null);
                if (!TextUtils.isEmpty(rid)) {
                    try {
                        Log.i(TAG, "getLoginId() rid : " + rid);
                        user_id = Integer.parseInt(rid);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return user_id;
    }*/

    /*public static String getLoginEmailId(Context context) {
        Log.i(TAG, "getLoginEmailId()");
        if (ApplicationHelper.isUserLogined(context)) {
            SharedPreferences settings = context.getSharedPreferences(ApplicationConstant.PREFS_NAME, 0);
            if (null != settings) {
                String emailid = settings.getString(Config.KEY_EMAIL, null);
                // String emailid = settings.getString(Config.KEY_EMAIL_ID, null);
                if (ApplicationHelper.isStringValid(emailid) && Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
                    try {
                        Log.i(TAG, "getLoginEmailId() emailid : " + emailid);
                        return emailid;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }*/

    /*public static void logout(Context context) {
        Log.i(TAG, "logout()");
        SharedPreferences settings = context.getSharedPreferences(ApplicationConstant.PREFS_NAME, 0);
        settings.edit().remove(Config.KEY_R_ID).commit();
        settings.edit().remove(Config.KEY_LOGGED).commit();
        settings.edit().remove(Config.KEY_EMAIL).commit();
        ApplicationHelper.showToast(context, "Logout Successfully");
        ApplicationHelper.launchLoginActivity(context, true);
    }*/

    public static boolean isEmpty(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence)
                || charSequence.toString().equalsIgnoreCase("null");
    }

    /*public static void shareTextUrl(Context context) {
        Log.i(TAG, "shareTextUrl()");
        Intent share = new Intent(Intent.ACTION_SEND);
        //share.setType("text/html");
        share.setType("text*//*");
//        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        //share.putExtra(Intent.EXTRA_SUBJECT, "Share");
        share.putExtra(Intent.EXTRA_TEXT, ApplicationConstant.SHARE_TEXT);

        context.startActivity(Intent.createChooser(share, "Share link!"));
    }*/

    public static int getResourceId(Context context, String pVariableName, String pResourcename, String pPackageName) {
        Log.i(TAG, "getResourceId()");
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

   /* public static Bitmap getBitmapFromUri(Context mContext, Uri imageUri) {
        Log.i(TAG, "getBitmapFromUri()");

        Bitmap bmp = null;
        FileInputStream inputStream = null;
        File file = null;
        try {

            if (null != imageUri && !TextUtils.isEmpty(imageUri.toString())) {
                Uri tempImageUri = Uri.parse(ApplicationHelper.getRealPathFromURI(mContext, imageUri));
//                if (!ApplicationHelper.haveAuthority(tempImageUri)) {
//                    tempImageUri = Uri.parse(ApplicationConstant.AUTHORITY_FILE + tempImageUri.toString());
//                }
                // bmp = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), tempImageUri);
                //file = new File(tempImageUri.toString());
                file = ApplicationHelper.getFileFromUri(mContext, imageUri);
                inputStream = new FileInputStream(file);
                bmp = BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
            Log.i(TAG, "getBitmapFromUri() exception : " + e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bmp;
    }*/

    public static boolean haveAuthority(Uri uri) {
        if (null == uri) {
            return false;
        }
        String authority = uri.getAuthority();
        if (ApplicationHelper.isStringValid(authority)) {
            return true;
        }
        return false;
    }

    public static Uri writeBitmapToFile(Bitmap bmpfix, String dir, String filename) {
        Log.i(TAG, "writeBitmapToFile()");
        dir = dir.replace(" ", "%20");
        filename = filename.replace(" ", "%20");
        Uri imguri = null;
        try {
            if (null != bmpfix && bmpfix.getByteCount() > 0 && !TextUtils.isEmpty(dir) && !TextUtils.isEmpty(filename)) {
                File mydir = new File(dir);
                if (!mydir.exists()) {
                    mydir.mkdirs();
                }
                File myFile = new File(mydir, filename);
                FileOutputStream out = new FileOutputStream(myFile);
                bmpfix.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
                imguri = Uri.parse(myFile.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imguri;
    }


    public static String getHumanReadableByteCount(long bytes, boolean si) {
        Log.i(TAG, "getHumanReadableByteCount()");
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public static void dumpIntentData(Intent intent) {
        Log.i(TAG, "dumpIntentData()");
        if (null == intent) {
            Log.e(TAG, "dumpIntentData() intent null");
            return;
        }

        if (null == intent.getExtras()) {
            Log.e(TAG, "dumpIntentData() bundle null");
        }

        Bundle extras = intent.getExtras();
        if (null != extras) {
            Log.i(TAG, "dumpIntentData() =========================");
            for (String key : extras.keySet()) {
                Object value = extras.get(key);
                try {
                    Log.i(TAG, "bundle : " + key + " = " + value);
//                Log.i(TAG, "dumpIntentData() " + String.format("%s %s (%s)", key,
//                        "=" + value.toString(), " " + value.getClass().getName()));
                } catch (Exception e) {
                    Log.i(TAG, "dumpIntentData() error message " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
            Log.i(TAG, "dumpIntentData() =========================");
        }

        String action = intent.getAction();
        Log.i(TAG, "dumpIntentData() action : " + ((TextUtils.isEmpty(action)) ? "" : action));
    }


   /* @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getRealPathFromURI(Context context, Uri uri) {
        Log.i(TAG, "getRealPathFromURI() uri " + uri);
        String pattern = "/^[ A-Za-z0-9_@./#&+-]*$/";
        uri = Uri.parse(uri.toString().replaceAll(pattern, uri.toString()));
        //uri = Uri.parse(uri.toString().replace(" ", "%20"));

        Log.i(TAG, "getRealPathFromURI() new uri " + uri);
//        uri = Uri.parse(uri.toString().replace(" ", "%20"));
        String path = app.technoadviser.com.letterwritingmokeup.Utils.FileUtils.getPath(context, uri);
        Log.i(TAG, "getRealPathFromURI() get path " + path);
        if (ApplicationHelper.isStringValid(path)) {
            if (!path.contains("://")) {
                path = "file://" + path;
            }
            path = path.replaceAll(pattern, path);
            //path = path.replace(" ", "%20");
        } else {
            path = uri.toString();
        }
        Log.i(TAG, "getRealPathFromURI() return path " + path);
        return path;
    }*/


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Log.i(TAG, "getDataColumn()");
        uri = Uri.parse(uri.toString().replace(" ", "%20"));
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        uri = Uri.parse(uri.toString().replace(" ", "%20"));
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        uri = Uri.parse(uri.toString().replace(" ", "%20"));
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        uri = Uri.parse(uri.toString().replace(" ", "%20"));
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        uri = Uri.parse(uri.toString().replace(" ", "%20"));
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static float get1InchXPixels(Context context) {
        Log.i(TAG, "get1InchXPixels()");
        if (context instanceof Activity) {
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
            return dm.xdpi;
        }
        Log.i(TAG, "get1InchXPixels() context not of activity");
        return 0;
    }

    public static float get1InchYPixels(Context context) {
        Log.i(TAG, "get1InchYPixels()");
        if (context instanceof Activity) {
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
            return dm.ydpi;
        }
        Log.i(TAG, "get1InchYPixels() context not of activity");
        return 0;
    }


    public static void deleteAllOldSavedFiles(Context context) {
        Log.i(TAG, "deleteAllOldSavedFiles()");
        File dirFiles = context.getFilesDir();
        if (null == dirFiles) {
            Log.i(TAG, "deleteAllOldSavedFiles() dirFiles null");
            return;
        }
        File[] files = dirFiles.listFiles();
        if (null == files || files.length < 1) {
            Log.i(TAG, "deleteAllOldSavedFiles() files array not valid");
            return;
        }
        Log.i(TAG, "deleteAllOldSavedFiles() files count " + files.length);

        for (File file : Arrays.asList(files)) {
            // strFile is the file txtCard
            if (null != file && file.isFile()) {
                Log.i(TAG, "deleteAllOldSavedFiles() file " + file);
                boolean isDelete = file.delete();
                Log.i(TAG, "deleteAllOldSavedFiles() file deleted ? " + isDelete);
            } else if (null != file && file.isDirectory()) {
                Log.i(TAG, "deleteAllOldSavedFiles() dir " + file);
            } else {
                Log.i(TAG, "deleteAllOldSavedFiles() file obj null");
            }
        }
    }

    public static String getMimeType(Context context, String url) {
        Log.i(TAG, "getMimeType() url " + url);
        url = url.replace(" ", "%20");
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        Log.i(TAG, "getMimeType() mimetype " + type);
        return type;
    }

//    public static float pixelsToSp(Context context, float px) {
//        Log.i(TAG, "pixelsToSp() px " + px);
//        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
//        Log.i(TAG, "pixelsToSp() scaledDensity" + scaledDensity);
//        // Log.i(TAG, "pixelsToSp() size " + px / scaledDensity);
//        Log.i(TAG, "pixelsToSp() size " + px * scaledDensity);
//        //return (px / scaledDensity);
//        return (px * scaledDensity);
//    }

    @SuppressWarnings("deprecation")
    public static Drawable getCircleDrawable(Context context, int width, int height, String color) {
        Log.i(TAG, "getCircleDrawable()");
        //////Drawing oval & Circle programmatically /////////////

//        ShapeDrawable oval = new ShapeDrawable(new OvalShape());
//        oval.setIntrinsicHeight(height);
//        oval.setIntrinsicWidth(width);
//        oval.getPaint().setColor(Color.parseColor(color));


        int strokeWidth = 5; // 3px not dp
        int roundRadius = 360; // 8px not dp
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor(color));
        gd.setCornerRadius(roundRadius);
        gd.setSize(width, height);
        gd.setStroke(strokeWidth, ContextCompat.getColor(context, R.color.colorPrimary));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            gd.setStroke(strokeWidth, ((Activity) context).getResources().getColor(R.color.app_yellow_color, null));
//        } else {
//            gd.setStroke(strokeWidth, ((Activity) context).getResources().getColor(R.color.app_yellow_color));
//        }


        return gd;
    }

    public static void SetTextColorToWholeView(final Context context, final View v, String color) {
        Log.i(TAG, "SetTextColorToWholeView()");
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    SetTextColorToWholeView(context, child, color);
                }
            } else if (v instanceof TextView) {
                Log.i(TAG, "SetTextColorToWholeView() TextView");
                ((TextView) v).setTextColor(Color.parseColor(color));
            } else if (v instanceof EditText) {
                Log.i(TAG, "SetTextColorToWholeView() EditText");
                ((EditText) v).setTextColor(Color.parseColor(color));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SetTextSizeInPTToWholeView(final Context context, final View v, String textsize) {
        Log.i(TAG, "SetTextSizeInPTToWholeView() textsize " + textsize);
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    SetTextSizeInPTToWholeView(context, child, textsize);
                }
            } else if (v instanceof TextView) {
                Log.i(TAG, "SetTextSizeInPTToWholeView() TextView");
                //((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_SP, Float.parseFloat(textsize));
                ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PT, Float.parseFloat(textsize));
            } else if (v instanceof EditText) {
                Log.i(TAG, "SetTextSizeInPTToWholeView() EditText");
                //((EditText) v).setTextSize(TypedValue.COMPLEX_UNIT_SP, Float.parseFloat(textsize));
                ((EditText) v).setTextSize(TypedValue.COMPLEX_UNIT_PT, Float.parseFloat(textsize));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SetBackgroundColorToWholeView(final Context context, final View v, String color) {
        Log.i(TAG, "SetBackgroundColorToWholeView()");
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    SetBackgroundColorToWholeView(context, child, color);
                }
                v.setBackgroundColor(Color.parseColor(color));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BigDecimal roundFloatValue(float value, int decimalPlace) {
        Log.i(TAG, "roundFloatValue()");
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_UP);
        return bd;
    }

    /*public static String getAddressInStringFormat(Address address) {
        Log.i(TAG, "getAddressInStringFormat()");
        String strAddress = null;
        if (null == address) {
            Log.e(TAG, "getAddressInStringFormat() address obj is null");
            return strAddress;
        }

        String firstName = address.getFirstName();
        String lastname = address.getLastName();
        String address1 = address.getAddress1();
        String address2 = address.getAddress2();
        String city = address.getCity();
        String state = address.getState();
        String country = address.getCountry();
        String postalCode = address.getPostalCode();

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(address1) || TextUtils.isEmpty(city) || TextUtils.isEmpty(state) || TextUtils.isEmpty(postalCode) || TextUtils.isEmpty(country)) {
            Log.e(TAG, "getAddressInStringFormat() address some fields not valid -> firstName " + firstName + " lastname " + lastname + " address1 " + address1 + " city " + city + " state " + state + " postalcode " + postalCode + " country " + country);
            return strAddress;
        }

        strAddress = firstName;
        strAddress += " " + lastname;
        strAddress += ",\n" + address1;
        if (!TextUtils.isEmpty(address2)) {
            strAddress += ",\n" + address2;
        }
        strAddress += ",\n" + city;
        strAddress += ", " + state;
        strAddress += ", " + postalCode;
        strAddress += ",\n" + country;
        Log.i(TAG, "getAddressInStringFormat() strAddress " + strAddress);
        return strAddress;
    }*/


    /*public static String getAddressWithoutCommaInStringFormat(Address address) {
        Log.i(TAG, "getAddressWithoutCommaInStringFormat()");
        String strAddress = null;
        if (null == address) {
            Log.e(TAG, "getAddressWithoutCommaInStringFormat() address obj is null");
            return strAddress;
        }

        String firstName = address.getFirstName();
        String lastname = address.getLastName();
        String address1 = address.getAddress1();
        String address2 = address.getAddress2();
        String city = address.getCity();
        String state = address.getState();
        String country = address.getCountry();
        String postalCode = address.getPostalCode();

        if (TextUtils.isEmpty(address1) || TextUtils.isEmpty(city) || TextUtils.isEmpty(state) || TextUtils.isEmpty(postalCode) || TextUtils.isEmpty(country)) {
            Log.e(TAG, "getAddressWithoutCommaInStringFormat() address some fields not valid -> firstName " + firstName + " lastname " + lastname + " address1 " + address1 + " city " + city + " state " + state + " postalcode " + postalCode + " country " + country);
            return strAddress;
        }

        strAddress = firstName;
        strAddress += "*" + lastname;
        strAddress += "" + address1;
        if (!TextUtils.isEmpty(address2)) {
            strAddress += "" + address2;
        }
        strAddress += "" + city;
        strAddress += "" + state;
        strAddress += "" + postalCode;
        strAddress += "" + country;
        Log.i(TAG, "getAddressWithoutCommaInStringFormat() strAddress " + strAddress);
        return strAddress;
    }*/



    public static Bitmap BITMAP_RESIZER(Bitmap bitmap, int newWidth, int newHeight) {
        Log.i(TAG, "BITMAP_RESIZER()");
        Bitmap scaledBitmap = null;
        if (null == bitmap || bitmap.getWidth() < 1 || bitmap.getHeight() < 1) {
            return scaledBitmap;
        }
        scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    public static Uri getInternalDownloadUri(Context context, Uri source_uri) {
        Log.i(TAG, "getInternalDownloadUri()");
        source_uri = Uri.parse(source_uri.toString().replace(" ", "%20"));
        Uri uri = null;
        String source_url = ((null != source_uri) ? source_uri.toString() : null);
        try {
            if (TextUtils.isEmpty(source_url)) {
                Log.e(TAG, "getInternalDownloadUri() " + source_url + " not valid ");
                return uri;
            }

            String filename = source_url.substring(source_url.lastIndexOf("/") + 1, source_url.length());
            File cacheDir = ((Activity) context).getBaseContext().getFilesDir();
            File mydir = new File(cacheDir.getAbsolutePath());
            if (!mydir.exists()) {
                mydir.mkdirs();
            }

            File myFile = new File(mydir, filename);
            if (!myFile.exists()) {
                Log.e(TAG, "getInternalDownloadUri() " + filename + " file not exist ");
                return uri;
            }
            uri = Uri.parse(myFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uri;
    }

   /* public static Typeface getAppTypeFace(Context mContext) {
        Log.i(TAG, "getAppTypeFace()");
        Typeface typeFace = null;
        try {
            typeFace = getTypeFace(mContext, Constants.textraleway_semibold);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return typeFace;
    }*/


    public static void setTypefaceToWholeView(View v, Typeface typeface) {
        Log.i(TAG, "setTypefaceToWholeView()");
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    if (child instanceof TextView) {
                        ((TextView) child).setTypeface(typeface);
                    }
                    if (child instanceof EditText) {
                        ((EditText) child).setTypeface(typeface);
                    }
                    if (child instanceof RadioButton) {
                        ((RadioButton) child).setTypeface(typeface);
                    }
                    if (child instanceof Button) {
                        ((Button) child).setTypeface(typeface);
                    }
                    if (child instanceof CheckBox) {
                        ((CheckBox) child).setTypeface(typeface);
                    } else {
                        setTypefaceToWholeView(child, typeface);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isStringValid(String data) {
        Log.i(TAG, "isStringValid()");
        if (!TextUtils.isEmpty(data) && !data.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public static String getDateTimeString(long milliSeconds, String dateFormat) {
        Log.i(TAG, "getDateTimeString()");

        if (!ApplicationHelper.isStringValid(dateFormat)) {
            Log.i(TAG, "getDateTimeString() dateFormat not valid");
            return "";
        }

        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    /*public static String getCardString(CardData cardData) {
        Log.i(TAG, "getCardString()");
        String data = "";
        if (null == cardData) {
            Log.i(TAG, "getCardString() card is null");
            return data;
        }
        String card_number = cardData.getCard_number();
        int exp_month = cardData.getExp_month();
        int exp_year = cardData.getExp_year();

        if (!TextUtils.isEmpty(card_number) && exp_month > 0 && exp_year > 999) {
            String str_month = String.valueOf(exp_month);
            if (exp_month < 10) {
                str_month = ("0" + exp_month);
            }
            data = "**** " + card_number.substring(card_number.length() - 4, card_number.length()) + " " + str_month + "/" + exp_year;
        }
        Log.i(TAG, "getCardString() data " + data);
        return data;
    }*/

    public static String getDeviceConfiguration(Context context) {
        Log.i(TAG, "getDeviceConfiguration()");
        String device_config = null;
        if (null == context) {
            return device_config;
        }
        device_config = context.getResources().getConfiguration().toString();
        Log.i(TAG, "getDeviceConfiguration() configuration " + context.getResources().getConfiguration().toString());
        Log.i(TAG, "getDeviceConfiguration() SCREENLAYOUT_SIZE " + (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK));
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Log.i(TAG, "getDeviceConfiguration() SCREENLAYOUT_SIZE_SMALL " + Configuration.SCREENLAYOUT_SIZE_SMALL);
        }
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Log.i(TAG, "getDeviceConfiguration() SCREENLAYOUT_SIZE_NORMAL " + Configuration.SCREENLAYOUT_SIZE_NORMAL);
        }
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Log.i(TAG, "getDeviceConfiguration() SCREENLAYOUT_SIZE_LARGE " + Configuration.SCREENLAYOUT_SIZE_LARGE);
        }
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Log.i(TAG, "getDeviceConfiguration() SCREENLAYOUT_SIZE_XLARGE " + Configuration.SCREENLAYOUT_SIZE_XLARGE);
        }
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_UNDEFINED) {
            Log.i(TAG, "getDeviceConfiguration() SCREENLAYOUT_SIZE_UNDEFINED " + Configuration.SCREENLAYOUT_SIZE_UNDEFINED);
        }

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int densityDpi = dm.densityDpi;
        Log.i(TAG, "getDeviceConfiguration() DENSITY  " + densityDpi);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            if (densityDpi == DisplayMetrics.DENSITY_260) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_260 " + DisplayMetrics.DENSITY_260);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (densityDpi == DisplayMetrics.DENSITY_280) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_280 " + DisplayMetrics.DENSITY_280);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            if (densityDpi == DisplayMetrics.DENSITY_300) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_300 " + DisplayMetrics.DENSITY_300);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            if (densityDpi == DisplayMetrics.DENSITY_340) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_340 " + DisplayMetrics.DENSITY_340);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (densityDpi == DisplayMetrics.DENSITY_360) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_360 " + DisplayMetrics.DENSITY_360);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (densityDpi == DisplayMetrics.DENSITY_400) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_400 " + DisplayMetrics.DENSITY_400);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (densityDpi == DisplayMetrics.DENSITY_420) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_420 " + DisplayMetrics.DENSITY_420);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (densityDpi == DisplayMetrics.DENSITY_560) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_560 " + DisplayMetrics.DENSITY_560);
            }
        }
        if (densityDpi == DisplayMetrics.DENSITY_TV) {
            Log.i(TAG, "getDeviceConfiguration() DENSITY_TV " + DisplayMetrics.DENSITY_TV);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (densityDpi == DisplayMetrics.DENSITY_XXHIGH) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_XXHIGH " + DisplayMetrics.DENSITY_XXHIGH);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (densityDpi == DisplayMetrics.DENSITY_XXXHIGH) {
                Log.i(TAG, "getDeviceConfiguration() DENSITY_XXXHIGH " + DisplayMetrics.DENSITY_XXXHIGH);
            }
        }
        if (densityDpi == DisplayMetrics.DENSITY_LOW) {
            Log.i(TAG, "getDeviceConfiguration() DENSITY_LOW " + DisplayMetrics.DENSITY_LOW);
        }
        if (densityDpi == DisplayMetrics.DENSITY_MEDIUM) {
            Log.i(TAG, "getDeviceConfiguration() DENSITY_MEDIUM " + DisplayMetrics.DENSITY_MEDIUM);
        }
        if (densityDpi == DisplayMetrics.DENSITY_HIGH) {
            Log.i(TAG, "getDeviceConfiguration() DENSITY_HIGH " + DisplayMetrics.DENSITY_HIGH);
        }
        if (densityDpi == DisplayMetrics.DENSITY_XHIGH) {
            Log.i(TAG, "getDeviceConfiguration() DENSITY_XHIGH " + DisplayMetrics.DENSITY_XHIGH);
        }


        double widthinch = (context.getResources().getConfiguration().screenWidthDp / densityDpi);
        double heightinch = (context.getResources().getConfiguration().screenHeightDp / densityDpi);
        device_config = device_config.concat("\n width_inch " + widthinch + "\n height_inch " + heightinch + "\n");
        Log.i(TAG, "getDeviceConfiguration() widthinch " + widthinch + " heightinch " + heightinch);

        double density = dm.density * DisplayMetrics.DENSITY_DEFAULT;
        double x = Math.pow(dm.widthPixels / density, 2);
        double y = Math.pow(dm.heightPixels / density, 2);
        double screenInches = (Math.round(Math.sqrt(x + y) * 100.00) / 100.00);
        device_config = device_config.concat(" display in inches " + screenInches);
        Log.i(TAG, "getDeviceConfiguration() display in inches " + screenInches);
        Log.i(TAG, "getDeviceConfiguration() return " + device_config);
        return device_config;
    }

    public static String convertInputStreamToString(InputStream stream) {
        Log.i(TAG, "convertInputStreamToString()");

        if (null == stream) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /*public static String getTemplate1JsonData(int templateId, int user_id, String Greeting, String Subject, String Message, String closing) {
        String data = "";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(ApplicationConstant.KEY_GREETING, (!ApplicationHelper.isStringValid(Greeting) ? null : Greeting));
            jsonObject.put(Config.KEY_SUBJECT, (!ApplicationHelper.isStringValid(Subject) ? null : Subject));
            jsonObject.put(Config.KEY_MESSAGE, (!ApplicationHelper.isStringValid(Message) ? null : Message));
            jsonObject.put(ApplicationConstant.KEY_CLOSING, (!ApplicationHelper.isStringValid(closing) ? null : closing));
            data = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }*/

    public static void CopyFile(Uri sourceUri, File destination) {
        try {
            Log.i(TAG, "CopyFile() sourceuri " + sourceUri.toString() + " destination " + destination);
            sourceUri = Uri.parse(sourceUri.toString().replace(" ", "%20"));
            File source = new File(sourceUri.getPath());
            FileChannel src = new FileInputStream(source).getChannel();
            FileChannel dst = new FileOutputStream(destination).getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getAmountInString(int amount) {
        Log.i(TAG, "getAmountInString() amount " + amount);
        String str_cost = null;
        if (amount > 0) {
            double cost = (((double) amount) / ((double) 100));
            Log.i(TAG, "getAmountInString() cost " + cost);
            //new DecimalFormat("##.##").format(cost)
            str_cost = String.format("%.2f", cost);
        }
        return str_cost;
    }

    public static int getAmountInint(String amount) {
        Log.i(TAG, "getAmountInint() amount " + amount);
        int cost = 0;
        if (ApplicationHelper.isStringValid(amount)) {
            double amount_double = 0.0f;
            try {
                amount_double = Double.parseDouble(amount);
                if (amount_double > 0) {
                    double double_cost = (amount_double * ((double) 100));
                    Log.i(TAG, "getAmountInint() double_cost " + double_cost);
                    cost = (int) Math.round(double_cost);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cost;
    }


    /*public static File getFileFromUri(Context context, Uri uri) {
        Log.i(TAG, "getFileFromUri() uri " + uri);
        if (null == uri) {
            return null;
        }
        String realPathFromURI = ApplicationHelper.getRealPathFromURI(context, uri);

        String subdir = realPathFromURI.substring(realPathFromURI.lastIndexOf("://") + 1, realPathFromURI.length());
        File f = new File(subdir);
        return f;
    }*/


    public static String getCapitalString(String data) {
        Log.i(TAG, "getCapitalString()");
        if (!ApplicationHelper.isStringValid(data)) {
            return null;
        }
        return data.toUpperCase();
    }

}
