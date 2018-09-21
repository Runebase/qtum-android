package org.runebase.wallet.ui.fragment.transaction_fragment;

import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

interface TransactionPresenter extends BaseFragmentPresenter {
    void openTransactionView(int position);
}
