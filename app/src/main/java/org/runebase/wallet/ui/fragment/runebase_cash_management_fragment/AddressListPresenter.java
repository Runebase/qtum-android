package org.runebase.wallet.ui.fragment.runebase_cash_management_fragment;

import org.runebase.wallet.model.AddressWithBalance;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

import java.util.List;

public interface AddressListPresenter extends BaseFragmentPresenter {
    AddressWithBalance getKeyWithBalanceFrom();

    void setKeyWithBalanceFrom(AddressWithBalance keyWithBalanceFrom);

    List<AddressWithBalance> getAddressWithBalanceList();
}
