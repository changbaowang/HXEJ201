package hexing.icomm;

import hexing.model.CommPara;

public interface ICommucation {

    // 特殊参数
	public static final Object  Tag=null;

    // 转化波特率
	public static final int CBaudRate=300;
	
	/***
	 * 打开通讯模块
	 * @param cpara
	 * @return
	 */
	boolean OpenDevice(CommPara cpara);

	/***
	 * 关闭串口
	 * @return
	 */
	boolean Close();

	/***
	 * 接受
	 * @param SleepT
	 * @param WaitT
	 * @return
	 */
	byte[] ReceiveByt();

	/***
	 * 发送
	 * @param sndByte
	 * @return
	 */
	boolean SendByt(byte[] sndByte);

	/***
	 * 设置波特率
	 * @param Baudrate
	 */
	void SetBaudRate(int Baudrate);
}
