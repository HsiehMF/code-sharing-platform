package platform.Code;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@Controller
public class HtmlController {


    @RequestMapping("/code/{fetchNumber}")
    public String getNthCodeSnippet(Model model, @PathVariable int fetchNumber) {
        System.out.println(fetchNumber);
        return "nthCode";
    }

    @RequestMapping("/code/latest")
    public String renderHtmlPage(Model model, Code code) {
        ArrayList<Code> arrayList = CodeArrayList.getAllCodeSnippet();
        Collections.reverse(arrayList);
        model.addAttribute("codeArrayList", arrayList);
        return "latestCode";
    }
}
