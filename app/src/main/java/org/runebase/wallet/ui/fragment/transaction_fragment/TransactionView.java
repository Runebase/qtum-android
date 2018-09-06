package org.runebase.wallet.ui.fragment.transaction_fragment;

import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;

public interface TransactionView extends BaseFragmentView {
    void setUpTransactionData(String value, String fee,String receivedTime, boolean confirmed, boolean isContractCall);
}
