package com.tsilva.newspapertask.service;

import com.tsilva.newspapertask.persistence.dao.IDeviceInfoJpaRepository;
import com.tsilva.newspapertask.persistence.dao.IGetPagesJpaRepository;
import com.tsilva.newspapertask.persistence.entity.DeviceInfo;
import com.tsilva.newspapertask.persistence.entity.GetPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@Service
public class NewsService implements INewsService
{
    @Autowired
    private IDeviceInfoJpaRepository iDeviceInfoJpaRepository;

    @Autowired
    private IGetPagesJpaRepository iGetPagesJpaRepository;

    @Override
    public void saveNews(DeviceInfo deviceInfo, GetPages getPages)
    {
        DeviceInfo deviceInfoResult = iDeviceInfoJpaRepository.save(deviceInfo);

        getPages.setDeviceInfo(deviceInfoResult);
        iGetPagesJpaRepository.save(getPages);
    }

    @Override
    public void getNews(String sort, int page, String filter)
    {
        
    }
}
