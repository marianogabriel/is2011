package ar.kennedy.is2011.picture;

import java.io.ByteArrayOutputStream;

/**
 * @author: mlabarinas
 */
public class UploadedFile {
	
	private String fileName;
	private String contentType;
	private ByteArrayOutputStream content;

	public String getFileName() {
		return fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public ByteArrayOutputStream getContent() {
		return content;
	}

	public UploadedFile(String fileName, String contentType, ByteArrayOutputStream content) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.content = content;
	}

	public String toString() {
		return (new StringBuilder()).append("[").append(fileName).append(" / ").append(contentType).append("]").toString();
	}

}