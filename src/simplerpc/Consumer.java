package simplerpc;

/**
 * 模拟远程服务的调用者
 * 调用hello服务
 */
public class Consumer {
    public static void main(String[] args) {
        //1 调用一个远程服务，远程服务的调用过程对调用者来说是不可见的
        HelloService helloService = new RemoteHelloService();
        String result = helloService.hello("lxy");
        System.out.println("the result is :" + result);
    }
}
