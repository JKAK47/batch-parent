package org.vincent.rediscache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.vincent.rediscache.service.CacheService;

import java.util.List;

/**
 * batch-parent.org.vincent.rediscache <br/>
 * Created by PengRong on 2018/3/9. <br/>
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-03-09 10:07 <br/>
 */

@RestController
public class MessageController {
    @Autowired
    private CacheService cacheService;
    @RequestMapping(value = "/message",method = RequestMethod.GET)
    @ResponseBody
    public List<String> greeting(String user) {
        List<String> messages = cacheService.listMessages(user);
        return messages;
    }
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    @ResponseBody
    public String saveGreeting(String user,String message) {
        cacheService.addMessage(user,message);
        return "OK";
    }
}
