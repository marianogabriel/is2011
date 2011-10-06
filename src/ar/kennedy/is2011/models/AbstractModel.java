package ar.kennedy.is2011.models;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author mlabarinas
 */
public class AbstractModel implements ModelItf {
	
	protected static final Logger log = Logger.getLogger(AbstractModel.class);
	protected Map<String, Object> errors = new HashMap<String, Object>();
	
}