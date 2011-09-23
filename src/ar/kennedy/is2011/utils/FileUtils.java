package ar.kennedy.is2011.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author mlabarinas
 */
public class FileUtils {

	public static byte[] ObjectToByteArray(Object obj) throws IOException {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ObjectOutputStream oOut = new ObjectOutputStream(bOut);
        oOut.writeObject(obj);
        oOut.flush();

        byte[] byteMap = bOut.toByteArray();
        oOut.close();
        
        return byteMap;
	}
	
	public static Object byteArrayToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bIn = new ByteArrayInputStream(bytes);
		ObjectInputStream oIn = new ObjectInputStream(bIn);
		
		return oIn.readObject();
	}
	
}