package org.runebase.wallet.ui.fragment.token_cash_management_fragment;

import org.runebase.wallet.model.DeterministicKeyWithTokenBalance;
import org.runebase.wallet.model.contract.Token;
import org.runebase.wallet.model.gson.token_balance.TokenBalance;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

import java.util.List;

public interface AddressesListTokenPresenter extends BaseFragmentPresenter {
    String getContractAddress();

    void setTokenBalance(TokenBalance tokenBalance);

    void processTokenBalances(TokenBalance tokenBalance);

    void setToken(Token token);

    void setCurrency(String currency);

    int getDecimalUnits();

    List<DeterministicKeyWithTokenBalance> getKeysWithTokenBalance();

    String getCurrency();

    void setKeyWithTokenBalanceFrom(DeterministicKeyWithTokenBalance itemAtPosition);

    DeterministicKeyWithTokenBalance getKeyWithTokenBalanceFrom();

    void transfer(DeterministicKeyWithTokenBalance keyWithBalanceTo, DeterministicKeyWithTokenBalance keyWithTokenBalanceFrom, String s);
}
