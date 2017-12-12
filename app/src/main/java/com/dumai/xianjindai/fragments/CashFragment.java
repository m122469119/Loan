package com.dumai.xianjindai.fragments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.activity.MyDataActivity;
import com.dumai.xianjindai.adapter.GalleryAdapter;
import com.dumai.xianjindai.base.BaseWGalleryActivity;
import com.dumai.xianjindai.fragment.AlertDialogFragment;
import com.dumai.xianjindai.util.DialogUtil;
import com.dumai.xianjindai.view.PerfectDataDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 现金贷
 * hrg
 * 2017年11月17日17:38:16
 */
public class CashFragment extends BaseWGalleryActivity {

    @BindView(R.id.ll_sing_loan)
    LinearLayout llSingLoan;
    @BindView(R.id.ll_cash_staging)
    LinearLayout llCashStaging;
    @BindView(R.id.tv_ex_repayment)
    TextView tvExRepayment;
    @BindView(R.id.tv_ex_schedule)
    TextView tvExSchedule;
    @BindView(R.id.tv_ex_rate)
    TextView tvExRate;

    @Override
    protected BaseAdapter initGalleryAdapter() {
        return new GalleryAdapter();
    }

    @OnClick({R.id.ll_sing_loan, R.id.ll_cash_staging})
    public void onViewClicked(View view) {
        View dia_view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout, null, false);
        switch (view.getId()) {
            case R.id.ll_sing_loan:
                alertMessage("请先完善个人资料");
                break;
            case R.id.ll_cash_staging:
                alertMessage("请先完善个人资料");
                break;
        }
    }

    private void alertMessage(String description) {
        // 产生背景变暗效果
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        final PerfectDataDialog perdataDialog = new PerfectDataDialog(getContext(), description, "取消", "完善资料");
        perdataDialog.setCanceledOnTouchOutside(false);
        perdataDialog.setCancelable(false);
        perdataDialog.show();
        perdataDialog.setClicklistener(new PerfectDataDialog.ClickListenerInterface() {


            @Override
            public void doCancel() {
                perdataDialog.dismiss();
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }

            @Override
            public void doUpdate() {
                perdataDialog.dismiss();
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
                startActivity(new Intent(getContext(), MyDataActivity.class));
            }
        });
    }
}
