package org.runebase.wallet.ui.fragment.wallet_main_fragment;

import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

public interface WalletMainView extends BaseFragmentView {
    void showOtherTokens(boolean isShow);

    void showPageIndicator();

    void hidePageIndicator();
}
