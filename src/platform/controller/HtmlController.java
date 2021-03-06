package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import platform.models.Code;
import platform.service.CodeService;
import platform.utilities.Helper;

import java.util.ArrayList;

@Controller
public class HtmlController {

    private final CodeService codeService;

    @Autowired
    public HtmlController(CodeService codeService) {
        this.codeService = codeService;
    }

    @RequestMapping("/code/new")
    public String getAddCodePage() {
        return "newCode";
    }

    @RequestMapping("/code/{uuid}")
    public String getNthCodePage(Model model, @PathVariable String uuid) {
        Code codeSnippetHtml = codeService.getCodeSnippetById(uuid);

        if (codeSnippetHtml.isTimeLimit()) {
            codeService.updateCodeTimeStatus(uuid);
        }
        if (codeSnippetHtml.isViewsLimit()) {
            codeService.updateCodeViewsStatus(uuid);
        }

        model.addAttribute("codeSnippetHtml", codeSnippetHtml);
        return "nthCode";
    }

    @RequestMapping("/code/latest")
    public String getLatestCodePage(Model model) {
        ArrayList<Code> latestCode = Helper.getCodeArrayListByDesc(codeService.getAllCodeSnippet());
        model.addAttribute("codeArrayList", latestCode);
        return "latestCode";
    }
}
