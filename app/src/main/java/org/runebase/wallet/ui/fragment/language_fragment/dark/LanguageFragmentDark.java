package org.runebase.wallet.ui.fragment.language_fragment.dark;

import android.util.Pair;

import org.runebase.wallet.ui.fragment.language_fragment.LanguageFragment;

import java.util.List;

public class LanguageFragmentDark extends LanguageFragment {
    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.fragment_language;
    }

    @Override
    public void setUpLanguagesList(List<Pair<String, String>> languagesList) {
        mLanguagesList = languagesList;
        mLanguageAdapter = new LanguageAdapterDark(languagesList, this);
        mRecyclerView.setAdapter(mLanguageAdapter);
    }
}
