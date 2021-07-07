package platform.Code;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HtmlController {

    // ### maybe you can combine two .ftl in one
    @RequestMapping("/code/{fetchNumber}")
    public String getNthCodePage(Model model, @PathVariable int fetchNumber) {
        Code codeSnippetHtml = CodeArrayList.getCodeSnippetById(fetchNumber);
        model.addAttribute("codeSnippetHtml", codeSnippetHtml);
        return "nthCode";
    }

    @RequestMapping("/code/latest")
    public String getLatestCodePage(Model model) {
        // ### Need to refactor, because Collection.sort() doesn't not work
        // ### So we use simple and brutal way to make it work
        ArrayList<Code> arrayList = CodeArrayList.getAllCodeSnippet();
        ArrayList<Code> reverseArrayList = new ArrayList<>();
        if (arrayList.size() > 10) {
            for (int i = arrayList.size()-1; i >= arrayList.size()-10; i--) {
                reverseArrayList.add(arrayList.get(i));
            }
        } else {
            for (int i = arrayList.size()-1; i >= 0; i--) {
                reverseArrayList.add(arrayList.get(i));
            }
        }

        model.addAttribute("codeArrayList", reverseArrayList);
        return "latestCode";
    }
}
