package com.joseph.web;

import com.joseph.beans.AuthData;
import com.joseph.models.Account;
import com.joseph.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Created by joseph on 4/8/17.
 * Email: developergitch@outlook.com
 */
@Controller
@RequestMapping(path = {"/signin"})
public class SigninController {
    private AccountService accountService;
    private HttpSession httpSession;
    @Autowired
    public void setAccountService(AccountService accountService){
        this.accountService=accountService;
    }
    @Autowired
    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handleInitialRequest(Model model){
        model.addAttribute("authData",new AuthData());
        return "login";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String processRequest(@Valid AuthData authData, BindingResult result){
        boolean status;
        if(!result.hasErrors()){
            //call valdation method to validate user data against one stored in database
            status=authenticateUser(authData.getEmail(),authData.getPassword());
            if(status){
                httpSession.setAttribute("account",accountService.getAccount(authData.getEmail()));
                return "redirect:/explore";
            }else {
                return "redirect:/signin";
            }
        }else {
            return "redirect:/signin";
        }
    }
    public boolean authenticateUser(String email,String password){
        Account account=accountService.getAccount(email);
        if(account!=null){
            if(Objects.equals(account.getEmail(), email) && Objects.equals(account.getPasswordHash(), password)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }
}