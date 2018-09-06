package org.runebase.wallet.ui.fragment.subscribe_tokens_fragment;

import org.runebase.wallet.model.contract.Token;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

import java.util.List;

public interface AddressesListTokenPresenter extends BaseFragmentPresenter {
    void saveTokens(List<Token> tokens);
}
