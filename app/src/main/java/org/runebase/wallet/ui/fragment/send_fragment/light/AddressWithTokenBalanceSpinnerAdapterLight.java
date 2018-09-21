package org.runebase.wallet.ui.fragment.send_fragment.light;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import org.runebase.wallet.R;
import org.runebase.wallet.model.gson.token_balance.TokenBalance;
import org.runebase.wallet.ui.fragment.send_fragment.AddressWithTokenBalanceSpinnerAdapter;


public class AddressWithTokenBalanceSpinnerAdapterLight extends AddressWithTokenBalanceSpinnerAdapter {

    public AddressWithTokenBalanceSpinnerAdapterLight(@NonNull Context context, TokenBalance tokenBalance, String currency, int decimalUnits) {
        super(context, tokenBalance, currency, decimalUnits);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i, R.layout.item_address_spinner_light_send, viewGroup);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomViewDropDown(position, R.layout.item_address_spinner_dropdown_light_send, parent);
    }
}
