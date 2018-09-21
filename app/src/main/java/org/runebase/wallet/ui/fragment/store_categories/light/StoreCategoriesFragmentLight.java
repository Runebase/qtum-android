package org.runebase.wallet.ui.fragment.store_categories.light;

import org.runebase.wallet.model.gson.QstoreContractType;
import org.runebase.wallet.ui.fragment.store_categories.StoreCategoriesAdapter;
import org.runebase.wallet.ui.fragment.store_categories.StoreCategoriesFragment;
import org.runebase.wallet.ui.fragment.store_categories.StoreCategoryViewHolder;

import java.util.List;

public class StoreCategoriesFragmentLight extends StoreCategoriesFragment {

    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.lyt_store_categories_light;
    }

    @Override
    public void setUpCategoriesList(List<QstoreContractType> list, StoreCategoryViewHolder.OnCategoryClickListener listener) {
        adapter = new StoreCategoriesAdapter(list, org.runebase.wallet.R.layout.lyt_store_category_list_item_light, listener);
        contentList.setAdapter(adapter);
    }
}
