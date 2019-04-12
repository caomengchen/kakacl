package cn.stylefeng.guns.core.readCard;

/**
 * @author wangwei
 * @version v1.0.0
 * @description
 * @date
 */
import cn.stylefeng.guns.core.util.Card2.PERSONINFOW;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.win32.StdCallLibrary;

public interface Card2CardApi extends StdCallLibrary {
    Card2CardApi INSTANCE = (Card2CardApi)Native.loadLibrary("cardapi3.dll", Card2CardApi.class);

    int OpenCardReader(int var1, int var2, int var3);

    int GetPersonMsgW(PERSONINFOW var1, WString var2);

    int CloseCardReader();

    void GetErrorTextW(char[] var1, int var2);
}
