package org.runebase.wallet.ui.fragment.news_fragment;

import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

public interface NewsPresenter extends BaseFragmentPresenter {
    void onRefresh();

    void onNetworkStateChanged(boolean networkConnectedFlag);
}
