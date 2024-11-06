package link.lynux.validator.impl;

import lombok.Data;

/**
 * @author lin.lin
 * @Description BaseValidator
 * @Date 2024/5/30
 */
@Data
public abstract class BaseValidator {
    private String errMsg;

    public abstract boolean validate(Object value, String express);
}
