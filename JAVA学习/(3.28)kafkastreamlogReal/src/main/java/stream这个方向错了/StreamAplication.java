package stream这个方向错了;

import stream这个方向错了.FirstStreamsUtil.ClearStreamsUtil;
import stream这个方向错了.secondstreamsutil.LogTotalStreamUtil;
import stream这个方向错了.secondstreamsutil.MethodStreamUtil;
import stream这个方向错了.secondstreamsutil.OtaStreamUtil;
import stream这个方向错了.secondstreamsutil.RouteStreamUtil;
import stream这个方向错了.thirdstreamsutil.ErrorFrequncyUtil;

public class StreamAplication {
    public static void main(String[] args) throws Exception {
        /*清洗*/
        ClearStreamsUtil.Clear();
        /*第1个功能：日志总统计*/
        LogTotalStreamUtil.logTotal();
        /*第2个功能：航线出现次数*/
        RouteStreamUtil.routeStreams();
        /*第3个功能：每个OTA出现次数*/
        OtaStreamUtil.otaStreams();
        /*第4个功能：每个method出现次数*/
        MethodStreamUtil.otamethod();
        /*第5个功能：航线错误率*/
        ErrorFrequncyUtil.routeStreams();
    }
}
