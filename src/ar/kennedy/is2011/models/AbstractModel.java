package ar.kennedy.is2011.models;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author mlabarinas
 */
public class AbstractModel implements ModelItf {
	
	protected final Logger log = Logger.getLogger(getClass());
	protected Map<String, Object> errors = new HashMap<String, Object>();
	
}