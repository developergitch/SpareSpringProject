package com.joseph.web;

import com.joseph.models.Item;
import com.joseph.services.ItemService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joseph on 4/7/17.
 * Email: developergitch@outlook.com
 */
@Controller
@RequestMapping("/")
public class HomeController {


    private HttpSession session;
    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String handleHomePage(Model model){
        if(session.getAttribute("account")==null){
            session.setAttribute("logged",false);
        }else {
            session.setAttribute("logged",true);
        }
        return "landing";
    }
    @RequestMapping(method = RequestMethod.GET,path = "/navigation")
    public String navigationTemplate(Model model){
        Calendar calendar=Calendar.getInstance();
        model.addAttribute("date",calendar.getTime());
        model.addAttribute("name","joseph");
        return "navigation";
    }

}
