/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('#search-button').click(function (event){
        $('#errorMessages').empty();
        var category = $('#search-category').val();
        var searchTerm = $('#search-term').val();
        
        if(category == "" || category == null || searchTerm =="" || searchTerm == null){
            $('#errorMessages').append("Both search term and category required")
        } else {
            
        
        
        if(category == "title"){
             title = $('#search-term').val();
        } else {
            title="";
        }
        if(category == "director"){
            director= $('#search-term').val();
        } else {
            director = "";
        }
        if(category == "rating"){
            rating= $('#search-term').val();
        } else {
            rating = "";
        }
        if(category == "date"){
            date= $('#search-term').val();
        } else {
            date ="";
        }
        
        $.ajax({
            type: 'POST',
            url: 'search/dvds',
            data: JSON.stringify({
                title: title,
                director: director,
                rating: rating,
                date: date
                
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function(data){
                fillDvdTable(data);
                $('#undo-search').show();
            },
            error: function() {
                $('#errorMessages')
                        .append($('<h4>'))
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service');
            }
        });
    }
    });

});

function fillDvdTable(data){
    clearDvdTable();
    var contentRows = $('#contentRows');
    
    $.each(data, function(index, dvd) {
        var title = dvd.title;
        var director = dvd.director;
        var rating = dvd.rating;
        var date = dvd.date;
        
        var row = '<tr>';
        row += '<td>' + title + '</td>';
        row += '<td>' + date + '</td>';
        row += '<td>' + director + '</td>';
        row += '<td>' + rating + '<td>';
        contentRows.append(row);
    })
}
function clearDvdTable() {
        $('#contentRows').empty();
    }
    
function deleteDvd(dvdId) {
    response = confirm("Are you sure you want to delete this title?");
    if(response == true){
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + dvdId,
            success: function() {
                alert("Dvd deleted.");
            }
        })
    }
    
}