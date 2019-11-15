package org.runebase.wallet.ui.fragment.language_fragment;

import org.runebase.wallet.ui.base.base_fragment.BaseFragmentPresenter;

public interface LanguagePresenter extends BaseFragmentPresenter {
    String getCurrentLanguage();

    void setCurrentLanguage(String language);
}
