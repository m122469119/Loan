package com.dumai.xianjindai.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.bean.InfoCreditCardBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 名称：CreditCardAdapter.java
 * 描述：信用卡列表
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/30 17:02
 */
public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.ViewHolder> {

    private List<InfoCreditCardBean> listData;

    public CreditCardAdapter(List<InfoCreditCardBean> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text1.setText(listData.get(position).getIssuingBank());
        holder.text2.setText("尾号" + listData.get(position).getCreditCardNum().substring(listData.get(position).getCreditCardNum().length() - 4));//截取最后4位
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text1)
        TextView text1;
        @BindView(R.id.text2)
        TextView text2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
