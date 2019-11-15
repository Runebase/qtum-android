package org.runebase.wallet.ui.fragment.pin_fragment.dark;

import org.runebase.wallet.R;
import org.runebase.wallet.ui.fragment.pin_fragment.PinFragment;

public class PinFragmentDark extends PinFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_pin;
    }

    @Override
    public int getThemedStatusBarColor() {
        return R.color.background;
    }
}
