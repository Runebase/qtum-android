package org.runebase.wallet.ui.fragment.token_cash_management_fragment;

import org.bitcoinj.crypto.DeterministicKey;
import org.runebase.wallet.model.DeterministicKeyWithTokenBalance;
import org.runebase.wallet.model.gson.token_balance.TokenBalance;

import java.util.List;

public interface AddressesListTokenInteractor {
    boolean isCurrencyValid(String currency);

    boolean isAmountValid(String amountString);

    List<DeterministicKey> getKeys(int i);

    boolean isValidForAddress(TokenBalance tokenBalance, DeterministicKeyWithTokenBalance keyWithTokenBalanceFrom);

    boolean isValidBalance(TokenBalance tokenBalance, DeterministicKeyWithTokenBalance keyWithTokenBalanceFrom, String amountString);
}
