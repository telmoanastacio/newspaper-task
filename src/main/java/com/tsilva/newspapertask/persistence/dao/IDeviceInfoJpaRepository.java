package com.tsilva.newspapertask.persistence.dao;

import com.tsilva.newspapertask.persistence.entity.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@Repository
public interface IDeviceInfoJpaRepository extends JpaRepository<DeviceInfo, Long>
{
}
