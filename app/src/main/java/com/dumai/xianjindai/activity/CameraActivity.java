package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.http.http.MyProgressDialog;
import com.dumai.xianjindai.util.CameraUtils;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.pickers.util.LogUtils;
import com.zxy.tiny.callback.FileCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： haoruigang on 2017-11-28 16:56:17
 * 类描述：相机，图片上传
 */
public class CameraActivity extends BaseActivity {
    public final static String ISUPDATE = "ISUPDATE";//是否上传
    public final static String Proportionx = "Proportionx";//比例X
    public final static String Proportiony = "Proportiony";//比例Y
    public final static int ACTIVITY_RESULT = 200;//返回到activity
    int aspectX;
    int aspectY;
    @BindView(R.id.tv_camera)
    TextView tvCamera;
    @BindView(R.id.tv_album)
    TextView tvAlbum;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private boolean isupdate;
    private MyProgressDialog dialog;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void init() {
        super.init();
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        if (getIntent().getExtras() != null) {
            isupdate = getIntent().getExtras().getBoolean(ISUPDATE);
            aspectX = getIntent().getExtras().getInt(Proportionx, 0);
            aspectY = getIntent().getExtras().getInt(Proportiony, 0);
        }
        dialog = new MyProgressDialog(this, true);
    }

    @OnClick({R.id.tv_camera, R.id.tv_album, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_camera:
                dialog.show();
                CameraUtils.openCameraImage(this, "img.jpg");
                break;
            case R.id.tv_album:
                CameraUtils.openLocalImage(this);
                break;
            case R.id.tv_cancel:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        super.onDestroy();
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            dialog.dismiss();
            finish();
            return;
        }
        if (requestCode == CameraUtils.GET_IMAGE_BY_CAMERA) {//相机
            CameraUtils.startPhotoZoom(this, CameraUtils.imageUri, aspectX, aspectY, false);
        } else if (requestCode == CameraUtils.GET_IMAGE_FROM_PHONE) {//相册
            CameraUtils.startPhotoZoom(this, data.getData(), aspectX, aspectY, false);
        } else if (requestCode == CameraUtils.CROP_IMAGE) {//裁剪
            LogUtils.error("onActivityResult", CameraUtils.getImgPath("cut").getAbsolutePath());
            CameraUtils.compressBitmap(CameraUtils.getImgPath("cut").getAbsolutePath(), CameraUtils.getImgPath("img_" + System.currentTimeMillis()).getAbsolutePath(), Bitmap.Config.RGB_565, new FileCallback() {
                @Override
                public void callback(boolean isSuccess, String outfile) {
                    if (!isSuccess) {
                        dialog.dismiss();
                        ToastUtils.showToast(CameraActivity.this, "图片上传失败, 请重新提交~！");
                        return;
                    }
                    LogUtils.error("callback", outfile);
                    if (isupdate) {
                        update(outfile);
                        dialog.dismiss();
                    } else {
                        setResult(outfile);
                    }
                }
            });
        }
    }

    //图片上传
    public void update(String outfile) {
        // 生成文件名
        String id = SharedUtils.getString(CameraActivity.this, "id");
        String filename = id + "_" + System.currentTimeMillis() + ".jpg";

    }

    //返回
    private void setResult(String outfile) {
        Intent intent = new Intent("inline-data");
        intent.setData(Uri.parse(outfile));
        setResult(RESULT_OK, intent);
        finish();
    }
}
