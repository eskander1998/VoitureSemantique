package com.esprit;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

 

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.jena.ontology.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class VehiculesRestApi {
	
   
    List<JSONObject> listVoitures=new ArrayList();
    OntModel model = null;
    public OntModel readModel() {
    	String fileName = "data/voiture.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
           
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
    
    
   
    
    
    @RequestMapping(value = "/getVehicules",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> queryGetallInstance() {
    	
		   
        List<JSONObject> list=new ArrayList();
        try {
            this.model = this.readModel();

            String querygetPays =
   	    	     "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +        
   	    	     "PREFIX vec: <http://www.semanticweb.org/karou/ontologies/2022/8/untitled-ontology-5#>  " +
   	    	     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +

   	    	     " SELECT ?voiture  ?nomVoiture ?prixVoiture  " +
   	    	     " WHERE { ?voiture vec:nomVoiture ?nomVoiture . ?voiture vec:prixVoiture ?prixVoiture .  } " ;
           
            //Query query = QueryFactory.create(req1);
            QueryExecution qe = QueryExecutionFactory.create(querygetPays, this.model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                //System.out.println(solution.get("x").toString());
                obj.put("id",x);

                obj.put("label",solution.get("voiture").toString().substring(solution.get("voiture").toString().indexOf('#')+1));
                //obj.put("type",solution.get("type").toString().substring(solution.get("type").toString().indexOf('#')+1));
                obj.put("nomVoiture",solution.get("nomVoiture").toString().substring(solution.get("nomVoiture").toString().indexOf('#')+1));
                obj.put("prixVoiture",solution.get("prixVoiture").toString());
//                obj.put("nombredePorte",solution.get("nbporte").toString().substring(0, 1));
                //obj.put("property",solution.get("nom").toString());
                //obj.put("object",solution.get("z").toString());
                list.add(obj);
            }
            listVoitures = list ;
            System.out.println(x);
            return listVoitures;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    
    @RequestMapping(value = "/getMarque",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> queryMarque() {
    	
		   
        List<JSONObject> list=new ArrayList();
        try {
            this.model = this.readModel();

            String querygetPays =
            		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +        
               	    	     "PREFIX vec: <http://www.semanticweb.org/karou/ontologies/2022/8/untitled-ontology-5#>  " +
               	    	     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +

   	    	     " SELECT ?marque  ?nomMarque ?gammeMarque " +
   	    	     " WHERE { ?marque vec:nomMarque ?nomMarque . ?marque vec:gammeMarque ?gammeMarque .  } " ;
           
            //Query query = QueryFactory.create(req1);
            QueryExecution qe = QueryExecutionFactory.create(querygetPays, this.model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                //System.out.println(solution.get("x").toString());
                obj.put("id",x);

                obj.put("label",solution.get("marque").toString().substring(solution.get("marque").toString().indexOf('#')+1));

                obj.put("nomMarque",solution.get("nomMarque").toString().substring(solution.get("nomMarque").toString().indexOf('#')+1));
                obj.put("gammeMarque",solution.get("gammeMarque").toString().substring(solution.get("gammeMarque").toString().indexOf('#')+1));

                list.add(obj);
            }
            listVoitures = list ;
            System.out.println(x);
            return listVoitures;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/hautegame",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getHauteGames() {
    	
		   
        List<JSONObject> list=new ArrayList();
        String fileName = "data/voiture.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            String querygetPays =
            		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +        
               	    	     "PREFIX vec: <http://www.semanticweb.org/karou/ontologies/2022/8/untitled-ontology-5#>  " +
               	    	     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +

   	    	     " SELECT ?HauteDeGamme ?nomMarque " +
   	    	     " WHERE { ?HauteDeGamme vec:nomMarque ?nomMarque    } " ;
            QueryExecution qe = QueryExecutionFactory.create(querygetPays, model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("id",x);
                System.out.println(solution);
              
                System.out.println(solution.get("nomMarque").toString().substring(solution.get("nomMarque").toString().indexOf('#')+1));
                obj.put("label",solution.get("HauteDeGamme").toString().substring(solution.get("HauteDeGamme").toString().indexOf('#')+1));
               obj.put("nomMarque",solution.get("nomMarque").toString().substring(solution.get("nomMarque").toString().indexOf('#')+1));
        
          
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    @RequestMapping(value = "/getGarage",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> queryGarage() {
    	
		   
        List<JSONObject> list=new ArrayList();
        try {
            this.model = this.readModel();

            String querygetPays =
            		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +        
               	    	     "PREFIX vec: <http://www.semanticweb.org/karou/ontologies/2022/8/untitled-ontology-5#>  " +
               	    	     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +

   	    	     " SELECT ?garage  ?nomGarage  " +
   	    	     " WHERE { ?garage vec:nomGarage ?nomGarage .} " ;
           
            //Query query = QueryFactory.create(req1);
            QueryExecution qe = QueryExecutionFactory.create(querygetPays, this.model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                //System.out.println(solution.get("x").toString());
                obj.put("id",x);

                obj.put("label",solution.get("garage").toString().substring(solution.get("garage").toString().indexOf('#')+1));

                obj.put("nomGarage",solution.get("nomGarage").toString().substring(solution.get("nomGarage").toString().indexOf('#')+1));

                list.add(obj);
            }
            listVoitures = list ;
            System.out.println(x);
            return listVoitures;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @RequestMapping(value = "/getVendeur",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getVendeur() {
    	
		   
        List<JSONObject> list=new ArrayList();
        try {
            this.model = this.readModel();

            String querygetPays =
            		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +        
               	    	     "PREFIX vec: <http://www.semanticweb.org/karou/ontologies/2022/8/untitled-ontology-5#>  " +
               	    	     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +

   	    	     " SELECT ?vendeur  ?nomVendeur  " +
   	    	     " WHERE { ?vendeur vec:nomVendeur ?nomVendeur .} " ;
           
            //Query query = QueryFactory.create(req1);
            QueryExecution qe = QueryExecutionFactory.create(querygetPays, this.model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                //System.out.println(solution.get("x").toString());
                obj.put("id",x);

                obj.put("label",solution.get("vendeur").toString().substring(solution.get("vendeur").toString().indexOf('#')+1));

                obj.put("nomVendeur",solution.get("nomVendeur").toString().substring(solution.get("nomVendeur").toString().indexOf('#')+1));

                list.add(obj);
            }
            listVoitures = list ;
            System.out.println(x);
            return listVoitures;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    @RequestMapping(value = "/getMecanicien",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getMecanicien() {
    	
		   
        List<JSONObject> list=new ArrayList();
        try {
            this.model = this.readModel();

            String querygetPays =
            		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +        
               	    	     "PREFIX vec: <http://www.semanticweb.org/karou/ontologies/2022/8/untitled-ontology-5#>  " +
               	    	     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +

   	    	     " SELECT ?mecanicien  ?nomMecanicien  " +
   	    	     " WHERE { ?mecanicien vec:nomMecanicien ?nomMecanicien .} " ;
           
            //Query query = QueryFactory.create(req1);
            QueryExecution qe = QueryExecutionFactory.create(querygetPays, this.model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                //System.out.println(solution.get("x").toString());
                obj.put("id",x);

                obj.put("label",solution.get("mecanicien").toString().substring(solution.get("mecanicien").toString().indexOf('#')+1));

                obj.put("nomMecanicien",solution.get("nomMecanicien").toString().substring(solution.get("nomMecanicien").toString().indexOf('#')+1));

                list.add(obj);
            }
            listVoitures = list ;
            System.out.println(x);
            return listVoitures;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value = "/getDiagnostiqeur",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getDiagnostiqeur() {
    	
		   
        List<JSONObject> list=new ArrayList();
        try {
            this.model = this.readModel();

            String querygetPays =
            		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +        
               	    	     "PREFIX vec: <http://www.semanticweb.org/karou/ontologies/2022/8/untitled-ontology-5#>  " +
               	    	     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +

   	    	     " SELECT ?diagnostiqeur  ?nomDiagnostiqeur  " +
   	    	     " WHERE { ?diagnostiqeur vec:nomDiagnostiqeur ?nomDiagnostiqeur .} " ;
           
            //Query query = QueryFactory.create(req1);
            QueryExecution qe = QueryExecutionFactory.create(querygetPays, this.model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                //System.out.println(solution.get("x").toString());
                obj.put("id",x);

                obj.put("label",solution.get("diagnostiqeur").toString().substring(solution.get("diagnostiqeur").toString().indexOf('#')+1));

                obj.put("nomDiagnostiqeur",solution.get("nomDiagnostiqeur").toString().substring(solution.get("nomDiagnostiqeur").toString().indexOf('#')+1));

                list.add(obj);
            }
            listVoitures = list ;
            System.out.println(x);
            return listVoitures;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    @RequestMapping(value = "/getMaisonVoiture",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getMaisonVoiture() {
    	
		   
        List<JSONObject> list=new ArrayList();
        try {
            this.model = this.readModel();

            String querygetPays =
            		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +        
               	    	     "PREFIX vec: <http://www.semanticweb.org/karou/ontologies/2022/8/untitled-ontology-5#>  " +
               	    	     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +

   	    	     " SELECT ?MaisonVoiture  ?nomMaisonVoiture  " +
   	    	     " WHERE { ?MaisonVoiture vec:nomMaisonVoiture ?nomMaisonVoiture .} " ;
           
            //Query query = QueryFactory.create(req1);
            QueryExecution qe = QueryExecutionFactory.create(querygetPays, this.model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                //System.out.println(solution.get("x").toString());
                obj.put("id",x);

                obj.put("label",solution.get("MaisonVoiture").toString().substring(solution.get("MaisonVoiture").toString().indexOf('#')+1));

                obj.put("nomMaisonVoiture",solution.get("nomMaisonVoiture").toString().substring(solution.get("nomMaisonVoiture").toString().indexOf('#')+1));

                list.add(obj);
            }
            listVoitures = list ;
            System.out.println(x);
            return listVoitures;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

