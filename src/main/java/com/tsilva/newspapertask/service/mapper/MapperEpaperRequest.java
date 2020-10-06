package com.tsilva.newspapertask.service.mapper;

import com.tsilva.newspapertask.controller.rest.contract.request.*;
import com.tsilva.newspapertask.persistence.entity.DeviceInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Telmo Silva on 06.10.2020.
 */

public class MapperEpaperRequest implements IMapper<List<EpaperRequest>, List<DeviceInfo>>
{
    @Override
    public List<EpaperRequest> map(List<DeviceInfo> value)
    {
        return value.stream().map(deviceInfoEntity ->
        {
            com.tsilva.newspapertask.controller.rest.contract.request.DeviceInfo deviceInfo =
                    new com.tsilva.newspapertask.controller.rest.contract.request.DeviceInfo(
                            new ScreenInfo(deviceInfoEntity.getScreenWidth(),
                                    deviceInfoEntity.getScreenHeight(),
                                    deviceInfoEntity.getScreenDpi()),
                            new OsInfo(deviceInfoEntity.getOsName(), deviceInfoEntity.getOsVersion()),
                            new AppInfo(deviceInfoEntity.getAppName(), deviceInfoEntity.getAppVersion()),
                            deviceInfoEntity.getName(),
                            deviceInfoEntity.getIdStr());

            // this section will make n requests, batch is not implemented
            GetPages getPages = new GetPages(deviceInfoEntity.getGetPages().getEditionDefId(),
                    deviceInfoEntity.getGetPages().getPublicationDate());

            return new EpaperRequest(deviceInfo, getPages);
        }).collect(Collectors.toList());
    }
}
