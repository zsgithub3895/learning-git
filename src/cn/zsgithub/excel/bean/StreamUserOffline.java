package cn.zsgithub.excel.bean;

public class StreamUserOffline {
	private long id;
	private String provinceID;
	private String platform;
	private String deviceProvider;
	private String fwVersion;
	private String parseTime;
	private String period;
	private long playUserCount;
	
	private String startDate;
	private String endDate;

	public long getId() {
		return id;
	}
	
	public String getProvinceID() {
		return provinceID;
	}

	public String getPlatform() {
		return platform;
	}

	public String getDeviceProvider() {
		return deviceProvider;
	}

	public String getFwVersion() {
		return fwVersion;
	}

	public String getParseTime() {
		return parseTime;
	}

	public String getPeriod() {
		return period;
	}

	public long getPlayUserCount() {
		return playUserCount;
	}

	public void setPlayUserCount(long playUserCount) {
		this.playUserCount = playUserCount;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setDeviceProvider(String deviceProvider) {
		this.deviceProvider = deviceProvider;
	}

	public void setFwVersion(String fwVersion) {
		this.fwVersion = fwVersion;
	}

	public void setParseTime(String parseTime) {
		this.parseTime = parseTime;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
