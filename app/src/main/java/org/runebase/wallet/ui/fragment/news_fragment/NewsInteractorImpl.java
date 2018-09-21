package org.runebase.wallet.ui.fragment.news_fragment;

import android.content.Context;

import org.runebase.wallet.dataprovider.rest_api.medium.MediumService;
import org.runebase.wallet.datastorage.TinyDB;
import org.runebase.wallet.model.news.News;
import org.runebase.wallet.model.news.RssFeed;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.Observable;
import rx.internal.util.SubscriptionList;

public class NewsInteractorImpl implements NewsInteractor {

    private WeakReference<Context> mContext;
    private SubscriptionList mSubscriptionList = new SubscriptionList();
    private final String MEDIUM_RUNEBASE_CHANEL = "@runebase";

    public NewsInteractorImpl(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    public Observable<RssFeed> getMediumRssFeed() {
        return MediumService.getInstance().getRssFeed(MEDIUM_RUNEBASE_CHANEL);
    }

    @Override
    public List<News> getNewses() {
        TinyDB tinyDB = new TinyDB(mContext.get());
        return tinyDB.getNewsList();
    }

    @Override
    public void setNewses(List<News> newses) {
        TinyDB tinyDB = new TinyDB(mContext.get());
        tinyDB.putNewsList(newses);
    }

    @Override
    public void unSubscribe() {
        mSubscriptionList.clear();
    }
}
