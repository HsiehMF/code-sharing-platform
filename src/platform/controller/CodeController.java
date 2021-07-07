package platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.models.Code;
import platform.repo.CodeArrayList;

import java.util.ArrayList;
import java.util.HashMap;

import static platform.utilities.Helper.*;
import static platform.utilities.Script.getScriptPage;

@RestController
public class CodeController {

    @RequestMapping(method = RequestMethod.GET, value = "/code/new")
    public ResponseEntity<String> getCreateCodePage() {
        String page = getScriptPage();
        return ResponseEntity.ok().body(page);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/code/latest")
    public ArrayList<Code> getLatestCodeJson() {
        ArrayList<Code> latestCode = getCodeArrayListByDesc(CodeArrayList.getAllCodeSnippet());
        return latestCode;
    }

    @GetMapping("/api/code")
    public ArrayList<Code> getAllCodeJson() {
        return CodeArrayList.getAllCodeSnippet();
    }

    @GetMapping("/api/code/{fetchNumber}")
    public Code getCodeByIdJson(@PathVariable int fetchNumber) {
        return CodeArrayList.getCodeSnippetById(fetchNumber);
    }

    @PostMapping(path = "/api/code/new")
    public HashMap<String, String> addCodeSnippet(@RequestBody Code newCodeContent) {
        Code codeInstance = addNewCodeToDatabase(newCodeContent);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(codeInstance.getId()));
        return map;
    }

}
