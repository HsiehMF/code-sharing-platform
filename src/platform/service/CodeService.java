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

    public Code getCodeSnippetById(String uuid) {
        Optional<Code> code = codeRepository.findById(uuid);
        if (code.isPresent()) {
            return code.get();
        } else {
            throw new CodeNotFoundException();
        }
    }

    public ArrayList<Code> getAllCodeSnippet() {
        ArrayList<Code> codes = new ArrayList<>();
        codeRepository.findAll().forEach(codes::add);
        return codes;
    }

    public void updateCodeStatus(String uuid) {
        Code readyUpdateOrDeleteCode = getCodeSnippetById(uuid);

        if (readyUpdateOrDeleteCode.getTime() == 0) {
            boolean viewLimitTrigger = isViewsLimit(readyUpdateOrDeleteCode);
            System.out.println(viewLimitTrigger);
            if (viewLimitTrigger) {
                codeRepository.deleteById(uuid);
                System.out.println("delete");
            } else {
                codeRepository.save(readyUpdateOrDeleteCode);
            }
        } else if (readyUpdateOrDeleteCode.getViews() == 0) {
            boolean timeLimitTrigger = isTimeLimit(readyUpdateOrDeleteCode);
            if (timeLimitTrigger) {
                codeRepository.deleteById(uuid);
            } else {
                codeRepository.save(readyUpdateOrDeleteCode);
            }
        } else {
            boolean timeLimitTrigger = isTimeLimit(readyUpdateOrDeleteCode);
            boolean viewsLimitTrigger = isViewsLimit(readyUpdateOrDeleteCode);

            if (timeLimitTrigger || viewsLimitTrigger) {
                codeRepository.deleteById(uuid);
            } else {
                codeRepository.save(readyUpdateOrDeleteCode);
            }
        }
    }

    public boolean isTimeLimit(Code readyUpdateOrDeleteCode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        LocalDateTime currentTime = LocalDateTime.parse(Helper.getCurrentDateTime(), formatter);
        LocalDateTime startTime = LocalDateTime.parse(readyUpdateOrDeleteCode.getDate(), formatter);

        long elapsedTime = Duration.between(startTime, currentTime).getSeconds();
        long originTimeLimit = readyUpdateOrDeleteCode.getOriginTimeLimit();
        readyUpdateOrDeleteCode.setTime(originTimeLimit - elapsedTime);

        return originTimeLimit - elapsedTime <= 0;
    }

    public boolean isViewsLimit(Code readyUpdateOrDeleteCode) {
        int viewsLimit = readyUpdateOrDeleteCode.getViews();
        readyUpdateOrDeleteCode.setViews(--viewsLimit);

        return viewsLimit <= 0;
    }

    public void addCode(Code code) {
        codeRepository.save(code);
    }

}
