package se.iths.cecilia.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.cecilia.atm.models.AccountComponent;
import se.iths.cecilia.atm.services.ATMService;

@Controller
@RequestMapping("/")
public class ATMController {

    @GetMapping
    public String getBalance(Model model) {
        ATMService service = new ATMService(new AccountComponent());
        int balance = service.getBalance();
        model.addAttribute("balance", balance);
        return "index";
    }
}
