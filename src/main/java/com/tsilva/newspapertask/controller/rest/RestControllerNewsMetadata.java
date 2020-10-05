package com.tsilva.newspapertask.controller.rest;

import com.tsilva.newspapertask.controller.rest.contract.request.EpaperRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@RestController
@RequestMapping(value = "/news", method = RequestMethod.POST)
public class RestControllerNewsMetadata
{
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public EpaperRequest doSomething(@RequestBody EpaperRequest epaperRequest)
    {
        return epaperRequest;
    }
}
