package simplerpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟server处的服务调用过程
 * 主要包含参数反序列化，调用服务产生结果，结果序列化以及发送过程
 */
public class Provider{

    //实例化一个服务
    private HelloService helloService = new HelloServiceImp();

    public static void main(String[] args) throws IOException {
        new Provider().run();
    }

    private void run() throws IOException{
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                //建立一个socket
                Socket socket = listener.accept();
                try {
                    // 4 将请求反序列化
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = objectInputStream.readObject();

                    // 5 6 模拟找到对应的服务，并调用服务，产生结果
                    String result = "";
                    if (object instanceof HelloRequestObject) {
                        HelloRequestObject helloRequestObject = (HelloRequestObject) object;
                        if ("hello".equals(helloRequestObject.getMethod())) {
                            result = helloService.hello(helloRequestObject.getName());
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    }

                    // 7 8 将结果反序列化，通过socket将结果返回调用者
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(result);
                } catch (Exception e) {

                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }
}
