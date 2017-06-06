package com.pixelplex.qtum.ui.fragment.WalletMainFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.pixelplex.qtum.R;
import com.pixelplex.qtum.ui.fragment.BaseFragment.BaseFragment;
import com.pixelplex.qtum.ui.fragment.BaseFragment.BaseFragmentPresenterImpl;
import com.pixelplex.qtum.ui.fragment.OtherTokens.OtherTokensFragment;
import com.pixelplex.qtum.ui.fragment.TokenFragment.TokenFragment;
import com.pixelplex.qtum.ui.fragment.WalletFragment.WalletFragment;

import butterknife.BindView;

/**
 * Created by kirillvolkov on 25.05.17.
 */

public class WalletMainFragment extends BaseFragment implements WalletMainFragmentView {

    public final int LAYOUT = R.layout.fragment_wallet_main;

    public static WalletMainFragment newInstance() {
        Bundle args = new Bundle();
        WalletMainFragment fragment = new WalletMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private WalletMainFragmentPresenterImpl mWalletMainFragmentPresenter;

    @BindView(R.id.view_pager)
    ViewPager pager;

    @Override
    protected void createPresenter() {
        mWalletMainFragmentPresenter = new WalletMainFragmentPresenterImpl(this);
    }

    @Override
    protected BaseFragmentPresenterImpl getPresenter() {
        return mWalletMainFragmentPresenter;
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        pager.setAdapter(new FragmentAdapter(getFragmentManager()));
    }

    @Override
    protected int getLayout() {
        return LAYOUT;
    }

    public class FragmentAdapter extends FragmentStatePagerAdapter {

        private static final int NUM_ITEMS = 2;

        public FragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return WalletFragment.newInstance();
                case 1:
                    return OtherTokensFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}
