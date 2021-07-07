package platform.utilities;

public class Script {

    private final static String sendCodeXMLHttpScript = "<script type=\"text/javascript\">\n" +
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

    private final static String getTextArea = "<title>Create</title>"
            + "<body>"
            + "<textarea id=\"code_snippet\">...</textarea>"
            + "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>"
            + sendCodeXMLHttpScript
                + "</body>";

    public static String getScriptPage() {
        return getTextArea;
    }
}
