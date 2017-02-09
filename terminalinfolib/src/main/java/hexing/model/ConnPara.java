package hexing.model;

import java.io.Serializable;

import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;

/**
 * Created by HEX170 on 2017/1/22.
 */
public class ConnPara implements Serializable {
    public String connafn;
    public String connfn;
    public ICallbackres callback;  //返回校验数据
    public IGetdataCallback getdatacallback; //返回界面显示数据

    public IGetdataCallback getGetdatacallback() {
        return getdatacallback;
    }

    public void setGetdatacallback(IGetdataCallback getdatacallback) {
        this.getdatacallback = getdatacallback;
    }

    public String getConnafn() {
        return connafn;
    }

    public void setConnafn(String connafn) {
        this.connafn = connafn;
    }

    public String getConnfn() {
        return connfn;
    }

    public void setConnfn(String connfn) {
        this.connfn = connfn;
    }

    public ICallbackres getCallback() {
        return callback;
    }

    public void setCallback(ICallbackres callback) {
        this.callback = callback;
    }
}
