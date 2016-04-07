package com.rudy.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by rudy on 2015/9/2.
 *这个类是为了计算Bitmap的缩放而获得缩放后的Bitmap
 */
public class ImageUtil {
    private static ImageUtil imageUtil = null;
    private static ImageUtil getInstance(){
        if(null == imageUtil){
            synchronized (ImageUtil.class){
                if(null == imageUtil){
                    imageUtil = new ImageUtil();
                }
            }
        }
        return imageUtil;
    }
    private  int calculateInSampleSize(BitmapFactory.Options options,int reqHeight,int reqWidth){
        int sampleSize = 1;
        int picHeight = options.outHeight;
        int picWidth = options.outWidth;
        return calculateInSampleSize(reqHeight, reqWidth, sampleSize, picHeight, picWidth);
    }
    //计算缩放倍数
    private int calculateInSampleSize(int reqHeight, int reqWidth, int sampleSize, int picHeight, int picWidth) {
        if(picHeight>reqHeight && picWidth>reqWidth){
            sampleSize = picHeight>picWidth?picWidth/reqWidth:picHeight/reqHeight;
        }
        return sampleSize;
    }

    //加载Bitmap
    private Bitmap decodeSampledBitmapFromPath(String path,int reqHeight,int reqWidth){
        Bitmap bitmap = null;
        BitmapFactory.Options options =new BitmapFactory.Options();
        if(reqHeight!=0&&reqWidth!=0){
            options.inJustDecodeBounds = true;//这是表示不分配内存的
            BitmapFactory.decodeFile(path,options);
            options.inSampleSize = calculateInSampleSize(options,reqHeight,reqWidth);
            options.inJustDecodeBounds = false;
        }
        bitmap = BitmapFactory.decodeFile(path,options);
        return  bitmap;
    }
    private Bitmap decodeSampledBitmapFromByte(byte[] bytes,int reqHeight,int reqWidth){
        Bitmap bitmap = null;
        BitmapFactory.Options options =new BitmapFactory.Options();
        if(reqHeight!=0&&reqWidth!=0){
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            options.inSampleSize = calculateInSampleSize(options,reqHeight,reqWidth);
            options.inJustDecodeBounds = false;
        }
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        return  bitmap;
    }
    public static Bitmap getBitmapFromPathWithRequest(String path,int reqHeight,int reqWidth){
        return getInstance().decodeSampledBitmapFromPath(path,reqHeight,reqHeight);
    }
}
