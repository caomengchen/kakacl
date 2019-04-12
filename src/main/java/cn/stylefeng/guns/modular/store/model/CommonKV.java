package cn.stylefeng.guns.modular.store.model;

/**
 * @author wangwei<br/>
 * @Description: <br/>
 * @date 2018/12/18 12:33<br/>
 * ${TAGS}
 */
public class CommonKV {

    private String key;
    private Object value;




    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
