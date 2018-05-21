package com.example.thoithanh.shoppingappp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Khanh Tran-Cong on 5/21/2018.
 * Email: congkhanh197@gmail.com
 */
public class ImageAdapter {
	static String TAG = "ImageAdapter.class";
	public static void setImageAdapter(String string_url, ImageView imageView){
		try {
			URL url = new URL(string_url);
			Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			imageView.setImageBitmap(bmp);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}
	}
}
