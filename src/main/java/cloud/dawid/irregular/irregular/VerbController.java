package cloud.dawid.irregular.irregular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class VerbController {

    private final VerbService verbService;

    @Autowired
    public VerbController(VerbService verbService) {
        this.verbService = verbService;
    }

    @GetMapping("/")
    public String findAll(Model model){
        List<Verb> verbList = verbService.listVerbs();
        model.addAttribute("verbList", verbList);
        return "showallverbs";
    }

    @RequestMapping(value = "/l/pl/{verb}", method = RequestMethod.GET)
    public String showOneVerbsAndFormEN(Model model, @PathVariable("verb") int verb,
                                        @ModelAttribute String infinitive,
                                        @ModelAttribute String pastsimple,
                                        @ModelAttribute String pastparticiple,
                                        @ModelAttribute String error,
                                        @ModelAttribute String info
                                        ) {
        Optional<Verb> oneverb = verbService.getOneByID(verb);

        //jeżeli jest błędna odpowiedz to dodajemy info
        if(!error.isEmpty()){
            model.addAttribute("error", error);
            model.addAttribute("infinitive", infinitive);
            model.addAttribute("pastsimple", pastsimple);
            model.addAttribute("pastparticiple", pastparticiple);
        }else if(!info.isEmpty()){
            model.addAttribute("info", info);
        }

        model.addAttribute("verb", oneverb.get());
        return "verblpl";
    }

    @RequestMapping(value = "/l/pl/check/", method = RequestMethod.POST)
    public String checkOnePL(Model model,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("idverb") int idverb,
                             @RequestParam("infinitive") String infinitive,
                             @RequestParam("pastsimple") String pastsimple,
                             @RequestParam("pastparticiple") String pastparticiple){
        //konwersja dużych liter na małe
        infinitive = infinitive.toLowerCase();
        pastsimple = pastsimple.toLowerCase();
        pastparticiple = pastparticiple.toLowerCase();

        //Pobranie poprawnej odpowiedzi i przypisanie do Stringów
        Optional<Verb> optionalVerb = verbService.getOneByID(idverb);
        String infinitiveGood = optionalVerb.get().getInfinitive();
        String pastsimpleGood = optionalVerb.get().getPastsimple();
        String pastparticipleGood = optionalVerb.get().getPastparticiple();

        boolean correct = true;

        if(!infinitive.equals(infinitiveGood)){
            redirectAttributes.addFlashAttribute("infinitive" , "error");
            correct = false;
        }
        if(!pastsimple.equals(pastsimpleGood)){
            redirectAttributes.addFlashAttribute("pastsimple", "error");
            correct = false;
        }
        if(!pastparticiple.equals(pastparticipleGood)){
            redirectAttributes.addFlashAttribute("pastparticiple", "error");
            correct = false;
        }

        if(correct) {
            redirectAttributes.addFlashAttribute("info", "poprawna");
            redirectAttributes.addFlashAttribute("error", "no");
            int nextverb = ++idverb;
            return "redirect:/l/pl/"+nextverb;

        }else{
            redirectAttributes.addFlashAttribute("error", "yes");
            return "redirect:/l/pl/"+idverb;
        }
    }
}