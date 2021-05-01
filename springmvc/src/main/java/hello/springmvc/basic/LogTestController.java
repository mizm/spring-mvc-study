package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    //@Slf4j 롬복을 통해 코드 생략가능
    private final Logger log = LoggerFactory.getLogger(getClass());



    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // log를 사용할때 string + 를 사용하지말자
        // 문자 더하기 때문에 쓸모 없는 리소스를 사용한다
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info(" info = {}", name);
        log.warn("warn = {}", name);
        log.error("error = {}", name);

        return "ok";
    }
}
