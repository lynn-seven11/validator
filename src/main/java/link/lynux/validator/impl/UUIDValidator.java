package link.lynux.validator.impl;

import cn.hutool.core.lang.Validator;

/**
 * @author lin.lin
 * @Description UUIDValidator
 * @Date 2024/6/2
 */
public class UUIDValidator extends BaseValidator {
    @Override
    public boolean validate(Object value, String express) {
        try {
            String str = (String) value;
            if (Validator.isUUID(str)) {
                return true;
            }
        } catch (Exception ignore) {
        }
        this.setErrMsg("[" + value + "]不是UUID");
        return false;
    }
}