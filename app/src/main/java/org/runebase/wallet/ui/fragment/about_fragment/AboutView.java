package org.runebase.wallet.ui.fragment.about_fragment;

import org.runebase.wallet.model.Version;
import org.runebase.wallet.ui.base.base_fragment.BaseFragmentView;

public interface AboutView extends BaseFragmentView {
    void updateVersion(Version version);
}
