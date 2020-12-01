/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarymvc.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrarymvc.model.Dvd;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author mirandabeamer
 */
@CrossOrigin
@Controller
public class RESTController {
    private DvdLibraryDao dao;
    
    @Inject
    public RESTController(DvdLibraryDao dao){
        this.dao = dao;
    }
    
    @RequestMapping(value = "/dvd/{title}", method=RequestMethod.GET)
    @ResponseBody
    public Dvd getDvd(@PathVariable("id") int id){
        return dao.getDvdById(id);
    }
    
    @RequestMapping(value="/dvd", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Dvd createDvd(@Valid @RequestBody Dvd dvd){
        return dao.addDvd(dvd);
    }     
    
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") int id){
        dao.removeDvd(id);
    }
    
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editDvd(@PathVariable("id") int id, @Valid @RequestBody Dvd dvd) throws UpdateIntegrityException {
        //dvd.setDvdId(id);
        if(id !=dvd.getDvdId()){
            throw new UpdateIntegrityException("Dvd ID on URL must match Dvd Id in submitted data.");
        }
        dao.editDvd(dvd);
    }
    
    @RequestMapping(value="/dvds", method=RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getAllDvds() {
        return dao.getAllDvds();
    }
    
    
}
