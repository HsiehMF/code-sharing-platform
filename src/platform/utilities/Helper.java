package platform.utilities;

import platform.models.Code;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Helper {
    private final static String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";

    public static String getCurrentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return localDateTime.format(formatter);
    }

    public static ArrayList<Code> getCodeArrayListByDesc(ArrayList<Code> codeList) {
        ArrayList<Code> reverseList = new ArrayList<Code>();
        ArrayList<Code> publicCodeList = new ArrayList<Code>();
        codeList.forEach(item -> {
            if (!item.isTimeLimit() && !item.isViewsLimit())
                publicCodeList.add(item);
        });

        if (publicCodeList.size() > 10) {
            for (int i = publicCodeList.size() - 1; i >= publicCodeList.size() - 10; i--) {
                reverseList.add(publicCodeList.get(i));
            }
        } else {
            for (int i = publicCodeList.size() - 1; i >= 0; i--) {
                reverseList.add(publicCodeList.get(i));
            }
        }

        return reverseList;
    }

}
