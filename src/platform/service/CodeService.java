package platform.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import platform.exception.CodeNotFoundException;
import platform.models.Code;
import platform.repo.CodeRepository;
import platform.utilities.Helper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public void addCode(Code code) {
        codeRepository.save(code);
    }

    public Code getCodeSnippetById(String uuid) {
        Optional<Code> codeSnippet = codeRepository.findById(uuid);
        if (codeSnippet.isPresent()) {
            return codeSnippet.get();
        } else {
            throw new CodeNotFoundException();
        }
    }

    public ArrayList<Code> getAllCodeSnippet() {
        ArrayList<Code> codes = new ArrayList<>();
        codeRepository.findAll().forEach(codes::add);
        return codes;
    }

    public void updateCodeTimeStatus(String uuid) {
        Code readyUpdateOrDeleteCode = getCodeSnippetById(uuid);

        if (isTimeLimitExceeded(readyUpdateOrDeleteCode)) {
            codeRepository.delete(readyUpdateOrDeleteCode);
        } else {
            codeRepository.save(readyUpdateOrDeleteCode);
        }
    }

    public boolean isTimeLimitExceeded(Code readyUpdateOrDeleteCode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.parse(Helper.getCurrentDateTime(), formatter);
        LocalDateTime startTime = LocalDateTime.parse(readyUpdateOrDeleteCode.getDate(), formatter);

        long elapsedTime = Duration.between(startTime, currentTime).getSeconds();
        long originTimeLimit = readyUpdateOrDeleteCode.getOriginTimeLimit();

        readyUpdateOrDeleteCode.setTime(originTimeLimit - elapsedTime);
        return originTimeLimit - elapsedTime <= 0;
    }

    public void updateCodeViewsStatus(String uuid) {
        Code readyUpdateOrDeleteCode = getCodeSnippetById(uuid);

        if (isViewsLimitExceeded(readyUpdateOrDeleteCode)) {
            codeRepository.delete(readyUpdateOrDeleteCode);
        } else {
            codeRepository.save(readyUpdateOrDeleteCode);
        }
    }

    public boolean isViewsLimitExceeded(Code readyUpdateOrDeleteCode) {
        int viewsLimit = readyUpdateOrDeleteCode.getViews();
        readyUpdateOrDeleteCode.setViews(--viewsLimit);
        return viewsLimit <= 0;
    }

}
