/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@Service
public class BlueprintAPIController {
    @Autowired
    private BlueprintsPersistence bpp=null;
    public void setBlueprintPersistence (InMemoryBlueprintPersistence bpp){
        this.bpp=bpp;
    }
    public BlueprintsPersistence getBpp(){
        return bpp;
    }
    
    @RequestMapping(value ="/blueprint",method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoBlueprintAPIController(){
        try{
            synchronized(bpp){
                return new ResponseEntity<>(bpp.getAllBlueprints(),HttpStatus.ACCEPTED);
        }
        }catch(Exception ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error no blueprints found",HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    
}

