package cn.zsgithub.excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DownloadFile {
	
	private String fileName;
	
	private InputStream in;
	
	private long lastModified;
	
	private long length;
	
	public DownloadFile(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public InputStream getInputStream() {
		return in;
	}

	public long getLastModified() {
		return lastModified;
	}

	public long getLength() {
		return length;
	}

	public DownloadFile readFrom(File file) throws IOException {
		this.in = new FileInputStream(file);
		this.length = file.length();
		this.lastModified = file.lastModified();
		return this;
	}

	public DownloadFile readFrom(InputStream in) {
		this.in = in;
		return this;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public void setLength(long length) {
		this.length = length;
	}

}
