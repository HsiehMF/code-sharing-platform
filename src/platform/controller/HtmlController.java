package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/code/new")
    public String getCreateCodePage() {
        return "newCode";
    }

    @RequestMapping("/code/{uuid}")
    public String getNthCodePage(Model model, @PathVariable String uuid) {
        Code codeSnippetHtml = codeService.getCodeSnippetById(uuid);

        System.out.println("id: " + codeSnippetHtml.getId());
        System.out.println("views: " + codeSnippetHtml.getViews());
        System.out.println("time: " + codeSnippetHtml.getTime());
        System.out.println(codeSnippetHtml.getSecret());

        if (codeSnippetHtml.getSecret()) {
            codeService.updateCodeStatus(uuid);
        }

        System.out.println("views: " + codeSnippetHtml.getViews());
        System.out.println("time: " + codeSnippetHtml.getTime());
        System.out.println(codeSnippetHtml.getSecret());

//        codeSnippetHtml = codeService.getCodeSnippetById(uuid);
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
