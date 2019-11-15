package org.runebase.wallet.ui.fragment.wallet_fragment;

import org.runebase.wallet.model.gson.history.History;

import javax.annotation.Nullable;

import io.realm.OrderedCollectionChangeSet;
import io.realm.RealmResults;


public interface HistoryInDbChangeListener<T>{
    void onHistoryChange(RealmResults<T> histories, @Nullable OrderedCollectionChangeSet changeSet);
}
