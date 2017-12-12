package com.dumai.xianjindai.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dumai.xianjindai.R;

/**
 * 名称：com.dumai.xianjindai.view
 * 描述：计算额度Dialog弹出框
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017-11-27 17:22:13
 */
public class CalculaLimitDialog extends Dialog {

    private Context context;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {

        void doClose();

        void doCancel();

        void doUpdate();

    }

    public CalculaLimitDialog(Context context, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
    }

    public CalculaLimitDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_calcula_limit, null);
        setContentView(view);

        ImageView imgClose = (ImageView) view.findViewById(R.id.img_close);
        Button cancel = (Button) view.findViewById(R.id.btn_calcula_limit);
        Button update = (Button) view.findViewById(R.id.btn_calcula_data);

        imgClose.setOnClickListener(new clickListener());
        cancel.setOnClickListener(new clickListener());
        update.setOnClickListener(new clickListener());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL);

        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.7); // 宽度设置为屏幕的0.7
        lp.height = (int) (d.heightPixels * 0.6); // 高度设置为屏幕的0.5
        // lp.width=DisplayUtil.dip2px(context, 270);
        // lp.height=DisplayUtil.dip2px(context, 175);
        dialogWindow.setAttributes(lp);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            switch (id) {
                case R.id.img_close:
                    clickListenerInterface.doClose();
                    break;
                case R.id.btn_calcula_limit:
                    clickListenerInterface.doCancel();
                    break;
                case R.id.btn_calcula_data:
                    clickListenerInterface.doUpdate();
            }
        }
    }
}
