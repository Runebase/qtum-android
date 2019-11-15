package org.runebase.wallet.ui.fragment.wallet_fragment;

import org.runebase.wallet.model.gson.history.History;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

import java.util.List;

import javax.annotation.Nullable;

import io.realm.OrderedCollectionChangeSet;
import io.realm.Realm;
import io.realm.RealmResults;

public interface WalletView extends BaseFragmentView {
    void updateHistory(List<History> histories, @Nullable OrderedCollectionChangeSet changeSet, int visibleItemCount);

    void updateHistory(List<History> histories, int startIndex, int insertCount);

    void setAdapterNull();

    void updateBalance(String balance, String unconfirmedBalance);

    void updatePubKey(String pubKey);

    void startRefreshAnimation();

    void stopRefreshRecyclerAnimation();

    void showBottomLoader();

    void hideBottomLoader();

    void clearAdapter();

    void offlineModeView();

    void onlineModeView();
}
