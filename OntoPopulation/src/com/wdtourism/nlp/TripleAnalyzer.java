package com.wdtourism.nlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wdtourism.nlp.TripleReader.TripleIterator;



public class TripleAnalyzer {
// run main class
	public static void main(String[] agrs){
		double total = 0;
		double hit = 0;
		double maybe = 0;
		TripleReader reader = new TripleReader();
		String fileName=null;
		
		InfoExactor ie = new InfoExactor();
		while((fileName=reader.nextFile())!=null){
			double every = 0;
			double eveHit = 0;
			double eveMaybe = 0;
			
			TripleIterator tIter = reader.getIterator(fileName);
			List<Triple> rest = new ArrayList<Triple>();
			while(tIter.hasNext()){
				total++;
				every++;
				Triple t = tIter.nextTriple();
				if(t.getRel().equals("root")){
					continue;
				}
				String con=null;
				if((con=ie.match(t))!=null){
				 
					try {
						ResultWriter.WriteRecord(fileName, con, t);
						hit++;
						eveHit++;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Write Failed");
					}
				}
				else{
					if(t.getRel().equals("nn")||t.getRel().equals("nsubj")
							||t.getRel().equals("conj")||t.getRel().equals("dep")||t.getRel().equals("dobj")
							||t.getRel().equals("assmod")){
						rest.add(t);
						maybe++;
						eveMaybe++;
					}
				}
				
			}
			try {
				ResultWriter.WriterRecord(fileName, null, rest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(fileName+" processing with "+ eveHit+" matches ");
			/*System.out.print("Hit Rate: "+(double)(eveHit/every)+" ");
			System.out.println("Possible Rate: "+(double)((eveHit+eveMaybe)/every));*/
			System.out.println(" Undiscovered Rate : "+eveMaybe/(eveMaybe+eveHit));
			
		}
		System.out.println("Hit Rate: "+(double)(hit/total));
		System.out.println("Possible Rate: "+(double)((hit+maybe)/total));
	}
	
}
