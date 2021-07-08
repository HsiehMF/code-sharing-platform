package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.repo.CodeArrayList;
import platform.models.Code;
import platform.service.CodeService;

import java.util.ArrayList;

import static platform.utilities.Helper.getCodeArrayListByDesc;

@Controller
public class HtmlController {
    private final CodeService codeService;

    @Autowired
    public HtmlController(CodeService codeService) {
        this.codeService = codeService;
    }

    @RequestMapping("/code/{id}")
    public String getNthCodePage(Model model, @PathVariable int id) {
        Code codeSnippetHtml = codeService.getCodeSnippetById(id);
        model.addAttribute("codeSnippetHtml", codeSnippetHtml);
        return "nthCode";
    }

    @RequestMapping("/code/latest")
    public String getLatestCodePage(Model model) {
        ArrayList<Code> latestCode = getCodeArrayListByDesc(codeService.getAllCodeSnippet());
        model.addAttribute("codeArrayList", latestCode);
        return "latestCode";
    }
}
