package com.tsilva.newspapertask.service;

import com.tsilva.newspapertask.controller.rest.contract.request.EpaperRequest;
import com.tsilva.newspapertask.persistence.dao.IDeviceInfoJpaRepository;
import com.tsilva.newspapertask.persistence.dao.IGetPagesJpaRepository;
import com.tsilva.newspapertask.persistence.entity.DeviceInfo;
import com.tsilva.newspapertask.persistence.entity.GetPages;
import com.tsilva.newspapertask.service.mapper.MapperEpaperRequest;
import com.tsilva.newspapertask.service.sort.INewsOrchestrator;
import com.tsilva.newspapertask.service.sort.NewsOrchestratorByName;
import com.tsilva.newspapertask.service.sort.NewsOrchestratorByUpdateTimeStamp;
import com.tsilva.newspapertask.service.sort.NewsSortEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@Service
public class NewsService implements INewsService
{
    private static final Logger LOG = LoggerFactory.getLogger(NewsService.class);

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
    @Nullable
    public List<EpaperRequest> getNews(@Nullable String sort, @Nullable Integer page, @Nullable String filter)
    {
        sort = sort == null ? NewsSortEnum.NAME.getFilterName() : sort;
        page = (page == null || page < 0) ? 1 : page;

        NewsSortEnum newsSortEnum = NewsSortEnum.getNewsSortEnum(sort);

        List<DeviceInfo> deviceInfoList = null;
        if(newsSortEnum.equals(NewsSortEnum.TIME_STAMP))
        {
            try
            {
                INewsOrchestrator<Long> iNewsOrchestrator = new NewsOrchestratorByUpdateTimeStamp();

                if(filter == null)
                {
                    deviceInfoList = iDeviceInfoJpaRepository.findAll(iNewsOrchestrator.getSort());
                }
                else
                {
                    deviceInfoList = iDeviceInfoJpaRepository.findAllDeviceInfoGreaterThanOrEqualsUpdateTs(
                            iNewsOrchestrator.getValue(filter), iNewsOrchestrator.getSort());
                }
            }
            catch (Exception e)
            {
                LOG.debug("Filter parse failed", e);
            }
        }
        else
        {
            INewsOrchestrator<String> iNewsOrchestrator = new NewsOrchestratorByName();

            if(filter == null)
            {
                deviceInfoList = iDeviceInfoJpaRepository.findAll(iNewsOrchestrator.getSort());
            }
            else
            {
                deviceInfoList = iDeviceInfoJpaRepository.findAllDeviceInfoLikeAppName(
                        iNewsOrchestrator.getValue(filter), iNewsOrchestrator.getSort());
            }
        }

        if(deviceInfoList == null || deviceInfoList.isEmpty())
        {
            return null;
        }

        deviceInfoList = getDeviceInfoListByPage(deviceInfoList, page);

        return new MapperEpaperRequest().map(deviceInfoList);
    }

    /**
     *
     * @return 10 results
     */
    private List<DeviceInfo> getDeviceInfoListByPage(
            @NonNull List<DeviceInfo> deviceInfoList,
            @NonNull Integer page)
    {
        final int results = 10;

        int indexStart = results * page - results;
        int indexEnd = Math.min(deviceInfoList.size(), indexStart + results);

        return deviceInfoList.subList(indexStart, indexEnd);
    }
}
