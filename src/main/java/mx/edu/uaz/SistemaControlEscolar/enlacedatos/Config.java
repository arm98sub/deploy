package mx.edu.uaz.SistemaControlEscolar.enlacedatos;

import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vaadin.ui.Notification;

public class Config {
	private static SqlSessionFactory sqlMapper;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("mx/edu/uaz/SistemaControlEscolar/db/ConfigDB.xml");
			Properties prop = new Properties();
		    
		    prop.setProperty("db.url", System.getenv("DB_URL"));
		    prop.setProperty("db.user", System.getenv("DB_USER"));
		    prop.setProperty("db.password", System.getenv("DB_PASSWORD"));
		    
			sqlMapper = new SqlSessionFactoryBuilder().build(reader, prop);
		} catch (Exception e) {
			Notification.show("Error al leer el archivo de configuración de la base de datos: ", e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
	}
	
	public static SqlSession abreSesion(){
		try{
			
			SqlSession sesion = sqlMapper.openSession();
			return sesion;
		}catch (Exception e) {
			Notification.show("Error al abrir sesión en la base de datos", e.getMessage(),Notification.Type.ERROR_MESSAGE);
			return null;
		}
		
		
	}
}

