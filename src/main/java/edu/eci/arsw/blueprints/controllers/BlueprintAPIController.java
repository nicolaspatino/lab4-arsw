/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
     BlueprintsPersistence  bps = null;

    public void setBlueprintPersistence(BlueprintsPersistence bps) {
        this.bps = bps;
    }

    public BlueprintsPersistence getBpp() {
        return bps;
    }

    @RequestMapping(value = "/blueprints", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoBlueprintAPIController() {
        try {
            synchronized(bps){
            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } }catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error no blueprints found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("blueprints/{authorName}")
    public ResponseEntity<?> GetBlueprintByAuthor(@PathVariable("authorName") String author) {
        try {
            synchronized(bps){
            Set<Blueprint> blues = bps.getBlueprintsByAuthor(author);
            return new ResponseEntity<>(blues, HttpStatus.ACCEPTED);
        } }catch (Exception e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error no author found", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("blueprints/{author}/{name}")
    public ResponseEntity<?> getBlueprintsByAuthorNameAndBpName(@PathVariable("author") String author, @PathVariable("name") String name) {
        try {
            synchronized(bps){
            Set<Blueprint> blue = bps.getBlueprintsByAuthor(author);
            Blueprint res = null;
            for (Blueprint b : blue) {
                if (b.getName().equals(name)) {
                    res = b;
                }
            }
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        } }catch (Exception e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("blueprint not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "blueprints",method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecurso(@RequestBody Blueprint blueprint) {
        try {
            synchronized(bps){
            bps.saveBlueprint(blueprint);
            //registrar dato
                   return new ResponseEntity<>(HttpStatus.CREATED);
        } }catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }

    }
    @RequestMapping(path ="{author}/{name}",method = RequestMethod.PUT)
    public ResponseEntity<?> manejadorPutRecurso(@PathVariable("author") String author,@PathVariable("name") String name,@RequestBody Point p){
        try {
            synchronized(bps){
            Blueprint bpr=null;
            for( Blueprint bp:bps.getBlueprintsByAuthor(author)){
                if(bp.getName().equals(name)){
                    bpr=bp;
                }
            }
            bpr.addPoint(p);
            
            return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("no actualizado", HttpStatus.FORBIDDEN);
        }
    }

}
