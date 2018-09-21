package org.runebase.wallet.ui.fragment.pin_fragment.dark;

import org.runebase.wallet.R;
import org.runebase.wallet.ui.activity.main_activity.MainActivity;
import org.runebase.wallet.ui.fragment.pin_fragment.PinFragment;

public class PinFragmentDark extends PinFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_pin;
    }

    boolean isBottomNavigationViewVisible;

    @Override
    public void initializeViews() {
        super.initializeViews();
        isBottomNavigationViewVisible = ((MainActivity) getActivity()).isBottomNavigationViewVisible();
        ((MainActivity) getActivity()).hideBottomNavigationView(R.color.background);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isBottomNavigationViewVisible) {
            ((MainActivity) getActivity()).showBottomNavigationView(true);
        } else {
            ((MainActivity) getActivity()).hideBottomNavigationView(R.color.background);
        }
    }
}
