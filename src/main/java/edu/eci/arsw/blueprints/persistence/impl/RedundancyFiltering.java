/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import  edu.eci.arsw.blueprints.persistence.Filter;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres
 */
@SpringBootApplication
@Service
@Primary
public class RedundancyFiltering implements Filter {

    @Override
    public Set<Blueprint> filter(Set<Blueprint> blueprints) {
        Set<Blueprint> BlueprintsFilter = new HashSet<>();
        for (Blueprint b: blueprints){
            if(!BlueprintsFilter.contains(b)){
                BlueprintsFilter.add(b);
            }
        }
        return BlueprintsFilter;
    }
    
}
