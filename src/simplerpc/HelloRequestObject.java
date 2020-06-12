package simplerpc;

import java.io.Serializable;
import java.util.Objects;

/**
 * 模拟服务调用者封装的数据包对象
 * 方法里只模拟了参数和方法名，实际实现时还有ID等信息
 */
public class HelloRequestObject implements Serializable {

    //在reference中设置，可让IDEA自动生成UID
    private static final long serialVersionUID = -1286791583467802271L;

    String name;
    String method;

    public HelloRequestObject() {
    }

    public HelloRequestObject(String name, String method) {
        this.name = name;
        this.method = method;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloRequestObject that = (HelloRequestObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, method);
    }

    @Override
    public String toString() {
        return "HelloRequestObject{" +
                "name='" + name + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
