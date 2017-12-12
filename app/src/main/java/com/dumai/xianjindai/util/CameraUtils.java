package com.dumai.xianjindai.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import com.dumai.xianjindai.global.Constant;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

/**
 * 名称：CameraUtils.java
 * 描述：拍照图片工具类
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/28 17:05
 */
public class CameraUtils {

    public static final int GET_IMAGE_BY_CAMERA = 5001;
    public static final int GET_IMAGE_FROM_PHONE = 5002;
    public static final int CROP_IMAGE = 5003;
    public static Uri imageUri;

    /**
     * convert Bitmap to byte array
     *
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * convert Drawable to Bitmap
     *
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable) d).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     *
     * @param b
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b != null ? new BitmapDrawable(b) : null;
    }

    /**
     * convert Drawable to byte array
     *
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * convert byte array to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * convert bitmap to base64 string
     *
     * @param bitmap
     * @return
     */
    public static String bitmaptoString(Bitmap bitmap) {
        String string = null;

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);

        byte[] bytes = bStream.toByteArray();
        string = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT);

        return string;
    }

    /**
     * convert base64 string to bitmap
     *
     * @param string
     * @return
     */
    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;

        byte[] bitmapArray;
        bitmapArray = android.util.Base64.decode(string, android.util.Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);

        return bitmap;
    }

    /**
     * scale image
     *
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(),
                (float) newHeight / org.getHeight());
    }

    /**
     * scale image
     *
     * @param org
     * @param scaleWidth  sacle of width
     * @param scaleHeight scale of height
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

    //打开相册
    public static void openLocalImage(final Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, CameraUtils.GET_IMAGE_FROM_PHONE);
    }

    // 拍照  haoruigang  2017/9/26 15:04
    public static void openCameraImage(final Activity activity, String name) {
        CameraUtils.imageUri = CameraUtils.createImagePathUri(name);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, CameraUtils.imageUri);
        activity.startActivityForResult(intent, CameraUtils.GET_IMAGE_BY_CAMERA);
    }

    // 获取路径  haoruigang  2017/9/26 15:04
    public static Uri createImagePathUri(String name) {
        File file = getImgPath(name);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(file);
    }

    // 判断路径  haoruigang  2017/9/26 15:05
    public static File getImgPath(String name) {
        File file;
        if (!new File(Constant.CAMERA_PATH).exists()) {
            new File(Constant.CAMERA_PATH).mkdirs();
        }
        file = new File(Constant.CAMERA_PATH, name);
        return file;
    }

    // 裁剪  haoruigang  2017/9/26 15:05
    public static void startPhotoZoom(Activity activity, Uri uri, int aspectX, int aspectY, boolean b) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
//            File outFile = new File(new URI(uri.toString()));
            intent.setDataAndType(uri, "image/*");
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            // aspectX aspectY 是宽高的比例
            if (0 != aspectX && aspectY != 0) {
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", aspectX);
                intent.putExtra("aspectY", aspectY);
            }
            intent.putExtra("scale", true);
            intent.putExtra("return-data", b); // 是否返回图片的bitmap
            Uri imageUri = createImagePathUri("cut");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
            activity.startActivityForResult(intent, CROP_IMAGE);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //压缩图片
    public static void compressBitmap(String bitmap, String imagePath, Bitmap.Config config, FileCallback callback) {
        FileUtils.gcAndFinalize();
        Tiny.FileCompressOptions compressOptions = new Tiny.FileCompressOptions();
        compressOptions.config = config;
        if (EmptyUtils.isNotEmpty(imagePath))
            compressOptions.outfile = imagePath;
        Tiny.getInstance().source(bitmap).asFile().withOptions(compressOptions).compress(callback);
    }

    //保存图片
    public static String compressBitmap(Bitmap bitmap, String imagePath) {
        FileUtils.gcAndFinalize();
        if (EmptyUtils.isNotEmpty(bitmap)) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                File file = new File(imagePath);
                if (file.exists()) {
                    file.delete();
                }
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bitmapToBytes(bitmap, true));
                fileOutputStream.flush();
                return Uri.fromFile(file).getPath();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != byteArrayOutputStream) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                    }
                }
                if (null != fileOutputStream) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return null;
    }

    /**
     * Bitmap转字节数组
     *
     * @return
     */
    private static byte[] bitmapToBytes(Bitmap bm, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bm.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Android7.0适配之图片裁剪
     *
     * @param context
     * @param imageFile
     * @return
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
}
