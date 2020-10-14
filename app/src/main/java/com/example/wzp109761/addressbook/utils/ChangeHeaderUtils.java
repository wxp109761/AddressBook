package com.example.wzp109761.addressbook.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.wzp109761.addressbook.application.AppContext;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import java.io.File;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;


public class ChangeHeaderUtils {
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    /*
   更换头像
    */
    public void  ChandeHeader(Context context, Activity activity) {
        String stringItems[] = {"拍照", "从相册选择"};
        final ActionSheetDialog dialog = new ActionSheetDialog(activity, stringItems, null);
        dialog.isTitleShow(true).show();
        dialog.title("更换头像");
        dialog.itemTextColor(Color.parseColor("#0091ea"));
        dialog.cancelText(Color.parseColor("#0091ea"));
        // dialog.itemPressColor(Color.parseColor("#e9857d"));
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        autoObtainCameraPermission(context,activity);
                        break;
                    case 1:
                        autoObtainStoragePermission(context,activity);
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission(Context context,Activity activity) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                Toasty.info(context, "您已经拒绝过一次", Toast.LENGTH_SHORT, true).show();
            }
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(context, "com.example.addressbookProvider", fileUri);
                }
                PhotoUtils.takePicture(activity, imageUri, CODE_CAMERA_REQUEST);
            } else {
                Toasty.info(context, "设备没有SD卡", Toast.LENGTH_SHORT, true).show();
            }
        }
    }
    public void onRequestPermissions(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults,Context context,Activity activity){
        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(context, "com.example.addressbookProvider", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(activity, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        Toasty.info(context, "设备没有SD卡", Toast.LENGTH_SHORT, true).show();
                    }
                } else {
                    Toasty.info(context, "请允许打开相机", Toast.LENGTH_SHORT, true).show();
                }
                break;


            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(activity, CODE_GALLERY_REQUEST);
                } else {
                    Toasty.info(context, "请允许操作SD卡", Toast.LENGTH_SHORT, true).show();
                }
                break;
            default:
        }
    }


    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;


    public Bitmap onAtyResult(int requestCode, int resultCode, Intent data, Context context, Activity activity){
        Bitmap bitmap=null;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(activity, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(context, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            newUri = FileProvider.getUriForFile(context, "com.example.addressbookProvider", new File(newUri.getPath()));
                        }
                        PhotoUtils.cropImageUri(activity, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    } else {
                        Toasty.info(activity, "设备没有SD卡", Toast.LENGTH_SHORT, true).show();
                    }
                    break;
                case CODE_RESULT_REQUEST:
                   bitmap= PhotoUtils.getBitmapFromUri(cropImageUri, context);
                    if (bitmap != null) {
                        System.out.println("GGG"+cropImageUri.getEncodedPath());
                        break;
                    }}

        }
        return bitmap;
    }




    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission(Context context,Activity activity) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(activity, CODE_GALLERY_REQUEST);
        }

    }


    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 重写返回按钮监听刷新主界面头像
     */

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            Intent intent = new Intent(UserDataActivity.this, LoginActivity.class);
//            setResult(2, intent);
//            finish();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
