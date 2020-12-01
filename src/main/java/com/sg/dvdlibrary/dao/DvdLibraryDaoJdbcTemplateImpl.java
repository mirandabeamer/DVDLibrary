/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrarymvc.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mirandabeamer
 */
public class DvdLibraryDaoJdbcTemplateImpl implements DvdLibraryDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //dvds 
    private static final String SQL_INSERT_DVD
            = "insert into dvd(title, release_date, rating_id, notes) "
            + "values(?, ?, ?, ?)";

    private static final String SQL_INSERT_DVD_DIRECTOR
            = "insert into dvd_director (dvd_id, director_id)  values(?, ?)";

    private static final String SQL_DELETE_DVD
            = "delete from dvd where dvd_id = ?";

    private static final String SQL_DELETE_DVD_DIRECTOR
            = "delete from dvd_director where dvd_id = ?";

    private static final String SQL_UPDATE_DVD
            = "update dvd set title = ?, release_date = ?, rating_id = ?, notes = ? "
            + "where dvd_id = ?";
    
     private static final String SQL_UPDATE_DVD_DIRECTOR
             = "update dvd_director set director_id = ? where dvd_id = ?";

    private static final String SQL_SELECT_DVD
            = "select dvd.dvd_id, dvd.title, dvd.release_date, r.rating, d.director_name, dvd.notes "
            + "from dvd join dvd_director dvdd ON dvd.dvd_id = dvdd.dvd_id "
            + "join director d ON dvdd.director_id = d.director_id "
            + "join rating r ON dvd.rating_id = r.rating_id "
            + "where dvd.dvd_id = ?";

    private static final String SQL_SELECT_DVD_DIRECTOR_DIRECTOR_ID_BY_DVD_ID
            = "select director_id from dvd_director where dvd_id = ?";

    private static final String SQL_SELECT_ALL_DVDS
            = "select dvd.dvd_id, dvd.title, dvd.release_date, r.rating, d.director_name, dvd.notes "
            + "from dvd join dvd_director dvdd ON dvd.dvd_id = dvdd.dvd_id "
            + "join director d ON dvdd.director_id = d.director_id "
            + "join rating r ON dvd.rating_id = r.rating_id";

    //director
    private static final String SQL_INSERT_DIRECTOR
            = "insert ignore into director(director_name) values (?)";

    private static final String SQL_SELECT_DVDS_BY_DIRECTOR_ID
            = "select dvd.dvd_id, dvd.title, dvd.release_date, dvd.notes from dvd "
            + " join dvd_director d on dvd.dvd_id = d.dvd_id where d.dvd_id = ?";
    
    private static final String SQL_SELECT_DIRECTOR_ID_BY_DIRECTOR
            ="select director.director_id from director where director_name = ?";

    //rating
    private static final String SQL_INSERT_RATING
            = "insert ignore into rating(rating) values(?)";
    
    private static final String SQL_SELECT_RATING_ID_BY_RATING 
            ="select rating.rating_id from rating where rating = ?";

    private static final String SQL_SELECT_DVDS_BY_RATING_ID
            = "select dvd.dvd_id, dvd.title, dvd.release_date, r.rating, dvd.notes, from dvd "
            + "join dvd_rating r on dvd.rating_id = r.rating_id where dvd.rating_id = ?";
    


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        //insert into rating table 
        jdbcTemplate.update(SQL_INSERT_RATING, dvd.getRating());
        int ratingId = jdbcTemplate.queryForObject(SQL_SELECT_RATING_ID_BY_RATING, Integer.class, dvd.getRating());
        
        
        //insert into director table
        jdbcTemplate.update(SQL_INSERT_DIRECTOR,dvd.getDirector());
        int directorId = jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR_ID_BY_DIRECTOR, Integer.class, dvd.getDirector());
       
        //insert into dvd table
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getDate(),
                ratingId,
                dvd.getNotes());
        int dvdId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        //insert into dvd_director bridge table
        jdbcTemplate.update(SQL_INSERT_DVD_DIRECTOR, dvdId, directorId);
        dvd.setDvdId(dvdId);
        return dvd;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeDvd(int dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD_DIRECTOR, dvdId);
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
       
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void editDvd(Dvd dvd) {
        String director = dvd.getDirector();
        jdbcTemplate.update(SQL_INSERT_DIRECTOR, dvd.getDirector());
        int directorId = jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR_ID_BY_DIRECTOR, Integer.class, dvd.getDirector());
        jdbcTemplate.update(SQL_INSERT_RATING, dvd.getRating());
        int ratingId = jdbcTemplate.queryForObject(SQL_SELECT_RATING_ID_BY_RATING, Integer.class, dvd.getRating());
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                dvd.getDate(),
                ratingId,
                dvd.getNotes(),
                dvd.getDvdId());
        jdbcTemplate.update(SQL_UPDATE_DVD_DIRECTOR, directorId, dvd.getDvdId());
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd getDvdById(int dvdId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD,
                    new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        if(criteria.isEmpty()){
            return getAllDvds();
        } else {
            //build a prepared statement based on the user's search terms
            StringBuilder sQuery = new StringBuilder("select dvd.dvd_id, dvd.title, dvd.release_date, r.rating, d.director_name, dvd.notes "
            + "from dvd join dvd_director dvdd ON dvd.dvd_id = dvdd.dvd_id "
            +"join director d ON dvdd.director_id = d.director_id "
            +"join rating r ON dvd.rating_id = r.rating_id where ");
            
            int numParams = criteria.size();
            int paramPosition = 0;
            
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            
            while(iter.hasNext()){
                SearchTerm currentKey = iter.next();
                if(paramPosition > 0){
                    sQuery.append(" and ");
                }
                
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }
            
            return jdbcTemplate.query(sQuery.toString(), new DvdMapper(), paramVals);
        }
    }

    private void insertDvdDirector(Dvd dvd) {
        final int dvdId = dvd.getDvdId();
        final String director = dvd.getDirector();
        jdbcTemplate.update(SQL_INSERT_DVD_DIRECTOR, dvdId, director);
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdId(rs.getInt("dvd_id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setDate(rs.getString("release_date"));
            dvd.setRating(rs.getString("rating"));
            dvd.setDirector(rs.getString("director_name"));
            dvd.setNotes(rs.getString("notes"));
            return dvd;
        }

    }

}
