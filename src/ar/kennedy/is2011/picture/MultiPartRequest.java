package ar.kennedy.is2011.picture;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;


import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

/**
 * @author: mlabarinas
 */
public class MultiPartRequest {

	private Hashtable<String, UploadedFile> files = null;
	protected Map<String, Object> parameters = null;
	
	@SuppressWarnings("unchecked")
	public MultiPartRequest(HttpServletRequest req, int maxSize) throws IOException{
		MultipartParser mPartParser = new MultipartParser(req, maxSize);
		parameters = new Hashtable<String, Object>();
		files = new Hashtable<String, UploadedFile>();
		BufferedOutputStream buffer = null;
		UploadedFile uploadedFile = null;
		Part part = null;
		
		while ((part = mPartParser.readNextPart()) != null) {
            if(part.isParam()) {
            	String name = part.getName();
                ParamPart parampart = (ParamPart) part;
                String value = parampart.getStringValue();
                Vector<Object> vector = (Vector<Object>) parameters.get(name);
                
                if(vector == null) {
                    vector = new Vector<Object>();
                    parameters.put(name, vector);
                }
                
                vector.addElement(value);
            
            } else if (part.isFile()) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				buffer = new BufferedOutputStream(stream);
				
				FilePart filepart = (FilePart) part;
				filepart.writeTo(buffer);
				buffer.flush();
				buffer.close();
				uploadedFile = new UploadedFile(filepart.getFileName(),filepart.getContentType(),stream);
				
				files.put(uploadedFile.getFileName(), uploadedFile);
			}
		}
	}
    
	@SuppressWarnings("unchecked")
	public String getParameter(String s) {
        try {
            Vector<Object> vector = (Vector<Object>) parameters.get(s);
            
            if(vector == null || vector.size() == 0) {
                return null;
            
            } else {
                return (String)vector.elementAt(vector.size() - 1);
            }
        
        } catch(Exception exception) {
            return null;
        }
    }

    public Enumeration<UploadedFile> getFiles() {
        return files.elements();
    }
    
}
