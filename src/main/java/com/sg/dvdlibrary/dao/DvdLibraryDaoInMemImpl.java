/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrarymvc.model.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author mirandabeamer
 */
public class DvdLibraryDaoInMemImpl implements DvdLibraryDao{
    private Map<Integer, Dvd> dvdMap = new HashMap<>();
    private static int dvdIdCounter = 0;
    
    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setDvdId(dvdIdCounter);
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void editDvd(Dvd dvd) {
        dvdMap.put(dvd.getDvdId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> c = dvdMap.values();
        return new ArrayList(c);
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        String titleSearchCriteria = criteria.get(SearchTerm.TITLE);
        String directorSearchCriteria = criteria.get(SearchTerm.DIRECTOR_NAME);
        String releaseDateSearchCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String ratingSearchCriteria = criteria.get(SearchTerm.RATING);
        
        Predicate<Dvd> titleMatchPredicate;
        Predicate<Dvd> directorMatchPredicate;
        Predicate<Dvd> releaseDateMatchPredicate;
        Predicate<Dvd> ratingMatchPredicate;

        Predicate<Dvd> truePredicate = (c) -> {
            return true;
        };
                
        if(titleSearchCriteria == null || titleSearchCriteria.isEmpty()){
            titleMatchPredicate = truePredicate;
        } else {
            titleMatchPredicate = (c) -> c.getTitle().equals(titleSearchCriteria);
        }
        if(directorSearchCriteria == null || directorSearchCriteria.isEmpty()){
           directorMatchPredicate = truePredicate;
        } else {
            directorMatchPredicate = (c) -> c.getDirector().equals(directorSearchCriteria);
        }
        if(releaseDateSearchCriteria == null || releaseDateSearchCriteria.isEmpty()){
            releaseDateMatchPredicate = truePredicate;
        } else {
            releaseDateMatchPredicate = (c) -> c.getDate().equals(releaseDateSearchCriteria);
        }
        if(ratingSearchCriteria == null || ratingSearchCriteria.isEmpty()){
            ratingMatchPredicate = truePredicate;
        } else {
            ratingMatchPredicate = (c) -> c.getRating().equals(ratingSearchCriteria);
        }
        
        return dvdMap.values().stream()
                .filter(titleMatchPredicate
                .and(directorMatchPredicate)
                .and(releaseDateMatchPredicate)
                .and(ratingMatchPredicate))
              .collect(Collectors.toList()); 
    }
    
}
