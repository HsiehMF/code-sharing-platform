package platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.repo.CodeArrayList;
import platform.models.Code;

import java.util.ArrayList;

import static platform.utilities.Helper.getCodeArrayListByDesc;

@Controller
public class HtmlController {

    @RequestMapping("/code/{id}")
    public String getNthCodePage(Model model, @PathVariable int id) {
        Code codeSnippetHtml = CodeArrayList.getCodeSnippetById(id);
        model.addAttribute("codeSnippetHtml", codeSnippetHtml);
        return "nthCode";
    }

    @RequestMapping("/code/latest")
    public String getLatestCodePage(Model model) {
        ArrayList<Code> latestCode = getCodeArrayListByDesc(CodeArrayList.getAllCodeSnippet());
        model.addAttribute("codeArrayList", latestCode);
        return "latestCode";
    }
}
