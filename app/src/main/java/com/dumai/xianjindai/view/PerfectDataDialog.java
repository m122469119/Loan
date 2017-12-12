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
import android.widget.TextView;

import com.dumai.xianjindai.R;

/**
 * 名称：com.dumai.xianjindai.view
 * 描述：完善资料Dialog弹出框
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/23 13:55
 */
public class PerfectDataDialog extends Dialog {

    private Context context;
    private String title;
    private String confirmButtonText1;
    private String confirmButtonText2;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {

        public void doCancel();

        public void doUpdate();

    }

    public PerfectDataDialog(Context context, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
    }

    public PerfectDataDialog(Context context, String title, String confirmButtonText1,
                             String confirmButtonText2) {
        super(context, R.style.dialog);
        this.context = context;
        this.title = title;
        this.confirmButtonText1 = confirmButtonText1;
        this.confirmButtonText2 = confirmButtonText2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_perfect_data, null);
        setContentView(view);

        TextView response = (TextView) view.findViewById(R.id.response);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        TextView update = (TextView) view.findViewById(R.id.update);

        response.setText(title);
        cancel.setText(confirmButtonText1);
        update.setText(confirmButtonText2);

        cancel.setOnClickListener(new clickListener());
        update.setOnClickListener(new clickListener());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL);

        // Window dialogWindow = getWindow();
        // WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.7); // 宽度设置为屏幕的0.6
        lp.height = (int) (d.heightPixels * 0.2); // 高度设置为屏幕的0.18
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
                case R.id.cancel:
                    clickListenerInterface.doCancel();
                    break;
                case R.id.update:
                    clickListenerInterface.doUpdate();
            }
        }
    }
}
