package simplerpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟远程调用服务的实现过程
 * 主要是参数的序列化，消息的发送，返回结果的反序列化
 */
public class RemoteHelloService implements HelloService{

    @Override
    public String hello(String name) {
        //3 模拟通过服务的名称，找到服务提供者的地址
        List<String> addressList = lookupProviders("HelloService.hello");
        String address = chooseTarget(addressList);
        try {
            //建立socket连接
            Socket socket = new Socket(address, 9090);

            // 2 将请求封装为一个对象
            HelloRequestObject helloRequestObject = generateRequest(name, "hello");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // 2 3 将请求发给服务提供方，将请求对象序列化，最后通过socket发送
            objectOutputStream.writeObject(helloRequestObject);

            // 8 从socket中获取结果响应体，将响应体反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();

            //10 模拟解析得到调用结果
            if (response instanceof String) {
                return (String) response;
            } else {
                throw new InternalError();
            }
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    /**
     * 将请求封装成一个对象，模拟封装的过程，内容包含参数、方法的名称等
     * @param name
     * @param function
     * @return HelloRequestObject
     */
    private HelloRequestObject generateRequest(String name, String function) {
        HelloRequestObject helloRequestObject = new HelloRequestObject();
        helloRequestObject.setName(name);
        helloRequestObject.setMethod(function);
        return helloRequestObject;
    }

    private String chooseTarget(List<String> providers) {
        if (null == providers || providers.size() == 0) {
            throw new IllegalArgumentException();
        }
        return providers.get(0);
    }

    /**
     * 模拟通过服务名来查找服务器，提供服务的服务器可能有多台
     * @param name
     * @return
     */
    public static List<String> lookupProviders(String name) {
        List<String> strings = new ArrayList();
        strings.add("127.0.0.1");
        return strings;
    }

}
