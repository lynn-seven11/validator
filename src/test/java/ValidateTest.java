import link.lynux.ExampleObject;
import link.lynux.validator.ValidateUtil;
import org.junit.jupiter.api.Test;

/**
 * @author lin.lin
 * @Description ValidateTest
 * @Date 2024/11/6
 */
public class ValidateTest {
    @Test
    public void test() {
        ExampleObject exampleObject = ExampleObject.builder()
                .mobile("13888888888")
                .uuid("b2a9b1d4-87d9-4f74-a4ef-7c5299b87e3f")
                .url("https://www.baidu.com")
                .fixValue("abc")
                .numberRangeString("8")
                .numberRangeDouble(6.2)
                .regex("test@test.com")
                .build();
        ValidateUtil.validate(exampleObject);
    }
}
