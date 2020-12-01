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
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mirandabeamer
 */
@Controller
public class LibraryController {
    
    DvdLibraryDao dao;
    
    @Inject
    public LibraryController(DvdLibraryDao dao){
        this.dao = dao;
    }
    
    
    @RequestMapping(value={"/displayLibrary", "/"}, method=RequestMethod.GET)
    public String displayLibrary(Model model){
        List<Dvd> dvdList = dao.getAllDvds();
        model.addAttribute("dvdList", dvdList);
        return "index";
    }
    
    @RequestMapping(value="/createDvd", method=RequestMethod.POST)
    public String createDvd(HttpServletRequest request){
        Dvd dvd = new Dvd();
        dvd.setTitle(request.getParameter("title"));
        dvd.setDirector(request.getParameter("director"));
        dvd.setRating(request.getParameter("rating"));
        dvd.setDate(request.getParameter("date"));
        dvd.setNotes(request.getParameter("notes"));
        
        dao.addDvd(dvd);
        return "redirect:/";
    }
  
    @RequestMapping(value = "displayDvdDetails", method=RequestMethod.GET)
    public String displayDvdDetails(HttpServletRequest request, Model model){
        String dvdIdParameter = request.getParameter("dvdId");
        int dvdID = Integer.parseInt(dvdIdParameter);
        Dvd dvd = dao.getDvdById(dvdID);
        model.addAttribute("dvd", dvd);
        return "dvdDetails";
    }
    
    @RequestMapping(value = "/displayCreateDvdPage")
    public String displayCreateDvdPage() {
        return "add";
   }
    
   @RequestMapping(value="/deleteDvd", method = RequestMethod.GET)
   public String deleteDvd(HttpServletRequest request) {
       //changed this method to run via javascript given need for alert box and interaction with user
//        String dvdIdParameter = request.getParameter("dvdId");
//        int dvdID = Integer.parseInt(dvdIdParameter);
//       dao.removeDvd(dvdID);
       return "redirect:/";
   }
   
   @RequestMapping(value="/displayEditDvdForm", method = RequestMethod.GET)
   public String displayEditDvdForm(HttpServletRequest request, Model model){
       String dvdIdParameter = request.getParameter("dvdId");
       int dvdID = Integer.parseInt(dvdIdParameter);
       Dvd dvd = dao.getDvdById(dvdID);
       model.addAttribute("dvd", dvd);
       return "editDvdForm";
   }
   
   @RequestMapping(value="/editDvd", method=RequestMethod.POST)
   public String editDvd(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {
      if(result.hasErrors()){
          return "editDvdForm";
      }
       dao.editDvd(dvd);
       return "redirect:/";
   }
}
