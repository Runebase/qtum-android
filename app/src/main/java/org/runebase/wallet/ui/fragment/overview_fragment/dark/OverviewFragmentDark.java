package org.runebase.wallet.ui.fragment.overview_fragment.dark;


import android.util.Pair;

import org.runebase.wallet.R;
import org.runebase.wallet.ui.fragment.overview_fragment.CopyableOverviewItem;
import org.runebase.wallet.ui.fragment.overview_fragment.OverviewFragment;

import java.util.List;

public class OverviewFragmentDark extends OverviewFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_overview;
    }

    @Override
    public void setUpOverview(List<CopyableOverviewItem> overview) {
        mOverviewAdapter = new OverviewAdapter(overview, R.layout.item_overview);
        mRecyclerViewOverview.setAdapter(mOverviewAdapter);
    }

}
