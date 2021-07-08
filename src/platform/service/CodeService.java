package platform.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import platform.models.Code;
import platform.repo.CodeRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CodeService {

    private final CodeRepository codeRepository;
    private int newCodeId;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code getCodeSnippetById(int id) {
        Optional<Code> code = codeRepository.findById(id);
        if (code.isPresent()) {
            return code.get();
        } else {
            throw new RuntimeException();
        }
    }

    public ArrayList<Code> getAllCodeSnippet() {
        ArrayList<Code> codes = new ArrayList<>();
        codeRepository.findAll().forEach(codes::add);
        return codes;
    }

    public int getCodeId() {
        newCodeId = (int) codeRepository.count() + 1;
        return newCodeId;
    }

    public void addCode(Code code) {
        codeRepository.save(code);
    }

}
