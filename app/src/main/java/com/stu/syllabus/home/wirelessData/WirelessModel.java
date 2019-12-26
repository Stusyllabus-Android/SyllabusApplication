package com.stu.syllabus.home.wirelessData;

import com.stu.syllabus.bean.WirelessInfo;
import com.stu.syllabus.retrofitApi.SchoolInternetApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class WirelessModel implements IWirelessModel {

    SchoolInternetApi mSchoolInternetApi;

    public WirelessModel(SchoolInternetApi schoolInternetApi) {
        mSchoolInternetApi = schoolInternetApi;
    }


    @Override
    public Observable<WirelessInfo> getWirelessInfo() {
        return mSchoolInternetApi.getInternetInfo()
                .subscribeOn(Schedulers.io())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        WirelessInfo.getInstance().setType(WirelessInfo.TYPE_UN_CONNECT);
                    }
                })
                .flatMap(new Function<String, ObservableSource<WirelessInfo>>() {
                    @Override
                    public ObservableSource<WirelessInfo> apply(String s)  {
                        WirelessInfo wirelessInfo = WirelessInfo.getInstance();

                        Document doc = Jsoup.parse(s);
                        Element tables = doc.getElementsByTag("table").first();
                        Elements trs = tables.select("tr");

                        if (trs.size() < 2) {
                            wirelessInfo.setType(WirelessInfo.TYPE_LOGOUT);
                            return Observable.just(wirelessInfo);
                        }

                        wirelessInfo.setName(trs.get(0).select("td").get(1).text());
                        wirelessInfo.setAllStream(trs.get(1).select("td").get(1).text());
                        wirelessInfo.setNowStream(trs.get(2).select("td").get(1).text());
                        wirelessInfo.setOutTime(trs.get(3).select("td").get(1).text());
                        wirelessInfo.setState(trs.get(4).select("td").get(1).text());
                        wirelessInfo.setType(WirelessInfo.TYPE_SUCCESS);
                        return Observable.just(wirelessInfo);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }


}
