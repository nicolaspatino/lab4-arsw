/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.Filter;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres
 */
@SpringBootApplication
@Service
public class SubsamplingFiltering implements Filter{

    @Override
    public Set<Blueprint> filter(Set<Blueprint> blueprints) {
        Set<Blueprint> BlueprintsFilter = new HashSet<>();
        int flag=1;
        for (Blueprint b:blueprints){
            if(flag==-1){
                BlueprintsFilter.add(b);
                
            } 
            flag=flag*-1;
        }
        return BlueprintsFilter;
    }
    
}
