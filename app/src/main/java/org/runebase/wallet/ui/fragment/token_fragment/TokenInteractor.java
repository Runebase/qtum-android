package org.runebase.wallet.ui.fragment.token_fragment;

import org.runebase.wallet.model.contract.Token;
import org.runebase.wallet.model.gson.history.History;
import org.runebase.wallet.model.gson.history.HistoryResponse;
import org.runebase.wallet.model.gson.history.TransactionReceipt;
import org.runebase.wallet.model.gson.token_history.TokenHistory;
import org.runebase.wallet.model.gson.token_history.TokenHistoryResponse;
import org.runebase.wallet.ui.fragment.wallet_fragment.HistoryInDbChangeListener;
import org.runebase.wallet.utils.ContractManagementHelper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public interface TokenInteractor {

    String getCurrentAddress();

    String readAbiContract(String uiid);

    void setupPropertyTotalSupplyValue(Token token, Subscriber<String> stringSubscriber);

    void setupPropertySymbolValue(Token token, Subscriber<String> stringSubscriber);

    void setupPropertyNameValue(Token token, Subscriber<String> stringSubscriber);

    void setupPropertyDecimalsValue(Token token, Subscriber<String> stringSubscriber);

    Token setTokenDecimals(Token token, String value);

    String handleTotalSupplyValue(Token token, String value);

    Observable<TokenHistoryResponse> getHistoryList(String contractAddress, int limit, int offset);

    List<String> getAddresses();

    int getTokenHistoryDbCount();

    List<TokenHistory> getTokenHistoryDb(int startIndex, int length);

    void updateHistoryInRealm(final List<TokenHistory> histories);

    void addHistoryInDbChangeListener(HistoryInDbChangeListener listener);

    TransactionReceipt getReceiptByRxhHashFromRealm(String txHash);

    Observable<List<TransactionReceipt>> getTransactionReceipt(String txHash);

    void setUpHistoryReceipt(String txHash);

    void updateReceiptInRealm(TransactionReceipt transactionReceipt);

}