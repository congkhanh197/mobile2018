package com.example.thoithanh.shoppingappp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Khanh Tran-Cong on 5/21/2018.
 * Email: congkhanh197@gmail.com
 */
public class ImageAdapter {
	static String TAG = "ImageAdapter.class";
	public static void setImageAdapter(URL url, ImageView imageView){
		Bitmap bmp = null;
		try {
			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			imageView.setImageBitmap(bmp);
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}

	}
}
