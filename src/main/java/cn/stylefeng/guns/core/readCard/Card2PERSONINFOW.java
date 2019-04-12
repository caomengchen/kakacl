package cn.stylefeng.guns.core.readCard;

/**
 * @author wangwei
 * @version v1.0.0
 * @description
 * @date
 */
import com.sun.jna.Structure;

public class Card2PERSONINFOW extends Structure {
    public char[] name = new char[16];
    public char[] sex = new char[2];
    public char[] nation = new char[10];
    public char[] birthday = new char[10];
    public char[] address = new char[36];
    public char[] cardId = new char[20];
    public char[] police = new char[16];
    public char[] validStart = new char[10];
    public char[] validEnd = new char[10];
    public char[] sexCode = new char[2];
    public char[] nationCode = new char[4];
    public char[] appendMsg = new char[36];

    public Card2PERSONINFOW() {
        this.setAlignType(3);
    }
}