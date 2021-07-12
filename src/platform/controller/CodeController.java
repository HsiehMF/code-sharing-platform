package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.models.Code;
import platform.service.CodeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static platform.utilities.Helper.*;

@RestController
public class CodeController {

    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/code/latest")
    public ArrayList<Code> getLatestCodeJson() {
        ArrayList<Code> latestCode = getCodeArrayListByDesc(codeService.getAllCodeSnippet());
        return latestCode;
    }

    @GetMapping("/api/code")
    public ArrayList<Code> getAllCodeJson() {
        return codeService.getAllCodeSnippet();
    }

    @GetMapping("/api/code/{uuid}")
    public Code getCodeByIdJson(@PathVariable String uuid) {
        Code codeInstance = codeService.getCodeSnippetById(uuid);

        System.out.println("id: " + codeInstance.getId());
        System.out.println("views: " + codeInstance.getViews());
        System.out.println("time: " + codeInstance.getTime());

        if (codeInstance.getSecret()) {
            codeService.updateCodeStatus(uuid);
        }

        System.out.println("id: " + codeInstance.getId());
        System.out.println("views: " + codeInstance.getViews());
        System.out.println("time: " + codeInstance.getTime());

        return codeService.getCodeSnippetById(uuid);
    }

    @PostMapping(path = "/api/code/new")
    public HashMap<String, String> addCodeSnippet(@RequestBody Code newCodeContent) {
        Code codeInstance = addNewCodeToDatabase(newCodeContent);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(codeInstance.getId()));
        return map;
    }

    public Code addNewCodeToDatabase(Code newCodeContent) {
        boolean isSecret = assertCodeVisibility(newCodeContent);
        System.out.println(isSecret);

        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid: " + uuid);
        System.out.println("view: " + newCodeContent.getViews());
        System.out.println("time: " + newCodeContent.getTime());

        Code codeInstance;
        if (isSecret) {
            codeInstance = new Code(uuid, "Code", newCodeContent.getCode(), getCurrentDateTime(), newCodeContent.getViews(), newCodeContent.getTime(), newCodeContent.getTime(), true);
        } else {
            codeInstance = new Code(uuid, "Code", newCodeContent.getCode(), getCurrentDateTime(), newCodeContent.getViews(), newCodeContent.getTime(), newCodeContent.getTime(), false);
        }

        codeService.addCode(codeInstance);
        return codeInstance;
    }

    public boolean assertCodeVisibility(Code newCodeContent) {
        return newCodeContent.getTime() > 0 || newCodeContent.getViews() > 0;
    }

}
