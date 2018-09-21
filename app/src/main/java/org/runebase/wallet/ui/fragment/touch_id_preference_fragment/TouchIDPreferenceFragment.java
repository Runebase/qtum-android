package org.runebase.wallet.ui.fragment.touch_id_preference_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import org.runebase.wallet.ui.fragment.backup_wallet_fragment.BackUpWalletFragment;
import org.runebase.wallet.ui.fragment.wallet_main_fragment.WalletMainFragment;
import org.runebase.wallet.ui.fragment_factory.Factory;
import org.runebase.wallet.ui.base.base_fragment.BaseFragment;

import butterknife.OnClick;

public abstract class TouchIDPreferenceFragment extends BaseFragment implements TouchIDPreferenceView {

    private static final String IS_IMPORTING = "is_importing";
    private static final String PIN = "pin";
    private boolean mIsImporting;

    @OnClick({org.runebase.wallet.R.id.bt_enable_touch_id, org.runebase.wallet.R.id.bt_not_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case org.runebase.wallet.R.id.bt_enable_touch_id:
                getPresenter().onEnableTouchIdClick();
            case org.runebase.wallet.R.id.bt_not_now:
                if (mIsImporting) {
                    WalletMainFragment walletFragment = WalletMainFragment.newInstance(getContext());
                    getMainActivity().setRootFragment(walletFragment);
                    openRootFragment(walletFragment);
                } else {
                    BaseFragment backUpWalletFragment = BackUpWalletFragment.newInstance(getContext(), true, getPin());
                    openFragment(backUpWalletFragment);
                }

                break;
        }
    }

    public static BaseFragment newInstance(Context context, boolean isImporting, String pin) {

        Bundle args = new Bundle();
        args.putBoolean(IS_IMPORTING, isImporting);
        args.putString(PIN, pin);
        BaseFragment fragment = Factory.instantiateFragment(context, TouchIDPreferenceFragment.class);
        fragment.setArguments(args);
        return fragment;
    }

    private TouchIDPreferencePresenter mTouchIDPreferencePresenterImpl;

    @Override
    public void initializeViews() {
        super.initializeViews();
        mIsImporting = getArguments().getBoolean(IS_IMPORTING);
    }

    @Override
    protected void createPresenter() {
        mTouchIDPreferencePresenterImpl = new TouchIDPreferencePresenterImpl(this, new TouchIDInterractorImpl(getContext()));
    }

    @Override
    protected TouchIDPreferencePresenter getPresenter() {
        return mTouchIDPreferencePresenterImpl;
    }

    @Override
    public String getPin() {
        return getArguments().getString(PIN);
    }
}