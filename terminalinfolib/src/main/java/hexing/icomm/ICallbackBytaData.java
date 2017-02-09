package hexing.icomm;

import hexing.model.ConnPara;

/**
 * Created by hex170 on 2016/7/20.
 * 回调数据
 */
public interface ICallbackBytaData {
    public void succeed(byte[] res,ConnPara connPara);
}
