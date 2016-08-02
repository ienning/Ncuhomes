package com.example.ienning.ncuhome.personcenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.activity.MainActivity;
import com.example.ienning.ncuhome.cache.ClipActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by ienning on 16-7-17.
 */
public class PersonCenter extends Activity implements View.OnClickListener{

    private ImageView head;
    private ImageButton back;
    private LinearLayout library;
    private LinearLayout phoneNumber;
    private LinearLayout changePassword;

    private LinearLayout feedback;
    private LinearLayout aboutHome;
    private LinearLayout logout;
    private PopupWindow popupWindow;
    private String photoSaveName;
    private String path;
    private String photoSavePath;
    private LayoutInflater layoutInflater;
    private TextView  albums;
    private LinearLayout cancel;
    public static final int PHOTOZOOM = 1;
    public static final int IMAGE_COMPLETE = 2;
    public static final int CROPERQCODE = 3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_center);
        layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        File file = new File(Environment.getExternalStorageDirectory(), "/ClipHeadPhoto/cache");
        if (!file.exists())
            file.mkdirs();
        sharedPreferences = getSharedPreferences("ncuhome", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        photoSavePath = Environment.getExternalStorageDirectory() + "/ClipHeadPhoto/cache/";
        photoSaveName = System.currentTimeMillis() + ".png";
        head = (ImageView) findViewById(R.id.head);
        back = (ImageButton) findViewById(R.id.back_main);
        library = (LinearLayout) findViewById(R.id.library);
        phoneNumber = (LinearLayout) findViewById(R.id.phonenumber);
        changePassword = (LinearLayout) findViewById(R.id.change_password);
        feedback = (LinearLayout) findViewById(R.id.feedback);
        aboutHome = (LinearLayout) findViewById(R.id.about_home);
        logout = (LinearLayout) findViewById(R.id.logout);
        try {
            head.setImageBitmap(getLoacalBitmap(sharedPreferences.getString("temppath", null)));
        } catch (Exception e) {
           e.printStackTrace();
        }
        back.setOnClickListener(this);
        library.setOnClickListener(this);
        phoneNumber.setOnClickListener(this);
        changePassword.setOnClickListener(this);
        feedback.setOnClickListener(this);
        aboutHome.setOnClickListener(this);
        logout.setOnClickListener(this);
        head.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head:
                showPopupWindow(head);
                Log.i("IEnning", "onClick: this is a small test");
                Toast.makeText(PersonCenter.this, "small test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back_main:
                Intent intent = new Intent(PersonCenter.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.library:
                Intent intent1 = new Intent(PersonCenter.this, BindLibrary.class);
                startActivity(intent1);
                break;
            case R.id.phonenumber:
                Intent intent2 = new Intent(PersonCenter.this, Phonenumber.class);
                startActivity(intent2);
                break;
            case R.id.change_password:
                Intent intent3 = new Intent(PersonCenter.this, ChangePassword.class);
                startActivity(intent3);
                break;
            case R.id.feedback:
                Intent intent4 = new Intent(PersonCenter.this, Feedback.class);
                startActivity(intent4);
                break;
            case R.id.about_home:
                Intent intent5 = new Intent(PersonCenter.this, AboutHome.class);
                startActivity(intent5);
                break;
            case R.id.logout:
                break;
        }
    }
    @SuppressWarnings("deprecation")
    private void showPopupWindow(View parent) {
        if (popupWindow == null) {
            View view = layoutInflater.inflate(R.layout.layout_pop_selector,null);
            popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            initPop(view);
        }
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }
    public void initPop(View view) {
        albums = (TextView)view.findViewById(R.id.albums);
        cancel = (LinearLayout) view.findViewById(R.id.cancel);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                Intent openAlbumIntnet = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntnet.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(openAlbumIntnet, PHOTOZOOM);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        Uri uri = null;
        switch (requestCode) {
            case PHOTOZOOM:
                if (data == null) {
                    return;
                }
                uri = data.getData();
                String[] proj = {
                        MediaStore.Images.Media.DATA
                };
                Log.i("Ienning", "The result is " + proj);
                Cursor cursor = managedQuery(uri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                path = cursor.getString(column_index);
                Intent intent3 = new Intent(PersonCenter.this, ClipActivity.class);
                intent3.putExtra("path", path);
                startActivityForResult(intent3, IMAGE_COMPLETE);
                break;
            case IMAGE_COMPLETE:
                final String temppath = data.getStringExtra("path");
                editor.putString("temppath", temppath);
                editor.commit();
                head.setImageBitmap(getLoacalBitmap(temppath));
                Log.i("Ienning", "it is true or not");
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
