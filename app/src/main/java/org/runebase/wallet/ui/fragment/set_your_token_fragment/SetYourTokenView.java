package org.runebase.wallet.ui.fragment.set_your_token_fragment;

import org.runebase.wallet.model.contract.ContractMethodParameter;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;

public interface SetYourTokenView extends BaseFragmentView {
    void onContractConstructorPrepared(List<ContractMethodParameter> params);
}
