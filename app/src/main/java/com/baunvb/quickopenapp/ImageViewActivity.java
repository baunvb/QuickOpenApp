package com.baunvb.quickopenapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by Baunvb on 12/28/2016.
 */

public class ImageViewActivity extends Activity {
    private ImageView imgPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        initView();

    }

    private void initView() {
        imgPhoto = (ImageView) findViewById(R.id.iv_gallery);
        Intent intent = getIntent();
        Uri uri = intent.getData();
        Bitmap bmPhoto = null;
        try {
            bmPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgPhoto.setImageBitmap(bmPhoto);
    }

}
