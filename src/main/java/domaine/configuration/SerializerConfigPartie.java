package domaine.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializerConfigPartie {
	private static final String FICHIER_CONFIG = "configPartie.json";
	
	public void sauverConfig(ConfigPartie config){
		Gson jsonWriter = new GsonBuilder().setPrettyPrinting().create();;		
		String json = jsonWriter.toJson(config);
		
		// Ecrire la string JSON dans notre fichier de config
		try {
			FileWriter writer = new FileWriter(FICHIER_CONFIG);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			System.out.println("Erreur d'\u00e9criture dans le fichier de configuration");
			e.printStackTrace();
		}
	}
	
	public ConfigPartie chargerConfig() throws IOException{
		Gson jsonReader = new Gson();
		try {
			FileReader fr = new FileReader(FICHIER_CONFIG);
			BufferedReader br = new BufferedReader(fr);
			
			//On retranscrit notre json en object
			ConfigPartie config = jsonReader.fromJson(br, ConfigPartie.class);
			return config;
		} catch (IOException e) {
			System.out.println("Erreur de lecture dans le fichier de configuration");
			throw e;
		}
	}
	
}
