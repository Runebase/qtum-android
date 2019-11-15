package org.runebase.wallet.ui.fragment.news_detail_fragment;

import org.runebase.wallet.model.news.News;

public interface NewsDetailInteractor {
    News getNews(int newsPosition);
}
