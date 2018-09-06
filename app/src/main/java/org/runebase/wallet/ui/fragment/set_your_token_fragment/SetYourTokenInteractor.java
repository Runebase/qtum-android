package org.runebase.wallet.ui.fragment.set_your_token_fragment;

import org.runebase.wallet.model.contract.ContractMethod;

public interface SetYourTokenInteractor {
    ContractMethod getContractConstructor(String uiid);
}
