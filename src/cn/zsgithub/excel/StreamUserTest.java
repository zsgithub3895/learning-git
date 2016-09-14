package cn.zsgithub.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.zsgithub.excel.bean.StreamUserOffline;

public class StreamUserTest {
	
	    public void export() {
			String[] headers = new String[]{"终端厂商","牌照方","省份","框架版本","分析时间","时间维度","流用户数"};
			List<StreamUserOffline> StreamUserOfflines = null;
			List<Object[]> objs = new ArrayList<Object[]>();
			for(StreamUserOffline streamUserOffline : StreamUserOfflines) {
				objs.add(new Object[]{
				streamUserOffline.getDeviceProvider(),
				streamUserOffline.getPlatform(),
				streamUserOffline.getProvinceID(),
				streamUserOffline.getFwVersion(),
				streamUserOffline.getParseTime(),
				streamUserOffline.getPeriod(),
				streamUserOffline.getPlayUserCount()
				});
			}
			InputStream in = ExcelTool.export(headers, objs, null);
			new DownloadFile("流用户指标" + System.currentTimeMillis() + ".xls").readFrom(in);
	    }
}
