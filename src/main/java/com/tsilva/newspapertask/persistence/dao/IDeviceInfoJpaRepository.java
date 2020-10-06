package com.tsilva.newspapertask.persistence.dao;

import com.tsilva.newspapertask.persistence.entity.DeviceInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@Repository
public interface IDeviceInfoJpaRepository extends JpaRepository<DeviceInfo, Long>
{
    @Query("select d from DeviceInfo d where d.appName like %:appName%")
    public abstract List<DeviceInfo> findAllDeviceInfoLikeAppName(String appName, Sort sort);

    @Query("select d from DeviceInfo d where d.updateTs >= :updateTs")
    public abstract List<DeviceInfo> findAllDeviceInfoGreaterThanOrEqualsUpdateTs(Long updateTs, Sort sort);
}
