package ru.chabanov.parts.contraller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.chabanov.parts.model.Parts;
import ru.chabanov.parts.model.Search;
import ru.chabanov.parts.service.UserService;

@Controller
public class UserContraller {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/")
    public String index() {
        return "index";
    }



    @RequestMapping(value = "parts/{page}")
    public String listComps(@PathVariable("page") int page, Model model,@ModelAttribute(value = "necessary") Search search) {
        try {
                   if(search.getNecessary() == 0 || search.getNecessary()== 1 )  {

                   model.addAttribute("listComps", this.userService.sortComps(10, page, search.getNecessary()));
                   model.addAttribute("compsCount", this.userService.getCompsCount(search.getNecessary()));

               }else {

                   model.addAttribute("listComps", this.userService.listComps(10, page, ""));
                   model.addAttribute("compsCount", this.userService.getCompsCount());
               }
            model.addAttribute("selected",search.getNecessary());
            model.addAttribute("parts", new Parts());
            model.addAttribute("page", page);
            model.addAttribute("search", new Search());
            model.addAttribute("AmountOfFullPC",this.userService.getAmountOfFullComplect());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "parts";
    }

    @RequestMapping(value = "parts/search", method = RequestMethod.POST)
    public String search(@ModelAttribute(value = "search") Search search, Model model) {
        model.addAttribute("listComps", this.userService.listComps(0,1, search.getCriterion()));
        return "parts";
    }
    @RequestMapping(value = "parts/add", method = RequestMethod.POST)
    public String addParts(@ModelAttribute("parts") Parts parts) {
        if(parts.getId() == 0) {
            this.userService.addParts(parts);
            long partsCount = this.userService.getCompsCount();
            return "redirect:/parts/" + (partsCount/10 + (partsCount%10==0?0:1));
        } else {
            System.out.println(" this.userService.updateParts(parts);");
            this.userService.updateParts(parts);
            return "redirect:/parts/1";
        }
    }
    @RequestMapping("delete/{id}")
    public String deleteParts(@PathVariable("id") int id) {
        this.userService.deleteParts(id);
        return "redirect:/parts/1";
    }

    @RequestMapping("edit/{id}")
    public String editParts(@PathVariable("id") int id, Model model) {
        model.addAttribute("parts", this.userService.getPartsById(id));
        return "parts";
    }
}
