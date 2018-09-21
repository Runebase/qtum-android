package org.runebase.wallet.ui.fragment.currency_fragment;

import android.content.Context;

import org.runebase.wallet.R;
import org.runebase.wallet.model.Currency;
import org.runebase.wallet.model.CurrencyToken;
import org.runebase.wallet.model.contract.ContractCreationStatus;
import org.runebase.wallet.model.contract.Token;
import org.runebase.wallet.datastorage.TinyDB;

import java.util.ArrayList;
import java.util.List;

class CurrencyInteractorImpl implements CurrencyInteractor {

    private Context mContext;

    CurrencyInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public List<Currency> getCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        List<Token> tokens = (new TinyDB(mContext)).getTokenList();
        Currency currency = new Currency("Runebase " + mContext.getString(R.string.default_currency));
        currencies.add(currency);
        for (Token token : tokens) {
            if (token.getCreationStatus().equals(ContractCreationStatus.Created) && token.isSubscribe()) {
                currency = new CurrencyToken(token.getContractName(), token);
                currencies.add(currency);
            }
        }
        return currencies;
    }
}
