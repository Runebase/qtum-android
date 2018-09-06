package org.runebase.wallet.ui.fragment.other_tokens;

import org.runebase.wallet.model.contract.Token;

import java.util.List;

import rx.Observable;

public interface OtherTokensInteractor {
    Observable<List<Token>> getTokenObservable();
}
