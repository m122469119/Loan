package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.adapter.CreditCardAdapter;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.bean.InfoCreditCardBean;
import com.dumai.xianjindai.bean.InfoIdentityBean;
import com.dumai.xianjindai.commons.ToUIEvent;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.xrecyclerview.ProgressStyle;
import com.dumai.xianjindai.view.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 名称：CreditCardDataActivity.java
 * 描述：信用卡信息
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-27 09:47:57
 */
public class CreditCardDataActivity extends BaseActivity {

    @BindView(R.id.ll_no_add_credit_card)
    LinearLayout llNoAddCreditCard;
    @BindView(R.id.recyclerview)
    XRecyclerView recyclerview;
    private CreditCardAdapter mAdapter;
    private List<InfoCreditCardBean> listData;
    private int times = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_credit_card_data;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("信用卡信息");
        Toolbar toolbar = toolbarHelper.getToolbar();
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbarHelper.setMenuTitle("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreditCardDataActivity.this, CreditCardAddActivity.class));
            }
        });
    }

    @Override
    protected void init() {
        super.init();

        getCreditCardList();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerview.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        listData.clear();
                        getCreditCardList();
                        mAdapter.notifyDataSetChanged();
                        recyclerview.refreshComplete();//下拉刷新完成
                    }

                }, 1000);

            }

            @Override
            public void onLoadMore() {
                if (times < 2) {//当前页/总页
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            recyclerview.loadMoreComplete();//加载更多完成

                            //更多数据

                            recyclerview.loadMoreComplete();//加载更多完成
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            //更多数据
                            mAdapter.notifyDataSetChanged();
                            recyclerview.setNoMore(true);//没有数据了
                        }
                    }, 1000);
                }
                times++;
            }
        });
    }

    private void getCreditCardList() {
        // 1.先查询表中所有数据
        List<InfoCreditCardBean> mInfoList = InfoIdentityBean.findAll(InfoCreditCardBean.class);
        if (mInfoList.size() > 0) {
            listData = mInfoList;//为集合赋值
            mAdapter = new CreditCardAdapter(listData);
            recyclerview.setAdapter(mAdapter);
            llNoAddCreditCard.setVisibility(View.GONE);
        } else {
            llNoAddCreditCard.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onEvent(ToUIEvent event) {
        super.onEvent(event);
        if (event.getWhat() == ToUIEvent.CREDIT_CARD_EVENT) {
            getCreditCardList();
        }
    }
}
