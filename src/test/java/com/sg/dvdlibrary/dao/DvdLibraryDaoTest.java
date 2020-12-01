/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrarymvc.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mirandabeamer
 */
public class DvdLibraryDaoTest {
    
    private DvdLibraryDao dao;
    
    public DvdLibraryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("DvdLibraryDao", DvdLibraryDao.class);
        
        List<Dvd> dvds = dao.getAllDvds();
        for(Dvd currentDvd : dvds){
            dao.removeDvd(currentDvd.getDvdId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testAddGetDeleteDvd() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Coco");
        dvd.setDirector("Adiren Molina");
        dvd.setDate("2017");
        dvd.setRating("PG");
        dvd.setNotes("10/10");
        dvd = dao.addDvd(dvd);
        Dvd fromDao = dao.getDvdById(dvd.getDvdId());
        assertEquals(fromDao, dvd);
        dao.removeDvd(dvd.getDvdId());
        assertNull(dao.getDvdById(dvd.getDvdId()));
    }

    /**
     * Test of editDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testEditDvd() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Coco");
        dvd.setDirector("Adiren Molina");
        dvd.setDate("2017");
        dvd.setRating("PG");
        dvd.setNotes("10/10");
        dvd = dao.addDvd(dvd);
        dvd.setNotes("5/10");
        dvd.setDirector(("Bill Smith"));
        dao.editDvd(dvd);
        Dvd fromDao = dao.getDvdById(dvd.getDvdId());
        assertEquals(fromDao, dvd);
    }

    /**
     * Test of getAllDvds method, of class DvdLibraryDao.
     */
    @Test
    public void testGetAllDvds() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Coco");
        dvd.setDirector("Adiren Molina");
        dvd.setDate("2017");
        dvd.setRating("PG");
        dvd.setNotes("10/10");
        dao.addDvd(dvd);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("Finding Dory");
        dvd2.setDirector("Andrew Stanton");
        dvd2.setDate("2016");
        dvd2.setRating("PG");
        dvd2.setNotes("9/10");
        dao.addDvd(dvd2);
        
        List<Dvd> dvds = dao.getAllDvds();
        assertEquals(dvds.size(), 2);
       
    }

    /**
     * Test of searchDvds method, of class DvdLibraryDao.
     */
    @Test
    public void testSearchDvds() {
        Dvd dvd = new Dvd();
        dvd.setTitle("Coco");
        dvd.setDirector("Adiren Molina");
        dvd.setDate("2017");
        dvd.setRating("PG");
        dvd.setNotes("10/10");
        dao.addDvd(dvd);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("Finding Dory");
        dvd2.setDirector("Andrew Stanton");
        dvd2.setDate("2016");
        dvd2.setRating("PG");
        dvd2.setNotes("9/10");
        dao.addDvd(dvd2);
        
        Dvd dvd3 = new Dvd();
        dvd3.setTitle("Moana");
        dvd3.setDirector("Ron Clemonts");
        dvd3.setDate("2016");
        dvd3.setRating("PG");
        dvd3.setNotes("8/10");
        dao.addDvd(dvd3);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "Coco");
        List<Dvd> dvds = dao.searchDvds(criteria);
        assertEquals(1, dvds.size());
        assertEquals(dvd, dvds.get(0));
        
        criteria.put(SearchTerm.DIRECTOR_NAME, "Adiren Molina");
        List<Dvd> dvds2 = dao.searchDvds(criteria);
        assertEquals(1, dvds.size());
//        
//        criteria.put(SearchTerm.RELEASE_DATE, "2016");
//        dvds = dao.searchDvds(criteria);
//        assertEquals(2, dvds.size());
//        
        
    }
    
}
