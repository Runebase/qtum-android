package org.runebase.wallet.ui.fragment.watch_token_fragment;

import org.runebase.wallet.model.ContractTemplate;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

public interface WatchTokenPresenter extends BaseFragmentPresenter {

    void onOkClick(String name, String address);

    void onContractAddressEntered(String contractAddress);

}
