package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.models.Code;
import platform.service.CodeService;
import platform.utilities.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class CodeController {

    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping(path = "/api/code/new")
    public HashMap<String, String> addNewCodeSnippet(@RequestBody Code newCodeContent) {
        Code codeInstance = addNewCodeToDatabase(newCodeContent);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(codeInstance.getId()));
        return map;
    }

    public Code addNewCodeToDatabase(Code newCodeContent) {

        String uuid = UUID.randomUUID().toString();
        Code codeInstance = new Code(
                uuid, "Code",
                newCodeContent.getCode(),
                Helper.getCurrentDateTime(),
                newCodeContent.getViews(),
                newCodeContent.getTime(),
                newCodeContent.getTime(),
                newCodeContent.getTime() > 0,
                newCodeContent.getViews() > 0
        );

        codeService.addCode(codeInstance);
        return codeInstance;
    }

    @GetMapping("/api/code/{uuid}")
    public Code getCodeByIdJson(@PathVariable String uuid) {
        Code codeInstance = codeService.getCodeSnippetById(uuid);

        if (codeInstance.isTimeLimit()) {
            codeService.updateCodeTimeStatus(uuid);
        }
        if (codeInstance.isViewsLimit()) {
            codeService.updateCodeViewsStatus(uuid);
        }

        return codeService.getCodeSnippetById(uuid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/code/latest")
    public ArrayList<Code> getLatestCodeJson() {
        return Helper.getCodeArrayListByDesc(codeService.getAllCodeSnippet());
    }

}
