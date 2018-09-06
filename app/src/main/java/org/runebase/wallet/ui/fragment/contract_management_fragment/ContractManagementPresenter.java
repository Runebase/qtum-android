package org.runebase.wallet.ui.fragment.contract_management_fragment;

import org.runebase.wallet.model.contract.Contract;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

public interface ContractManagementPresenter extends BaseFragmentPresenter {
    Contract getContractByAddress(String contractAddress);
}
