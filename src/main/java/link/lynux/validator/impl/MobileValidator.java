package link.lynux.validator.impl;

import cn.hutool.core.lang.Validator;

/**
 * @author lin.lin
 * @Description MobileValidator
 * @Date 2024/6/1
 */
public class MobileValidator extends BaseValidator {

    @Override
    public boolean validate(Object value, String express) {
        try {
            String str = (String) value;
            if (Validator.isMobile(str)) {
                return true;
            }
        } catch (Exception ignore) {
        }
        this.setErrMsg("[" + value + "]不是手机号码");
        return false;
    }
}
