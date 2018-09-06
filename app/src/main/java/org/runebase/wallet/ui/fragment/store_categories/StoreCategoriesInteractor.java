package org.runebase.wallet.ui.fragment.store_categories;

import org.runebase.wallet.model.gson.QstoreContractType;

import java.util.List;

import rx.Observable;

public interface StoreCategoriesInteractor {
    Observable<List<QstoreContractType>> contractTypesObservable();
}
