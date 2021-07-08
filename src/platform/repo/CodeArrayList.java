package platform.repo;

import platform.models.Code;

import java.util.ArrayList;

public class CodeArrayList {
    private static ArrayList<Code> codeArrayList = new ArrayList<>();
    private static int codeId = 0;

    public CodeArrayList() {
    }

    public static ArrayList<Code> getAllCodeSnippet() {
        return codeArrayList;
    }

    public static Code getCodeSnippetById(int id) {
        return codeArrayList.get(id-1);
    }

    public static int getCodeId() { return ++codeId; }

    public static void addCode(Code code) {
        codeArrayList.add(code);
    }

}
