package com.tsilva.newspapertask.service.mapper;

/**
 * Created by Telmo Silva on 06.10.2020.
 */

public interface IMapper <O , I>
{
    public O map(I value);
}
