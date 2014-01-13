package javaapplication36;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javaapplication36.Ciudad;

public class Permutar
{
	public static void main ( String args [])
	{
	        String p ="0123456789abcdefghijklmnopqrstuvwxyz";
              // String p ="abcdefghi";
               System.out.println("COMBINACION : "+p);
                Random rnd = new Random(111);
                
                Map<String, Ciudad> lista = new HashMap<String, Ciudad>();
                for (int i=0; i < p.length(); i++){
                    String  id= p.charAt(i)+ "";
       
                    lista.put(id, 
                            new Ciudad(id, rnd.nextInt(100), rnd.nextInt(100)));
                }
                
//---------------------Solucion Aleatoria--------------------------------------------------------
            
            String solAleatoria = newSolAleatoria(p, rnd);
            System.out.println("Sol Aleatoria :" + solAleatoria);
            double fitnesAleatoria = fitness(lista, solAleatoria);
            System.out.println("fitness Aleatoria :" + fitnesAleatoria);

            String solVecina = getVecina(solAleatoria, rnd);
            System.out.println("Sol Vecina :" + solVecina);
            double fitnessVecina = fitness(lista, solVecina);
            System.out.println("fitnes Vecina :" + fitnessVecina);

              int cantidadHabitantes=1000;
              int largoSolucion=p.length();
              List<String> poblacion= new ArrayList();
              List<String> nuevaPoblacion=new ArrayList();
              String hija1="";
              String hija2="";
              List<String> hijas=new ArrayList();
              String mejorSolucion = "";
              double mejorFitness = 0;
              List<String> hijasTemp=new ArrayList();
              int numNuevaPob=50;
              
             // llenarPoblacionEstocastico(poblacion,p,rnd,cantidadHabitantes);
              llenarConGradienteAscedente(lista,poblacion,p,rnd,100);
             //imprimirPoblacion(poblacion);
              
            hijas=cruce(poblacion.get(0),poblacion.get(1),rnd.nextInt(largoSolucion));
            
            for (int i = 0; i < 100; i++) {
               // System.out.println("--------------------------------ITERACION Nº: "+(i+1)+"--------------------------------------------------------");
                if (nuevaPoblacion.isEmpty()==true) {
                     for (int j = 0; j < numNuevaPob; j++) {
                     String hab1 = poblacion.get(rnd.nextInt(cantidadHabitantes));
                String hab2 = poblacion.get(rnd.nextInt(cantidadHabitantes));
                
               // System.out.println(" Habitante 1 :" + hab1);
               // System.out.println(" Habitante 2 :" + hab2);
                if(rnd.nextInt(100)<=80) {
                 hijas=cruce(hab1,hab2,rnd.nextInt(largoSolucion));
                }
                //System.out.println("Hijas sin mutar :"+hijas);
                hija1=hijas.get(0);
                hija2=hijas.get(1);
               // System.out.println("M solucion :"+mejorSolucion);
                
               // System.out.println("MEJOR de las Hijas : ["+mejorSolucion+"] - FITNESS : "+mejorFitness);
                if (rnd.nextInt(100)<=15) {
                  hija1=mutacion(hijas.get(0),rnd);
                  
                }
                if (rnd.nextInt(100)<=8) {
                  hija2=mutacion(hijas.get(1),rnd);  
                }                
               // System.out.println("Hija mutada 1 : "+hija1+" | Fitness : "+fitness(lista, hija1));System.out.println("Hija mutada 2 : "+hija2+" | Fitness : "+fitness(lista, hija2));
               // hijas.clear();
                hijas.set(0, hija1);
                hijas.set(1, hija2);
                //System.out.println("hijasss ->"+hijas.get(0));
               // System.out.println(" Coleccion hijas : "+hijas);
                mejorSolucion=mejorDeLaPoblacion(lista,hijas);
                mejorFitness=fitness(lista,mejorSolucion);
              //  System.out.println("MEJOR de las Hijas : ["+mejorSolucion+"] - FITNESS : "+mejorFitness);

                
                    nuevaPoblacion.add(hijas.get(0));
                    nuevaPoblacion.add(hijas.get(1));
                }
                }else{
                     for (int j = 0; j < numNuevaPob; j++) {
                     String hab1 = nuevaPoblacion.get(rnd.nextInt(numNuevaPob));
                String hab2 = nuevaPoblacion.get(rnd.nextInt(numNuevaPob));
                
               // System.out.println(" Habitante 1 :" + hab1);
               // System.out.println(" Habitante 2 :" + hab2);
                if(rnd.nextInt(100)<=80) {
                 hijas=cruce(hab1,hab2,rnd.nextInt(largoSolucion));
                }
                //System.out.println("Hijas sin mutar :"+hijas);
                hija1=hijas.get(0);
                hija2=hijas.get(1);
               // System.out.println("M solucion :"+mejorSolucion);
                
               // System.out.println("MEJOR de las Hijas : ["+mejorSolucion+"] - FITNESS : "+mejorFitness);
                if (rnd.nextInt(100)<=15) {
                  hija1=mutacion(hijas.get(0),rnd);
                  
                }
                if (rnd.nextInt(100)<=8) {
                  hija2=mutacion(hijas.get(1),rnd);  
                }                
               // System.out.println("Hija mutada 1 : "+hija1+" | Fitness : "+fitness(lista, hija1));System.out.println("Hija mutada 2 : "+hija2+" | Fitness : "+fitness(lista, hija2));
               // hijas.clear();
                hijas.set(0, hija1);
                hijas.set(1, hija2);
                //System.out.println("hijasss ->"+hijas.get(0));
               // System.out.println(" Coleccion hijas : "+hijas);
                mejorSolucion=mejorDeLaPoblacion(lista,hijas);
                mejorFitness=fitness(lista,mejorSolucion);
              //  System.out.println("MEJOR de las Hijas : ["+mejorSolucion+"] - FITNESS : "+mejorFitness);

                
                    nuevaPoblacion.add(hijas.get(0));
                    nuevaPoblacion.add(hijas.get(1));
                }
                }
              
               
          }
            
            
            System.out.println("-------------------------------------------------------------------------"); 
            System.out.println("Mejor Solucion Pobl 1 :"+mejorSolucion+"  |  mejor fitness : "+mejorFitness);
             String mejorHabitante=mejorDeLaPoblacion(lista, nuevaPoblacion);
             System.out.println("Mejor de la Poblacion 1: ["+mejorHabitante+"]-Fitness ["+fitness(lista, mejorHabitante)+"]");  
           System.out.println("-------------------------------------------------------------------------"); 
            List<String> segundaPoblacion=new ArrayList();
          /* 
            for (int i = 0; i < 5000; i++) {
                String habit1 = poblacion.get(rnd.nextInt(cantidadHabitantes));
                String habit2 = poblacion.get(rnd.nextInt(cantidadHabitantes));
                String hijaTemp1="";
                String hijaTemp2="";
               // System.out.println(" Habitante 1 :" + hab1);
               // System.out.println(" Habitante 2 :" + hab2);
                if(rnd.nextInt(100)<=49) {
                 hijas=cruce(habit1,habit2,rnd.nextInt(largoSolucion));
                }
                //System.out.println("Hijas sin mutar :"+hijas);
                // System.out.println("M solucion :"+mejorSolucion);
                // System.out.println("MEJOR de las Hijas : ["+mejorSolucion+"] - FITNESS : "+mejorFitness);
                
               // hijaTemp1=mutacion(hijas.get(0),rnd);
                //hijaTemp2=mutacion(hijas.get(1),rnd);
                hijaTemp1=hijas.get(0);
                hijaTemp2=hijas.get(1);
               // System.out.println("Hija mutada 1 : "+hija1+" | Fitness : "+fitness(lista, hija1));System.out.println("Hija mutada 2 : "+hija2+" | Fitness : "+fitness(lista, hija2));
               // hijas.clear();
                hijas.set(0, hijaTemp1);
                hijas.set(1, hijaTemp2);
                //System.out.println("hijasss ->"+hijas.get(0));
               // System.out.println(" Coleccion hijas : "+hijas);
                mejorSolucion=mejorDeLaPoblacion(lista,hijas);
                mejorFitness=fitness(lista,mejorSolucion);
              //  System.out.println("MEJOR de las Hijas : ["+mejorSolucion+"] - FITNESS : "+mejorFitness);

                segundaPoblacion.add(hijas.get(0));
                segundaPoblacion.add(hijas.get(1));
               
            }
            
            System.out.println("Mejor Solucion 2 :" +mejorSolucion+"|mejor Fitness :"+mejorFitness);
           System.out.println("-------------------------------------------------------------------------"); 
          // mejorHabitante=mejorDeLaPoblacion(lista, segundaPoblacion);
          // System.out.println("Mejor de la Poblacion 2: ["+mejorHabitante+"]-Fitness ["+fitness(lista, mejorHabitante)+"]");  
           System.out.println("-------------------------------------------------------------------------"); 
           * /
//--------------------------------Gradiente Ascendente-------------------------------------------------------- 
               
           /*   
              
             String solMejor=solAleatoria;
              double mejorFitnes=fitness(lista,solMejor);
              if(mejorFitnes>fitnessVecina){
                  solMejor=solVecina;
                  
              }
              
             int contador=0;
              // Ciclo de Cnd de termino
            for (int j = 0; j < 50; j++) {
            
              solVecina=newSolAleatoria(solMejor,rnd);
                for (int i = 0; i < 100; i++) {
                
                solVecina=getVecina(solMejor,rnd);
                double fitnesVeci =fitness(lista,solVecina);
                
                if(fitnesVeci<mejorFitnes){
                    mejorFitnes=fitnesVeci;
                    solMejor=solVecina;
                    contador=0;
                    System.out.println("Mejor Sol:"+mejorFitnes);
                }
                else{
                    contador++;
                    if(contador==15){
                        break;
                    }
                }
                  
                contador=0;
            }
                
                
            }
        
             
            
            
            System.out.println("Sol Mejor Final :"+solMejor);
            System.out.println("Sol Mejor Fitness Final:"+mejorFitnes);
            
                 
         */         
                
              
               /*
                for (int i=0; i < 200; i++){
                    String newSol = newSolAleatoria(p,rnd);
                    double fitne = fitness(lista, newSol);
                    System.out.println("sol n°"+i+1);
                     if(mejorFitne>fitne){
                        mejorFitne=fitne;
                        mejorSol=newSol;
                    }
                    System.out.println(newSol + " f=" + fitne);
                
                System.out.println("MEjor Fitne :"+mejorFitne+"||de la SOl:"+mejorSol);
                
                */
                              
//            int pr = getFactorial(p.length()); 
//            String[] ne = permutar(p,pr);
// 
//            
//            String mejorT = p;
//            double mejorF = fitness(lista, p);
//            
//            System.out.println("Permutaciones = " + ne.length);
//            
//            for(int i= 0; i< ne.length;i++)
//            {
//                   // System.out.print(ne[i] + " : ");
//                    double temp = fitness(lista, ne[i]);
//                 //   System.out.println(temp);
//                    if (temp < mejorF){
//                        mejorT = ne[i];
//                        mejorF = temp;
//                    }
//                    if (i % 100 == 0)
//                        System.out.print("." + i);
//                    
//            }
            //System.out.print( "======== ");
//            System.out.print(mejorT + " : " + mejorF);
         
	}
        
       
        
        public static void ordenarAscedentePoblacion(List<String> poblacion){
            
            for (int i = 0; i < poblacion.size(); i++) {
                
            }
        }
        
        public static void llenarConGradienteAscedente(Map<String,Ciudad> lista,List<String> poblacion,String ciudades,Random rnd,int cantidad){
            String solVecina = getVecina(newSolAleatoria(ciudades, rnd), rnd);
           // System.out.println("Sol Vecina :" + solVecina);
            double fitnessVecina = fitness(lista, solVecina);
            String solMejor = newSolAleatoria(ciudades, rnd);
           
              double mejorFitnes=fitness(lista,solMejor);
              if(mejorFitnes>fitnessVecina){
                  solMejor=solVecina;
                  
              }
          
            int contador = 0;
            // Ciclo de Cnd de termino
            for (int j = 0; j < 50; j++) {
            
              solVecina=newSolAleatoria(solMejor,rnd);
                for (int i = 0; i < cantidad; i++) {
                
                solVecina=getVecina(solMejor,rnd);
                double fitnesVeci =fitness(lista,solVecina);
                
                if(fitnesVeci<mejorFitnes){
                    mejorFitnes=fitnesVeci;
                    solMejor=solVecina;
                    contador=0;
                   // System.out.println("Mejor Sol:"+solMejor+"| mejor fitness :"+mejorFitnes);
                }
                else{
                    contador++;
                    if(contador==15){
                        break;
                    }
                }
                poblacion.add(solMejor);
                    //System.out.println("Mejor Solucion Gradiente :"+mejorFitnes+" | indice :"+i); 
                contador=0;
            }
                
                
            }
            System.out.println("mejorSOL : "+1+"fitness Gradiente Asc : "+mejorFitnes);
        }
        
        public static String mejorDeLaPoblacion(Map<String, Ciudad> lista,List<String> poblacion){
            double mejorFitness=fitness(lista,poblacion.get(0));
            String mejorSolucion=poblacion.get(0);
            double fitnessHab;
            for (int i = 0; i < poblacion.size(); i++) {
                fitnessHab=fitness(lista,poblacion.get(i));
                if (mejorFitness>fitnessHab) {
                    mejorSolucion=poblacion.get(i);
                    mejorFitness=fitnessHab;
                }
            }
            return mejorSolucion;
        }
        
        public static String mutacion(String habitante,Random rnd){
            String newSol1 = null;
            String newSol2 = null;
            String stTemp=habitante;
            char[] stTemp2;
            
            int dado1 = rnd.nextInt(stTemp.length());
            int dado2 = rnd.nextInt(stTemp.length());
            
            char posicionA=stTemp.charAt(dado1);
            char posicionB=stTemp.charAt(dado2);
            //System.out.println("char a - char b:"+posicionA+"-"+posicionB);
            //                    Antiguo   ,Nuevo
            stTemp2=stTemp.toCharArray();    
            stTemp2[dado1]=posicionB;
            stTemp2[dado2]=posicionA;
            
           // Arrays.toString(stTemp2);
            
            return new String(stTemp2);
        }
        
        public static List<String> cruce(String hab1,String hab2,int dado){
            //System.out.println("DADO : "+dado);
            List<String> hijas =new ArrayList();
            String habTemp1 ="";
            String habTemp2 ="";
            
            //LLenar los dos string con con las primeros caracteres de las soluciones habitantes
            for (int i = 0; i < hab1.length(); i++) {
                
                 if(i>dado){
                    
                 }
                 else{
                        habTemp1=habTemp1+hab1.charAt(i);
                        habTemp2=habTemp2+hab2.charAt(i);
                        //System.out.println("Sol 1 priori :"+habTemp1);
                 }
             }
         //   System.out.println("primero digitos :"+habTemp1);
         //   System.out.println("primero digitos :"+habTemp2);
            //--------------------------
            
            for (int i = 0; i < hab1.length(); i++) {
                //System.out.println("Temp 1 :"+habTemp1);
                //System.out.println(hab2.charAt(i)+"||"+"(-1) :"+habTemp1.indexOf(hab2.charAt(i)));
                if(habTemp1.indexOf(hab2.charAt(i))<0){
                   habTemp1=habTemp1+hab2.charAt(i);
                  // System.out.println("Que pasa HAB 1 :"+habTemp1);
                }
                if(habTemp2.indexOf(hab1.charAt(i))<0){
                    habTemp2=habTemp2+hab1.charAt(i);
                }
               
            }
            
            
            hijas.add(habTemp1);
            hijas.add(habTemp2);
            return hijas;
        }
        
       
        public static void llenarPoblacionEstocastico(List<String> poblacion,String ciudades,Random rnd,int cantidad){
            for (int i = 0; i < cantidad; i++) {
              poblacion.add(newSolAleatoria(ciudades, rnd));  
            }
           // return poblacion;
        }
        
        public static void imprimirPoblacion(List<String> poblacion){
            for (int i = 0; i < poblacion.size(); i++) {
                System.out.println("Habitante "+i+" : "+poblacion.get(i));
            }
        }
        
        
        public static String getVecina(String p,Random rnd){
            String newSol1 = null;
            String newSol2 = null;
            String stTemp=p;
            char[] stTemp2;
            
            int dado1 = rnd.nextInt(stTemp.length());
            int dado2 = rnd.nextInt(stTemp.length());
            
            char posicionA=stTemp.charAt(dado1);
            char posicionB=stTemp.charAt(dado2);
            //System.out.println("char a - char b:"+posicionA+"-"+posicionB);
            //                    Antiguo   ,Nuevo
            stTemp2=stTemp.toCharArray();    
            stTemp2[dado1]=posicionB;
            stTemp2[dado2]=posicionA;
            
           // Arrays.toString(stTemp2);
            
            return new String(stTemp2);
        } 
        
        public static String newSolAleatoria(String p, Random rnd){
            String temp = p;
            String newSol = "";
            while (temp.length()>1){
                int dado = rnd.nextInt(temp.length());
                char ch = temp.charAt(dado);
                newSol += ch;
                temp = temp.replaceAll(ch+"", "");         
            }
            return newSol + temp;    
        }
        
        public static double fitness(Map<String, Ciudad> lista, String p){
             double tour = 0.0;
                for (int i=0; i<p.length()-1; i++){
                    String inicio = p.charAt(i) + "";
                    String fin = p.charAt(i+1) + "";
                    tour += lista.get(inicio)
                            .distanceTo(lista.get(fin));
              
                }
                
                String inicio = p.charAt(0) + "";
                String fin = p.charAt(p.length()-1) + "";
                
                tour += lista.get(inicio)
                            .distanceTo(lista.get(fin));
                
                return tour;
        }
        
        
	public static String[]  permutar(String cadena,int p)
	{
		String[] per=new String[p];
		int l = cadena.length();
		int d=p/l;
		String[] aux = permutacion(cadena);
		int pos =0;
 
		if(p==1||l==1)
		{
			per[0] = cadena;
			return per;
		}
 
		for(int i=0;i<aux.length;i++)
		{
			String[] auxiliar = permutar(aux[i].substring(1),getFactorial(l-1)); 
			for(int j=0;j<auxiliar.length;j++)
			{
				per[pos]=aux[i].charAt(0)+auxiliar[j];
				pos++;
			}			
		}
		return per;
 
	}
	public static String[] permutacion(String cadena)
	{
		int n = cadena.length();
		String temporal="";
		String[] vector = new String[n];
		vector[0]=cadena;
		for(int i=1;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(j==n-1)
						temporal = cadena.charAt(j)+temporal;
				else temporal += cadena.charAt(j);
			}
			cadena=temporal;
			vector[i]=temporal;
			temporal="";
		}
		return vector;
	}
	public static int getFactorial (int n)
	{
		int result;
		if(n==1||n==0)
			return 1;
 
		result = getFactorial(n-1)*n;
		return result;
	}
	public static void mostrar (String[] vector)
	{
		for(int i= 0; i< vector.length;i++)
		{
			System.out.println(vector[i]);
		}
	}
}
