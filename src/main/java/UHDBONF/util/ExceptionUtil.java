package UHDBONF.util;

import UHDBONF.config.ExceptionMessageConfig;
import UHDBONF.domain.common.OnfException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class ExceptionUtil {
    @Autowired
    private ExceptionMessageConfig loader;
    private static ExceptionMessageConfig sLoader;

    @PostConstruct
    public void registerInstance() {
        this.sLoader  = loader;
    }

    public static OnfException createOnfBizException(String params){
        return new OnfException(generateMessage(params));
    }

    public static String generateMessage(String params) {
        if (params != null) {
            return params;
        }
        return null;
    }
}
