package ctrls;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Focalizado;

public class ImportData {

	public void cargarCSV(String file) {
		String 			csvFile 	=	"C:/csv/" + file + ".csv";
		BufferedReader 	br 			=	null;
		String 			line 		= 	"";
		
		//Se define separador ","
		String 			cvsSplitBy	=	";";
		
		
		
		List<Object> matricula_region = new ArrayList<>();
		
		try{
		    br	=	new BufferedReader(new FileReader(csvFile));
		    while ((line = br.readLine()) != null) {                
		       String[] datos = line.split(cvsSplitBy);
		       //System.out.println(datos[0] + ", " + datos[1] + ", " + datos[2] + ", " + datos[3] + ", " + datos[4] + ", " + datos[5]);
		       matricula_region.add(new Focalizado(
		    		   			datos[0],
		    		   			datos[1],
		    		   			datos[2],
		    		   			datos[3],
		    		   			datos[4],
		    		   			datos[5]
		    		   ));
		    }
		    
		    System.out.println("CTD. DE FILAS A INSERTAR " + file + ": " + matricula_region.size());
		    new FocalizadoData().insertNewDocCollection(matricula_region);		   
		    
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
	
	public static void main(String args[]) {
		
		SimpleDateFormat dfh = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat dff = new SimpleDateFormat("dd/MM/yyyy");
		
		Date inicio = new Date();
		System.out.println("START: " 	+ dfh.format(inicio) + " hrs. " + dff.format(inicio) + "\n - INIT PROCESS");
		long tiempoInicial = inicio.getTime();
		
		String file_1	=	"SIGE_REGION_13_201808_Municipal";
		String file_2	=	"SIGE_REGION_13_201808_P_Subvencionado";
		String file_3	=	"SIGE_REGION_4_201808";
		
		for(int i=0; i<15; i++) {
			new ImportData().cargarCSV(file_1);
			new ImportData().cargarCSV(file_2);
			new ImportData().cargarCSV(file_3);
		}
		
		Date fin = new Date();
		System.out.println("FINISHED: " + dfh.format(fin) + " hrs. " + dff.format(fin) + "\n - PROCESS COMPLETE");
		long tiempoFinal=fin.getTime();
		
		long resta = tiempoInicial - tiempoFinal;		
		resta = resta / (1000 * 60);
		System.out.println("DELAY MIN. " + resta);
		
		resta = tiempoInicial - tiempoFinal;		
		resta = resta / 1000;
		System.out.println("DELAY SEG. " + resta);
		
	}
	
}
