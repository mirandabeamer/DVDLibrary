/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarymvc.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.SearchTerm;
import com.sg.dvdlibrarymvc.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mirandabeamer
 */
@Controller
public class SearchController {
    private DvdLibraryDao dao;
    
    @Inject
    public SearchController(DvdLibraryDao dao){
        this.dao = dao;
    }
    
//    @RequestMapping(value="/displaySearchPage", method=RequestMethod.GET)
//    public String displaySearchPage() {
//        return "/";
//    }
    
    @RequestMapping(value="/search/dvds", method=RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchDvds(@RequestBody Map<String, String> searchMap){
        
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        
        String currentTerm = searchMap.get("title");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.TITLE, currentTerm);
        }
        currentTerm = searchMap.get("director");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DIRECTOR_NAME, currentTerm);
        }
        currentTerm = searchMap.get("date");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.RELEASE_DATE, currentTerm);
        }
        currentTerm = searchMap.get("rating");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.RATING, currentTerm);
        }
        
        List<Dvd> searchDvds =  dao.searchDvds(criteriaMap);
        return searchDvds;
    }
}
