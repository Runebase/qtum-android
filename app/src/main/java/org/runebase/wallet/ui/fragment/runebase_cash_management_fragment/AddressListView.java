package org.runebase.wallet.ui.fragment.runebase_cash_management_fragment;

import org.runebase.wallet.model.AddressWithBalance;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;

public interface AddressListView extends BaseFragmentView {
    void updateAddressList(List<AddressWithBalance> deterministicKeyWithBalance);
}
