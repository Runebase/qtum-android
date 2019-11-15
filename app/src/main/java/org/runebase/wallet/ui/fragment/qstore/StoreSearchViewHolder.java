package org.runebase.wallet.ui.fragment.qstore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.runebase.wallet.R;
import org.runebase.wallet.model.gson.qstore.QSearchItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreSearchViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.cost)
    TextView cost;

    public StoreSearchViewHolder(final View itemView, final StoreItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick(item);
            }
        });
    }

    private QSearchItem item;

    public void bind(QSearchItem item) {
        this.item = item;
        icon.setImageResource(item.getIcon());
        title.setText(item.name);
        cost.setText(String.valueOf(item.price));
    }
}
