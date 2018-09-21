package org.runebase.wallet.ui.fragment.overview_fragment;


import org.runebase.wallet.model.gson.history.History;

public interface OverviewIteractor {
    History getHistory(int position);
}
