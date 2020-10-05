package com.tsilva.newspapertask.controller.rest;

import com.tsilva.newspapertask.controller.rest.contract.request.EpaperRequest;
import com.tsilva.newspapertask.controller.rest.util.XmlUtil;
import com.tsilva.newspapertask.controller.rest.validator.EPaperRequestValidatorXsd;
import com.tsilva.newspapertask.controller.rest.validator.XsdValidator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

@RestController
@RequestMapping(value = "/news", method = RequestMethod.POST)
public class RestControllerNews
{
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    /**
     * @return true if xml is valid against xsd
     */
    public boolean postNews(@RequestBody EpaperRequest epaperRequest, HttpServletResponse response)
    {
        XsdValidator xsdValidator = new EPaperRequestValidatorXsd();
        boolean isValid = xsdValidator.validate(XmlUtil.toXML(epaperRequest));

        if(isValid)
        {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return isValid;
    }
}
