package cn.stylefeng.guns.core.readCard;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.win32.StdCallLibrary;

import java.io.IOException;

/**
 * @author wangjunsheng
 * @version v1.0.0
 * @description
 * @date
 */
public class Card2 {
    public Card2() {
    }

    public static String CStrToString(char[] var0) {
        int var1;
        for(var1 = 0; var1 < var0.length && var0[var1] != 0; ++var1) {
        }

        return new String(var0, 0, var1);
    }

    public static void main(String[] var0) throws IOException {
        Card2.PERSONINFOW var3 = new Card2.PERSONINFOW();
        String var4 = System.getProperty("java.io.tmpdir") + "image.bmp";
        WString var5 = new WString(var4);
        char[] var6 = new char[32];
        System.out.println("按Enter键打开端口");
        int var1 = System.in.read();
        System.in.read();
        int var2 = Card2.CardApi.INSTANCE.OpenCardReader(0, 2, 115200);
        if (var2 != 0) {
            System.out.println("端口打开失败：" + Integer.toString(var2));
        } else {
            while(true) {
                System.out.println("按'q'键退出，按Enter键读卡");
                var1 = System.in.read();
                System.in.read();
                if (var1 == 113) {
                    Card2.CardApi.INSTANCE.CloseCardReader();
                    return;
                }

                var2 = Card2.CardApi.INSTANCE.GetPersonMsgW(var3, var5);
                System.out.println("返回值：" + Integer.toString(var2));
                if (var2 == 0) {
                    System.out.println(CStrToString(var3.name));
                    System.out.println(CStrToString(var3.sex));
                    System.out.println(CStrToString(var3.nation));
                    System.out.println(CStrToString(var3.birthday));
                    System.out.println(CStrToString(var3.address));
                    System.out.println(CStrToString(var3.cardId));
                    System.out.println(CStrToString(var3.police));
                    System.out.println(CStrToString(var3.validStart));
                    System.out.println(CStrToString(var3.validEnd));
                } else {
                    Card2.CardApi.INSTANCE.GetErrorTextW(var6, var6.length);
                    System.out.println(CStrToString(var6));
                }
            }
        }
    }

    public interface CardApi extends StdCallLibrary {
        Card2.CardApi INSTANCE = (Card2.CardApi) Native.loadLibrary("cardapi3.dll", Card2.CardApi.class);

        int OpenCardReader(int var1, int var2, int var3);

        int GetPersonMsgW(Card2.PERSONINFOW var1, WString var2);

        int CloseCardReader();

        void GetErrorTextW(char[] var1, int var2);
    }

    public static class PERSONINFOW extends Structure {
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

        public PERSONINFOW() {
            this.setAlignType(3);
        }
    }
}
