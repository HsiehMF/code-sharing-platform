package platform.Code;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@RestController
public class CodeController {
    private final static String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";

    @RequestMapping(method = RequestMethod.GET, value = "/code/new")
    public ResponseEntity<String> renderCreateCodePage() {
        String scriptGetTextArea = "<script type=\"text/javascript\">\n" +
                "function send() {\n" +
                "    let object = {\n" +
                "        \"code\": document.getElementById(\"code_snippet\").value\n" +
                "    };\n" +
                "    \n" +
                "    let json = JSON.stringify(object);\n" +
                "    \n" +
                "    console.log(json);\n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    console.log(\"it's ok\");\n" +
                "    xhr.send(json);\n" +
                "    \n" +
                "    if (xhr.status == 200) {\n" +
                "      alert(\"Success!\");\n" +
                "    }\n" +
                "}</script>";
        String sendCode = "<title>Create</title>"
                + "<body>"
                + "<textarea id=\"code_snippet\">...</textarea>"
                + "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>"
                + scriptGetTextArea
                + "</body>";
        return ResponseEntity.ok().body(sendCode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/code/latest")
    public ArrayList<Code> getCodeWithJson() {
        ArrayList<Code> arrayList = CodeArrayList.getAllCodeSnippet();
        Collections.reverse(arrayList);
        return arrayList;
    }

    @GetMapping("/api/code")
    public ArrayList<Code> getAllCode() {
        return CodeArrayList.getAllCodeSnippet();
    }

    @GetMapping("/api/code/{id}")
    public Code getCodeById(@PathVariable int id) {
        return CodeArrayList.getCodeSnippetById(id);
    }

    @PostMapping(path = "/api/code/new", produces = "application/json;charset=UTF-8")
    public HashMap<String, String> createCodeSnippetApi(@RequestBody Code newCodeContent) {
        // This part needs to be refactored, separate several function, it mixed different abstract layer
        Code codeInstance = new Code(CodeArrayList.getCodeId(),"Code", newCodeContent.getCode(), getCurrentDateTime());
        CodeArrayList.add(codeInstance);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(codeInstance.getId()));
        return map;
    }

    public String getCurrentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return localDateTime.format(formatter);
    }

}
