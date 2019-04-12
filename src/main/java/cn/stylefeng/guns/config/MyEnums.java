package cn.stylefeng.guns.config;


/*
 *
 * 功能描述
 * @author wangwjunsheng
 * @date 2019/3/15
  * @param null
  * 自定义枚举
  *      1 银行名称
  *      2 离职原因
 * @return
 */
public enum MyEnums {

    BANK_1001("中国银行",1001),
    BANK_1002("中国农业银行",1002),
    BANK_1003("中国工商银行",1003),
    BANK_1004("中国建设银行",1004),
    BANK_1005("交通银行",1005),
    BANK_1006("招商银行",1006),
    BANK_1007("中国人民银行",1007),
    BANK_1008("浦发银行",1008),
    BANK_1009("徽商银行",1009),


    LEAVE_1("正常离职",1),
    LEAVE_2("自动离职",2),
    LEAVE_3("辞退",3),
    LEAVE_4("其他原因",4);


    //成员变量
    private String name;
    private int index;

    MyEnums(String name, int index) {
        this.name = name;
        this.index = index;
    }
    //覆盖方法
    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public static String getName(int index) {
        for (MyEnums c : MyEnums.values()) {
            if (c.index == index) {
                return c.name;
            }
        }
        return "";
    }

    public static Integer getIndexByName(String name) {
        for (MyEnums c : MyEnums.values()) {
            if (c.name.equals(name)) {
                return c.index;
            }
        }
        return 0;
    }

}
