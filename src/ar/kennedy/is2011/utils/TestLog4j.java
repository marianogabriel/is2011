package ar.kennedy.is2011.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.PropertyConfigurator;


public class TestLog4j {
	//static Logger logger = Logger.getLogger("ar.kennedy.is2011.utils.TestLog4j");
	static Logger logger = Logger.getLogger(TestLog4j.class);
	static Logger logEx = Logger.getLogger("ar.kennedy.excepetion");
	static final String LOG_PROPERTIES_FILE = "log4j.properties";
	public TestLog4j(){
		initLog4jLogging();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Ejemplo de uso de log4j utilizando un archivo de properties.
		testFileProperties();
		
		//comentar la linea anterior para probar el c—digo que sigue
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
		
		//EL siguiente mensaje NO ser‡ despachado puesto que !(DEBUG>=INFO) 
		logger.debug("Level request: DEBUG. Msj: Hello World");

		//Los siguientes mensajes SI ser‡n despachados puesto que  LEVEL>=INFO 
		logger.error("Level request: ERROR. Msj: Hello World");
		logger.info(" Level request: INFO.  Msj: Hello World");
		

		//***EJEMPLO DEL LOG DE ERRORES CUANDO SE PRODUCE UNA EXCEPTION*****
		//N—tese que estamos usando un logger perteneciente a otra jerarquia
		try{
			printArray(new int[] {0,2,3});
			
		}catch(Exception e){
			logEx.error("Array exception: ",e);
		}
		

		//***AFECTANDO EL LEVEL DE TODO EL REPOSITORIO*****		
		//esta es una forma alternativa de fijar el nivel de todo el 
		//repositorio. N—tese que le pedimos el repositorio al propio logger
		logger.info("*******************************************");
		logger.info("Afectamos el LEVEL de todo el repositorio a ERROR");
		LoggerRepository repository = logger.getLoggerRepository();
		repository.setThreshold(Level.ERROR);
		logger.error("threshold ERROR, ahora el repositorio completo tiene level.ERROR");
		//EL siguiente mensaje NO ser‡ despachado puesto que !(INFO>=ERROR) 
		logger.info("Level request: INFO. Msj: Hello World");
		try{
			//En esta oportunidad, no se desplegar‡n los logs al iterar el array 
			printArray(new int[] {0,2,3});
			
		}catch(Exception e){
			//n—tese que utilizando la estructura try-catch evitamos que
			//aborte la ejecuci—n del programa
			logEx.error("Array exception: ",e);
		}
		
		//***USO DE LOS APPENDERS*****		
		//Hasta aqui hemos usado el appender default 
		//Ahora crearemos un appender de tipo "file"
		try{
			FileAppender fileApp = new FileAppender(new SimpleLayout(),"testLog.log");
			logEx.addAppender(fileApp);
			logEx.error("Este mensaje es para probar el appender a testLog.log");
		}catch(Exception e){
			//FileAppender prever la excepci—n propia de java.util.io
			logEx.error("Array exception: ",e);
		}
		//n—tese el efecto "aditivo" del appeder. Este mensaje se inserta al final
		// de testLog.log SUMADO (en adici—n) al appender default, es decir a la consola
		logEx.error("Otro mensaje para comprobar el efecto *append* en el file");
		

		
		
		
	}
	static void printArray(int[] intArray){
		int len=intArray.length;
		// n—tese que intencionalmente este ciclo for excede los l’mtes del array
		for(int i=0; i<=len; i++){
			logEx.debug("El indice: " + i +" contiene:" + intArray[i] +" ");
		}
	}
	public static void testFileProperties(){
		PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
		logger.info("testFileProperties");
		logger.info("**********************************");
		
	}
	public void initLog4jLogging(){
		PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
		logger.info("**********************************");		
		logger.info("Mensaje desde TestLog4java");
		logger.info("**********************************");		
	}

}
