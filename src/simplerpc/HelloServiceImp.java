package simplerpc;

/**
 * 模拟具体的远程服务hello
 */
public class HelloServiceImp implements HelloService {

    @Override
    public String hello(String name) {
        return "hello, " + name;
    }
}
