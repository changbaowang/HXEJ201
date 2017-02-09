package hexing.icomm;

import hexing.model.ConnPara;

/**
 * Created by hex170 on 2016/7/20.
 * 请求数据成功回调
 */
public interface ICallbackres {
    //    public void succeed(String res, int len, String afn1, String fn1);
    public void succeed(String res, ConnPara connPara);
}
