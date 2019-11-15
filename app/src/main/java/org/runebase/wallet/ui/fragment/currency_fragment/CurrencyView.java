package org.runebase.wallet.ui.fragment.currency_fragment;

import org.runebase.wallet.model.Currency;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;

public interface CurrencyView extends BaseFragmentView {
    void setCurrencyList(List<Currency> currencyList);
}
