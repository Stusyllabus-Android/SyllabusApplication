package com.stu.syllabus.home.wirelessData;


import com.stu.syllabus.bean.WirelessInfo;

import io.reactivex.Observable;

public interface IWirelessModel {

    Observable<WirelessInfo> getWirelessInfo();

}
