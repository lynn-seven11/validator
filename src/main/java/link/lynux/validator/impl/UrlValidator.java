package link.lynux.validator.impl;

import cn.hutool.core.lang.Validator;

/**
 * @author lin.lin
 * @Description UrlValidator
 * @Date 2024/5/30
 */
public class UrlValidator extends BaseValidator {
    @Override
    public boolean validate(Object value, String express) {
        try {
            String str = (String) value;
            if (Validator.isUrl(str)) {
                return true;
            }
        } catch (Exception ignore) {
        }
        this.setErrMsg("[" + value + "]不是url");
        return false;
    }
}
