package service;
import org.springframework.stereotype.Service;
import java.util.HashMap;
@Service("dictionary")
public class dictionary {
    HashMap<String,String> toVN = new HashMap<>();
    public HashMap<String, String> getToVN() {
        toVN.put("Hi","xin chào");
        toVN.put("thanks","cảm ơn");
        toVN.put("come","đến đây");
        toVN.put("done","xong!");
        toVN.put("free","miễn phí");
        toVN.put("ok","ok");
        return toVN;
    }
}
