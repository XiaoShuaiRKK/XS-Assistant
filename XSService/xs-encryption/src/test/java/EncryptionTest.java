import com.xs.assistant.encryption.controller.EncryptionController;
import com.xs.assistant.encryption.EncryptionApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EncryptionApplication.class)
public class EncryptionTest {
    @Autowired
    EncryptionController controller;

    @Test
    public void testEncode(){
        long time = System.currentTimeMillis();
        String encodedPassword;
        System.out.println(encodedPassword = controller.getEncodePassword("XS123456"));
        System.out.println(System.currentTimeMillis() - time + "ms");
        System.out.println(controller.checkEncodePassword("XS123456",encodedPassword));
        System.out.println();
    }
}
